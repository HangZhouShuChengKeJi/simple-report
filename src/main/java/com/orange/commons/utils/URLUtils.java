package com.orange.commons.utils;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class URLUtils {

    /**
     * 正斜杠
     */
    public static final char FORWARD_SLASH = '/';

    /**
     * url 分隔符
     */
    public static final String SEPARATOR_CHAR_URL = FORWARD_SLASH + "";

    /**
     * 根据相对路径获取全部路径
     */
    public static String getRelativeUrl(String url, String relativePath) {
        if (StringUtils.startsWith(relativePath, "http")) {
            return relativePath;
        }
        try {
            URL u = new URL(url);
            StringBuilder sb = new StringBuilder();

            sb.append(u.getProtocol()).append("://").append(u.getHost());
            if (u.getPort() > 0) {
                sb.append(":").append(u.getPort());
            }

            if (relativePath.startsWith("/")) {
                sb.append(relativePath);
            } else {
                String path = null;
                int index = StringUtils.lastIndexOf(u.getPath(), "/");
                if (index > 0) {
                    path = StringUtils.substring(u.getPath(), 0, index);
                } else {
                    path = u.getPath();
                }

                sb.append(path).append("/").append(relativePath);
            }

            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拼接url路径：
     * <pre>
     * pathArr = {null, "", " "}            -> ""
     * pathArr = {"/"}                      -> "/"
     * pathArr = {"a", "b"}                 -> "/a/b"
     * pathArr = {"/a", "b"}                -> "/a/b"
     * pathArr = {"/a/", "/b"}              -> "/a/b"
     * pathArr = {"/a/", "/b/"}             -> "/a/b/"
     * pathArr = {"/a/", "/b/"}             -> "/a/b/"
     * pathArr = {"a", "b", "////"}         -> "/a/b/"
     * pathArr = {"a", "b", null, "", " "}  -> "/a/b"
     * </pre>
     *
     * @param pathArr 路径数组
     *
     * @return
     */
    public static String concat(String... pathArr) {
        StringBuilder builder = new StringBuilder();
        for (String path : pathArr) {
            if (StringUtils.isBlank(path)) {
                continue;
            }

            // 从前往后找 FORWARD_SLASH 的字符
            int s = 0, e = path.length();
            if (path.charAt(0) == FORWARD_SLASH) {
                for (int i = 1; i < e; i++) {
                    if (path.charAt(i) != FORWARD_SLASH) {
                        s = i;
                        break;
                    }
                }
                if (builder.length() == 0 || builder.charAt(builder.length() - 1) != FORWARD_SLASH) {
                    // 追加一个正斜杠
                    builder.append(FORWARD_SLASH);
                }
                if (s == 0) {
                    // 全是正斜杠，结束本次循环
                    continue;
                }
            }
            // 从后往前找 FORWARD_SLASH 的字符
            for (int i = (e = (path.length() - 1)); i >= s; i--) {
                if (path.charAt(i) != FORWARD_SLASH) {
                    e = (i + 1);
                    break;
                }
            }

            if (builder.length() > 0 && builder.charAt(builder.length() - 1) != FORWARD_SLASH) {
                builder.append(FORWARD_SLASH);
            }

            // 移除前后的正斜杠，并追加到 builder 里
            builder.append(path, s, e);

            if (e != path.length()) {
                // 以正斜杠结尾的话，需要追加一个正斜杠
                builder.append(FORWARD_SLASH);
            }
        }
        return builder.toString();
    }

    /**
     * 对指定的字符串做 url 编码
     *
     * @param plainTxt
     *
     * @return 编码后的 url
     */
    public static String encode(String plainTxt) {
        try {
            return URLEncoder.encode(plainTxt, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }

    /**
     * URL 解码
     *
     * @param plainTxt
     *
     * @return 解码后的 url
     */
    public static String decode(String plainTxt) {
        try {
            return URLDecoder.decode(plainTxt, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }


    /**
     * 从 url 里获取文件扩展名
     * <pre>
     * null                                             -> null
     * ""                                               -> ""
     * "index"                                          -> ""
     * "index.jpg"                                      -> "jpg"
     * "/a/b/c/index.jpg"                               -> "jpg"
     * "/a/b/c/index.jpg?name=index.png"                -> "jpg"
     * "/a/b/c/index.jpg#name=index.png"                -> "jpg"
     * "/a/b/c/index.jpg#v=1?name=index.png"            -> "jpg"
     * "/a/b/c/index.jpg?v=1#name=index.png"            -> "jpg"
     * "www.a.com/a/b/c/index.jpg"                      -> "jpg"
     * "www.a.com/a/b/c/index.jpg?name=index.png"       -> "jpg"
     * "www.a.com/a/b/c/index.jpg#name=index.png"       -> "jpg"
     * "www.a.com/a/b/c/index.jpg#v=1?name=index.png"   -> "jpg"
     * "www.a.com/a/b/c/index.jpg?v=1#name=index.png"   -> "jpg"
     * </pre>
     *
     * @param url 原始文件名
     *
     * @return 文件扩展名
     */
    public static String getExNameFromUrl(String url) {
        if (url == null) {
            return null;
        }

        int e;
        if ((e = url.indexOf('#')) > 0) {
            int t;
            if ((t = url.indexOf('?')) > 0 && t < e) {
                e = t;
            }
        } else {
            e = url.indexOf('?');
        }

        int p;
        if (e > 0) {
            // 找最后一个 '/' 后面的，而且在 e 之前的，最后一个 '.'
            if ((p = url.lastIndexOf('.', e)) > 0 && p > url.lastIndexOf('/', e)) {
                return url.substring(p + 1, e);
            }
        } else if (e < 0) {
            // 找最后一个 '/' 后面的，最后一个 '.'
            if ((p = url.lastIndexOf('.')) > 0 && p > url.lastIndexOf('/')) {
                return url.substring(p + 1);
            }
        }
        return "";
    }


    /**
     * 对指定的 url 编码
     *
     * <pre>
     * null                                                -> null
     * ""                                                  -> ""
     * "中国.html"                                          -> "%E4%B8%AD%E5%9B%BD.html"
     * "/中国.html"                                         -> "/%E4%B8%AD%E5%9B%BD.html"
     * "http://temp.91chengguo.com/中国/abc/数学.html"       -> "http://temp.91chengguo.com/%E4%B8%AD%E5%9B%BD/abc/%E6%95%B0%E5%AD%A6.html"
     * </pre>
     *
     * @param url
     *
     * @return 编码后的 url
     */
    public static String encodeURL(final String url) {
        try {
            if (StringUtils.isBlank(url)) {
                return url;
            }
            String mUrl = url.toLowerCase();
            int s;
            if (mUrl.startsWith("https://")) {
                s = 8;
            } else if (mUrl.startsWith("http://")) {
                s = 7;
            }  else if (mUrl.startsWith("/")) {
                s = 1;
            } else {
                s = 0;
            }
            if (s > url.length()) {
                return url;
            }

            String url2 = url.substring(s);

            String[] itemArr = StringUtils.split(url2, "/");
            if (itemArr == null || itemArr.length == 0) {
                return url;
            }
            StringBuilder builder = new StringBuilder();
            if (s > 0) {
                builder.append(url.substring(0, s));
            }

            for (int i = 0; i < itemArr.length; i++) {
                builder.append(URLEncoder.encode(itemArr[i], StandardCharsets.UTF_8.name())).append("/");
            }
            builder.deleteCharAt(builder.length() - 1);

            return builder.toString();
        } catch (UnsupportedEncodingException ignore) {
        }
        return null;
    }
}
