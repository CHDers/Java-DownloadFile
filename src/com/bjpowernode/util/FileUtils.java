package com.bjpowernode.util;

import java.io.File;

public class FileUtils {
    public static long getFileContentLength(String path) {
        File file = new File(path);
        return file.exists() && file.isFile() ? file.length() : 0;
    }
}
