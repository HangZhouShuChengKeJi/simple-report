package com.orange.commons.utils;

import org.springframework.beans.factory.FactoryBean;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NamingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 用于数据库加密，解密，跟jboss4.0.5兼容,加解密代码源自于org.jboss.resource.security.SecureIdentityLoginModule
 * 
 * <pre>
 * 几点说明：
 * 1. 为什么不重用{@link CommonSecureIdentityLoginModule}, 原先的CommonSecureIdentityLoginModule主要针对是适合c3p0的一套实现，返回的数据是整个Drive Properties
 *    原先的c3p0 pool配置是区分为两个bean，一个DriverManagerDataSource(username,password处理),一个pooledDataSource. 而在dbcp中,处理方式比较简单直接,使用了{@link BasicDataSource}
 *    所以最后选择处理密码加密也就比较直接，针对password进行解密处理
 * 2. 类中未引入productMode的判断，主要考虑是职责明确问题，关注于密码的处理，至于决定是否需要密码处理应该是由一个特定更高级的上下文去关注
 * </pre>
 * 
 * @author administrator
 */
@SuppressWarnings("rawtypes")
public class EncryptDBPasswordFactory implements FactoryBean {

    private String password;

    private static String encode(String secret) throws NoSuchPaddingException, NoSuchAlgorithmException,
                                               InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = "jaas is the way".getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encoding = cipher.doFinal(secret.getBytes());
        BigInteger n = new BigInteger(encoding);
        return n.toString(16);
    }

    private static char[] decode(String secret) throws NoSuchPaddingException, NoSuchAlgorithmException,
                                               InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] kbytes = "jaas is the way".getBytes();
        SecretKeySpec key = new SecretKeySpec(kbytes, "Blowfish");

        BigInteger n = new BigInteger(secret, 16);
        byte[] encoding = n.toByteArray();

        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decode = cipher.doFinal(encoding);
        return new String(decode).toCharArray();
    }

    public Object getObject() throws Exception {
        if (password != null) {
            return String.valueOf(decode(password));
        } else {
            return null;
        }
    }

    public Class getObjectType() {
        return String.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) throws NamingException, InvalidKeyException, NoSuchAlgorithmException,
                                          NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        String secret = "123456";
        System.out.println(encode(secret));
    }

}
