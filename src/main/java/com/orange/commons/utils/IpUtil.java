package com.orange.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class IpUtil {

    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private static Set<String> internalIps = new HashSet<String>();

    static {
        internalIps.add("0:0:0:0:0:0:0:1");
        internalIps.add("127.0.0.1");
        internalIps.add("10.28.64.89");
    }

    /**
     * 功能描述：获取真实的IP地址
     *
     * @param request
     *
     * @author yajun
     */
    public static String getIp(HttpServletRequest request) {
        String ip = getAllIp(request);
        if (!isnull(ip) && ip.contains(",")) {
            String[] ips = ip.split(",");
            ip = ips[ips.length - 1];
        }
        return StringUtils.trim(ip);
    }

    /**
     * 如果经过代理，获取到IP可能为 10.1.3.121,115.199.150.107,115.199.150.108
     */
    public static String getAllIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 功能描述：获取真实的IP地址
     *
     * @param request
     *
     * @author yajun
     */
    public static String getFirstIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!isnull(ip) && ip.contains(",")) {
            String[] ips = ip.split(",");
            ip = ips[0];
            if (StringUtils.startsWith(ip, "192.168") || StringUtils.startsWith(ip, "10.") || StringUtils.startsWith(ip, "172.")) {
                if (ips.length >= 2) {
                    ip = ips[1];
                }
            }
        }
        return StringUtils.trim(ip);
    }

    /**
     * str空判政
     */
    public static boolean isnull(String str) {
        if (null == str || str.equalsIgnoreCase("null") || str.equals("")) {
            return true;
        } else
            return false;
    }

    /**
     * 是否内网安全的IP
     */
    public static boolean isInternalSafeIp(HttpServletRequest request) {
        String ip = getIp(request);
        if (internalIps.contains(ip)) {
            return true;
        } else {
            logger.error(ip + " is not a safe ip, we're attacked ????");
            return false;
        }
    }

    /**
     * 获取本机IP。
     * 优先获取系统属性：orange.host.ip 作为本机IP
     * 其次使用环境变量：ORANGE_HOST_IP 作为本机IP
     * 否则，返回第一块网卡的IP
     */
    public static String getLocalIP() {
        String ip = System.getProperty("orange.host.ip", System.getenv("ORANGE_HOST_IP"));
        if (StringUtils.isNotBlank(ip)) {
            return ip;
        }

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface networkInterface;
            Enumeration<InetAddress> inetAddresses;
            InetAddress inetAddress;
            while (networkInterfaces.hasMoreElements()) {
                networkInterface = networkInterfaces.nextElement();
                inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof Inet4Address) {
                        ip = inetAddress.getHostAddress();
                        if (ip.startsWith("127")) {
                            continue;
                        }
                        if (ip.equals("0.0.0.0")) {
                            continue;
                        }
                        return ip;
                    }
                }
            }
        } catch (SocketException e) {
            logger.error("获取本机IP时出现异常", e);
        }
        return ip;
    }

}
