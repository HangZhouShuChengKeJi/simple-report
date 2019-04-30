/**
 * 
 */
package com.orange.commons.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/** 换行 */
	public static final String	LINE_SEPARATOR			= System.getProperty("line.separator");

	/** 读取文件的最大值 */
	public static final long	MAX_STATIC_FILE_SIZE	= 20 * 1024 * 1024;

	/** 每次读取的缓存大小 */
	public static final int		BUFFER_SIZE				= 20 * 1024;

	/**
	 * 获取文件内容，以字符串返回
	 * 
	 * @param basePath 目录
	 * @param fileName 文件名
	 * @return
	 * @throws IOException
	 */
	public static String getFileContent(String basePath, String fileName) throws IOException {
		return getFileContent(getFile(basePath, fileName));
	}

	/**
	 * 删除文件，或目录，目录必须为空
	 */
	public static void deleteQuietly(String... file) {
		for (String f : file) {
			if (StringUtils.isNotBlank(f)) {
				File ff = new File(f);
				if (ff != null) {
					ff.delete();
				}
			}
		}
	}

	/**
	 * 删除文件，或目录，目录必须为空
	 */
	public static void deleteQuietly(File... file) {
		for (File f : file) {
			if (f != null) {
				f.delete();
			}
		}
	}

	/**
	 * 获取文件内容，以字符串返回
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileContent(File file) throws IOException {
		// 获取文件行列表
		List<String> list = getFileLineList(file);

		if (null == list) {
			return null;
		}

		StringBuilder sb = new StringBuilder(80);

		// 遍历文件行列表
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(LINE_SEPARATOR);
		}

		return sb.toString();
	}

	/**
	 * 获取文件行列表
	 *
	 * @param basePath 目录
	 * @param fileName 文件名
	 * @return
	 * @throws IOException
	 */
	public static List<String> getFileLineList(String basePath, String fileName) throws IOException {
		return getFileLineList(getFile(basePath, fileName));
	}

	/**
	 * 获取文件行列表
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String> getFileLineList(File file) throws IOException {
		if (null == file || !file.exists() || !file.isFile()) {
			return null;
		}

		// 如果超出文件可读的最大值
		if (file.length() > MAX_STATIC_FILE_SIZE) {
			return null;
		}

		List<String> list = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = null;

		while ((line = br.readLine()) != null) {
			list.add(line);
		}

		close(br);

		return list;
	}

	/**
	 * 生成文件
	 * 
	 * @param basePath 文件路径
	 * @param fileName 文件名
	 * @param lines 行
	 * @param append 追加/覆盖
	 * @throws IOException
	 */
	public static boolean writeFile(String basePath, String fileName, List<String> lines, boolean append) throws IOException {
		StringBuilder sBuilder = new StringBuilder(1000);

		// 如果内容不为空，则拼接起来
		if (CollectionUtils.isNotEmpty(lines)) {
			for (int i = 0; i < lines.size(); i++) {
				sBuilder.append(lines.get(i)).append(LINE_SEPARATOR);
			}
		}

		return writeFile(basePath, fileName, sBuilder.toString(), append);
	}

	/**
	 * 生成文件
	 *
	 * @param basePath 文件路径
	 * @param fileName 文件名
	 * @param content 文件内容
	 * @param append 追加/覆盖
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String basePath, String fileName, String content, boolean append) throws IOException {
		// 如果内容为null或者超出最大值
		if (null == content || content.length() > MAX_STATIC_FILE_SIZE) {
			return false;
		}

		// 如果创建目录失败，则直接诶返回
		if (!createFolder(basePath)) {
			return false;
		}

		// 创建文件
		File file = createFile(basePath, fileName);

		// 如果创建文件失败，则直接诶返回
		if (null == file) {
			return false;
		}

		BufferedWriter write = new BufferedWriter(new FileWriter(file, append));

		// 开始写
		write.write(content);
		// 输出到硬盘
		write.flush();
		// 关闭输出流
		close(write);

		return true;
	}

	/**
	 * 获取文件/目录
	 * 
	 * @param basePath 目录
	 * @param fileName 文件名
	 * @return
	 */
	public static File getFile(String basePath, String fileName) {
		// 如果文件名为空，则直接返回
		if (null == fileName || fileName.length() == 0) {
			return null;
		}

		File file;

		// 如果目录文件，则使用相对路径
		if (null == basePath || basePath.length() == 0) {
			file = new File(fileName);
		} else {
			file = new File(basePath, fileName);
		}

		return file;
	}

	/**
	 * 创建文件
	 * 
	 * @param basePath 目录
	 * @param fileName 文件名
	 * @return
	 */
	public static File createFile(String basePath, String fileName) {
		File file = getFile(basePath, fileName);

		// 判断文件/目录是否存在，如果存在
		if (file.exists()) {
			// 如果不是文件，则直接返回null
			if (!file.isFile()) {
				return null;
			}
			// 如果不是文件，则直接返回文件
			else {
				return file;
			}
		}

		try {
			// 如果创建失败，则直接返回null
			if (!file.createNewFile()) {
				return null;
			}

			return file;
		} catch (IOException e) {
			// 如果创建出错，则直接返回null
			return null;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param path
	 */
	public static boolean createFolder(String path) {
		// 如果路径为空，则返回失败
		if (null == path || path.length() == 0) {
			return false;
		}

		File folder = new File(path);

		// 判断文件/目录是否存在
		if (folder.exists()) {
			// 如果不是目录，则重命名
			if (!folder.isDirectory()) {
				// 如果重命名失败，则直接返回
				if (!folder.renameTo(new File(folder.getParent(), folder.getName() + "." + System.currentTimeMillis()))) {
					return false;
				}
				folder = new File(path);
			}
			// 如果是目录，则直接返回
			else {
				return true;
			}
		}

		// 如果创建失败，则打印日志并返回
		if (!folder.mkdirs()) {
			// log sth.
			return false;
		}

		return true;

	}

	/**
	 * 判断是否为文件且存在
	 * 
	 * @param srcFile
	 * @return
	 */
	public static boolean isFileAndExist(File srcFile) {
		return (null != srcFile) && (srcFile.exists()) && (srcFile.isFile());
	}

	/**
	 * 关闭输入流
	 * 
	 * @param reader
	 * @throws IOException
	 */
	public static void close(Reader reader) throws IOException {
		if (null != reader) {
			reader.close();
		}
	}

	/**
	 * 关闭输入流
	 * 
	 * @param reader
	 * @throws IOException
	 */
	public static void close(InputStream reader) throws IOException {
		if (null != reader) {
			reader.close();
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @throws IOException
	 */
	public static void close(Writer writer) throws IOException {
		if (null != writer) {
			writer.close();
		}
	}

	/**
	 * 关闭输出流
	 */
	public static void close(OutputStream writer) throws IOException {
		if (null != writer) {
			writer.close();
		}
	}

	public static int createFile(InputStream stream, String path, String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(new File(path, filename));
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		close(fs);
		close(stream);
		return bytesum;
	}

	public static void makeSureFileExists(String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

}
