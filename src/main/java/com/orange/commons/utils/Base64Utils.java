/**
 * 
 */
package com.orange.commons.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * @author sunjun
 * 
 */
public class Base64Utils {

	public static byte[] decode(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encode(byte[] encoded) {
		return Base64.encodeBase64String(encoded);
	}

}
