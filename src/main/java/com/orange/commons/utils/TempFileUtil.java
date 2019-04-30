package com.orange.commons.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * 临时文件工具类
 *
 * @author 小天
 * @date 2018/9/18 10:29
 */
public class TempFileUtil {

    private static Logger logger = LoggerFactory.getLogger(TempFileUtil.class);

    private static String tempRootPath;
    private static File   tempRootDir;

    static {
        tempRootPath = System.getProperty("java.io.tmpdir");
        tempRootDir = new File(tempRootPath);
    }

    public static void setTempRootDir(String path) throws IOException {
        if (tempRootPath == null) {
            throw new IllegalArgumentException(tempRootPath + " 不能为空");
        }
        tempRootPath = path;
        tempRootDir = new File(tempRootPath);
        if (tempRootDir.exists()) {
            if (!tempRootDir.isDirectory()) {
                throw new IllegalArgumentException(tempRootPath + " 不是一个目录");
            }
        } else {
            if (!tempRootDir.mkdirs()) {
                throw new IOException("创建目录失败：" + tempRootPath);
            }
        }
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFileDir() throws IOException {
        return createTempFileDir(tempRootDir, new Date());
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootPath 临时文件根路径
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFileDir(String tempRootPath) throws IOException {
        return createTempFileDir(new File(tempRootPath), new Date());
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param baseTime 基准时间
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFileDir(Date baseTime) throws IOException {
        return createTempFileDir(tempRootDir, baseTime);
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootPath 临时文件根路径
     * @param baseTime     基准时间
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFileDir(String tempRootPath, Date baseTime) throws IOException {
        return createTempFileDir(new File(tempRootPath), baseTime);
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootDir 临时文件根目录
     * @param baseTime    基准时间
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFileDir(File tempRootDir, Date baseTime) throws IOException {
        File tempFile = new File(tempRootDir, generateNewName(baseTime, null));
        if (tempFile.mkdirs()) {
            return tempFile;
        }
        throw new IOException("创建临时文件目录失败：" + tempFile.getAbsolutePath());
    }


    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootPath 临时文件根路径
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(String tempRootPath) throws IOException {
        return createTempFile(new File(tempRootPath), new Date(), null);
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootPath 临时文件根路径
     * @param baseTime     基准时间
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(String tempRootPath, Date baseTime) throws IOException {
        return createTempFile(new File(tempRootPath), baseTime, null);
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootDir 临时文件根目录
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(File tempRootDir) throws IOException {
        return createTempFile(tempRootDir, new Date(), null);
    }


    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootDir 临时文件根目录
     * @param exName 文件扩展名
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(File tempRootDir, String exName) throws IOException {
        return createTempFile(tempRootDir, new Date(), exName);
    }

    /**
     * 获取一个新的临时文件目录
     *
     * @param tempRootDir 临时文件根目录
     * @param baseTime    基准时间
     *
     * @return 临时文件
     *
     * @throws IOException
     */
    public static File createTempFile(File tempRootDir, Date baseTime, String exName) throws IOException {
        File tempFile;
        do {
            tempFile = new File(tempRootDir, generateNewName(baseTime, exName));
        } while (tempFile.exists());

        tempFile.getParentFile().mkdirs();


        if (tempFile.createNewFile()) {
            return tempFile;
        }
        throw new IOException("创建临时文件失败：" + tempFile.getAbsolutePath());
    }

    public static void delete(String path) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(new File(path));
        } catch (FileNotFoundException ignore) {
        } catch (IOException e) {
            String logMsg = MessageFormatter.format("删除文件时出现异常：{}", path).getMessage();
            logger.error(logMsg, e);
        }
    }
    public static void delete(File file) {
        try {
            org.apache.commons.io.FileUtils.forceDelete(file);
        } catch (FileNotFoundException ignore) {
        } catch (IOException e) {
            String logMsg = MessageFormatter.format("删除文件时出现异常：{}", file.getAbsolutePath()).getMessage();
            logger.error(logMsg, e);
        }
    }

    private static String generateNewName(Date baseTime, String exName) {
        if (baseTime == null) {
            baseTime = new Date();
        }
        if (StringUtils.isNotBlank(exName)) {
            return DateFormatUtils.format(baseTime, "yyyyMMddHHmmssSSS")
                    + "_" + Thread.currentThread().getId()
                    + "_" + RandomStringUtils.random(8, true, true)
                    + "." + exName;
        }
        return DateFormatUtils.format(baseTime, "yyyyMMddHHmmssSSS")
                + "_" + Thread.currentThread().getId()
                + "_" + RandomStringUtils.random(8, true, true);
    }

}
