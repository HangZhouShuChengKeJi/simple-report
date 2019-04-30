package com.orange.commons.utils;

import java.io.Closeable;

public class IOUtils {

	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (final Throwable ioe)

		{
			// ignore
		}
	}
}
