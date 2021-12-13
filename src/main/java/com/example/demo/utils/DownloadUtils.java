package com.example.demo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DownloadUtils {
    private final static String filenamePrefix = "UTF-8''";

    /**
     * 下载文件能正常显示中文
     */
    public static void download(byte[] bytes, String filename, HttpServletResponse response) throws IOException {
        setDownloadHeader(response, filename);
        response.getOutputStream().write(bytes);
    }

    public static void setDownloadHeader(HttpServletResponse response, String filename) {
        if (filename.startsWith(filenamePrefix)) {
            filename = filename.substring(filenamePrefix.length());
        }
        //URLEncoder: 防止中文文件名乱码
        response.setHeader("Content-Disposition", "attachment; filename="
                + URLEncoder.encode(filename, StandardCharsets.UTF_8) + "; filename*=" + filenamePrefix + URLEncoder.encode(filename, StandardCharsets.UTF_8));
    }
}
