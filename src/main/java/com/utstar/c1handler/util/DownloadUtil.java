package com.utstar.c1handler.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

public class DownloadUtil {

    private static final Logger log = LoggerFactory.getLogger(DownloadUtil.class);
    public static int downloadCommandReqFile(String cmdFileURL, String localFile) {
        URL url;
        InputStream is = null;
        FileOutputStream os = null;
        try {
            url = new URL(cmdFileURL);
            is = url.openStream();

            long size = 0;
            java.io.File outfile = new java.io.File(localFile);
            os = new FileOutputStream(outfile);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = is.read(bytes)) != -1) {
                os.write(bytes, 0, c);
                size = size + c;
            }

            is.close();
            os.close();

            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 文件拷贝的方法
     */
    public static void fileCopy(String src, String des) {

        try (InputStream is = new FileInputStream(src);
             OutputStream os = new FileOutputStream(des);) {
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                os.write(b);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

    }
}
