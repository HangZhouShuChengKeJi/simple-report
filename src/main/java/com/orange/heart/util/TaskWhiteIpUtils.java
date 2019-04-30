package com.orange.heart.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class TaskWhiteIpUtils {

	private static final Set<String>	whiteIps	= new HashSet<String>();

	static {
		whiteIps.add("10.1.1.138");
		// 线上
		whiteIps.add("10.174.36.65");
	}

	/**
	 * 判断启动任务的机器的IP是否是任务白名单里的IP
	 * 
	 * @return true 表示是 ,false 表示不是
	 */
	public static boolean canExeuteTask() {
		String ip = getLocalIPForJava();

		for (String x : whiteIps) {
			if (ip.contains(x)) {
				return true;
			}
		}

		return false;
	}

	public static String getLocalIPForJava() {
		StringBuilder sb = new StringBuilder();
		try {
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface intf = (NetworkInterface) en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						sb.append(inetAddress.getHostAddress().toString() + "\n");
					}
				}
			}
		} catch (SocketException e) {
		}
		return sb.toString();
	}

}
