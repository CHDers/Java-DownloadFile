package com.bjpowernode.core;

import com.bjpowernode.constant.Constant;
import com.bjpowernode.util.FileUtils;
import com.bjpowernode.util.HttpUtils;
import com.bjpowernode.util.LogUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 下载器
 */
public class DownLoader {
    public ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public void download(String url) {
        // 获取文件名
        String httpFileName = HttpUtils.getHttpFileName(url);
        // 文件下载路径
        httpFileName = Constant.PATH + httpFileName;

        // 获取本地文件的大小
        long localFileLength = FileUtils.getFileContentLength(httpFileName);

        // 获取连接对象
        HttpURLConnection httpURLConnection = null;
        DownloadInfoThread downloadInfoThread = null;
        try {
            httpURLConnection = HttpUtils.getHttpURLConnection(url);

            // 获取下载文件的总大小
            int contentLength = httpURLConnection.getContentLength();

            // 判断文件是否已下载过
            if (localFileLength >= contentLength) {
                LogUtils.info("{}已经下载完毕", httpFileName);
                return;
            }

            // 创建获取下载信息的任务对象
            downloadInfoThread = new DownloadInfoThread(contentLength);
            // 将任务交给线程执行，每隔一秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(downloadInfoThread, 1, 1, TimeUnit.SECONDS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                InputStream input = httpURLConnection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(input);
                FileOutputStream fos = new FileOutputStream(httpFileName);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
        ) {
            int len = -1;
            byte[] buffer = new byte[Constant.BYTE_SIZE];
            while ((len = bis.read(buffer)) != -1) {
                downloadInfoThread.downSize += len;
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            LogUtils.error("下载的文件不存在{}", url);
        } catch (IOException e) {
            LogUtils.error("下载失败");
        } finally {
            System.out.print("\r");
            System.out.print("下载完成");
            // 关闭连接对象
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            // 关闭
            scheduledExecutorService.shutdownNow();
        }
    }
}
