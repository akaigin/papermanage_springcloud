package com.henu.papercommon.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.UUID;

public class FileUtils {
    public static String saveFile(byte[] file, String filePath, String fileName) {
        int random = (int) (Math.random() * 100 + 1);
        int random1 = (int) (Math.random() * 100 + 1);
        filePath = filePath + random + File.separator + random1 + File.separator;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath + fileName);
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer buf = ByteBuffer.wrap(file);
            while (fileChannel.write(buf) != 0) {
            }
        } catch (Exception e) {

        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //url
        return random + "/" + random1 + "/" + fileName;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    /**
     * <B>方法名称：</B>获取文件后缀名<BR>
     * <B>概要说明：</B>获取文件后缀名<BR>
     * @param fileName
     * @return  如："jpg"、"txt"、"zip" 等
     */
    public static String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
    }
    /**
     * <B>方法名称：</B>获取文件名<BR>
     * <B>概要说明：</B>获取文件名<BR>
     * @param path
     * @return
     */
    public static String getOriginalFilename(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return "";
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
    }
}
