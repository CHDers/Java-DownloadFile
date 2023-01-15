package com.bjpowernode;

import com.bjpowernode.core.DownLoader;
import com.bjpowernode.util.LogUtils;

import java.util.Scanner;

/**
主类
 */
public class Main {
    public static void main(String[] args) {
        // 下载地址
        String url = null;
        if (args == null || args.length == 0) {
            for (; ; ) {
                LogUtils.info("请输入下载地址");
                Scanner scanner = new Scanner(System.in);
                url = scanner.next();
                if (url != null) {
                    break;
                }
            }
        } else {
            url = args[0];
        }
        DownLoader downLoader = new DownLoader();
        // url = "https://dldir1.qq.com/qqfile/qq/PCQQ9.6.8/QQ9.6.8.28823.exe";
        downLoader.download(url);
    }
}
