package com.henu.paperadmin.utils;

import org.apache.commons.codec.binary.Base64;

public class ToolUtils {
    public static Long intToLong(int myInt){
        return new Long((long)myInt);
    }
    public static Long objectToLong(Object myInt){
        return new Long((long)myInt);
    }
    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}
