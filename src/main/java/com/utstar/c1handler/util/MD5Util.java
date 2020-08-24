package com.utstar.c1handler.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.UUID;

public class MD5Util {

    private static final Log log = LogFactory.getLog(MD5Util.class);
    public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getHash(String fileName) throws Exception{
        InputStream fis;
        byte[] buffer = new byte[1024];
        MessageDigest md5=null;
        try {
            fis = new FileInputStream(fileName);
            md5 = MessageDigest.getInstance("MD5");
            int numRead = 0;
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
        } catch (Exception e) {
            log.error("MD5Util get hash failure.", e);
            throw e;
        }
        String md5Value = toHexString(md5.digest());
        log.debug("MD5Util get hash is:" + md5Value);
        return md5Value;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length << 1);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String createCorrelateid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
