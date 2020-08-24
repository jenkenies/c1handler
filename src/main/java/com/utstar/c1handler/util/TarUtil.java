package com.utstar.c1handler.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;

public class TarUtil {
    private TarUtil() {

    }

    public static void doDecompress(File file, String path) throws IOException {
        try (InputStream inputStream = new FileInputStream(file);
             TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(inputStream)) {
            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
                String dir = path + File.separator + entry.getName();
                File dirFile = new File(dir);
                mkdirFiles(dirFile);
                if (entry.isDirectory()) {
                    dirFile.mkdirs();
                } else {
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(dirFile);
                        int read = tarArchiveInputStream.read();
                        while (read != -1) {
                            fileOutputStream.write(read);
                            read = tarArchiveInputStream.read();
                        }
                    } catch (IOException ignore) {
                    } finally {
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public static void mkdirFiles(File dirFile) {
        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {
            mkdirFiles(parentFile);
            parentFile.mkdir();
        }
    }
}
