package com.bjpowernode.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/*
http相关工具类
 */
public class HttpUtils {
    /**
     * 获取HttpURLConnection链接对象
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        // 向文件所在服务器发送标识信息
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36");
        return httpURLConnection;
    }

    /**
     * 获取下载文件的名字
     *
     * @param url
     * @return
     */
    public static String getHttpFileName(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }
}
