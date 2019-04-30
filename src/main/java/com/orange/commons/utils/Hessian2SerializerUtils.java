package com.orange.commons.utils;

import com.caucho.hessian.HessianException;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * hessian2 协议序列化工具类
 *
 * @author 小天
 * @date 2019/2/12 18:04
 */
public class Hessian2SerializerUtils {

    /**
     * 序列化
     */
    public static byte[] serialize(Object value) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(outputStream);
        try {
            hessian2Output.writeObject(value);
        } catch (IOException e) {
            // 理论上不会出现这种情况
            throw new HessianException(e);
        } finally {
            try {
                hessian2Output.close();
            } catch (IOException ignore) {
                // 理论上不会出现这种情况
            }
        }
        // flush 之后，才能调用该方法
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     */
    public static Object deserialize(byte[] bytes) {
        return deserialize(bytes, null);
    }

    /**
     * 反序列化
     */
    public static Object deserialize(byte[] bytes, Class cl) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Hessian2Input hessian2Input = new Hessian2Input(inputStream);

        try {
            return hessian2Input.readObject(cl);
        } catch (IOException e) {
            // 理论上不会出现这种情况
            throw new HessianException(e);
        } finally {
            try {
                hessian2Input.close();
            } catch (IOException ignore) {
                // 理论上不会出现这种情况
            }
        }
    }
}
