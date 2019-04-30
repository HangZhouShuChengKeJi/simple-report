package com.orange.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串掩码工具
 *
 * @author 小天
 * @date 2019/3/12 16:00
 */
public class StringMaskUtil {

    /**
     * 从倒数第5位开始，将4个字符替换为 '*'。常用语手机号打码
     * <pre>
     * null         -> null
     * ""           -> ""
     * "123"        -> "123"
     * "123456"     -> "**3456"
     * "123456789"  -> "1****6789"
     *
     * @return 打码后的字符串
     */
    public static String maskMobile(String plainTxt) {
        return mask(plainTxt, '*', 4, 4, false);
    }

    /**
     * 从倒数第5位开始，将4个字符替换为 '*'。常用语手机号打码
     * <pre>
     * null '*' 4           -> null
     * "" '*' 4             -> ""
     * "123" '*' 4          -> "123"
     * "123456" '*' 4       -> "**3456"
     * "123456789" '*' 4    -> "1****6789"
     *
     * @param plainTxt      待打码的字符串
     * @param c             用于替换的字符
     * @param size          替换的长度
     * @param offset        偏移量
     * @param positive      是否正序处理
     * @return 打码后的字符串
     */
    public static String mask(String plainTxt, char c, int size, int offset, boolean positive) {
        if (plainTxt == null) {
            return null;
        }
        if (StringUtils.isEmpty(plainTxt)) {
            return "";
        }
        if (size <= 0) {
            throw new IllegalArgumentException("'size' 必须大于 0");
        }
        int start, end;
        if (positive) {
            start = offset;
            if (start >= plainTxt.length()) {
                return plainTxt;
            }
            end = start + size;
            if (end > plainTxt.length()) {
                end = plainTxt.length();
            }
        } else {
            end = plainTxt.length() - offset;
            if (end <= 0) {
                return plainTxt;
            }
            start = end < size ? 0 : end - size;
        }

        return plainTxt.substring(0, start) + StringUtils.repeat(c, end - start) + plainTxt.substring(end);
    }
}
