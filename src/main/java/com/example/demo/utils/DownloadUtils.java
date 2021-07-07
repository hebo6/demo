package com.example.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DownloadUtils {
    private DownloadUtils() {
    }

    /**
     * 下载文件能正常显示中文
     */
    public static void download(HttpServletResponse response, String fileName, byte[] bytes) throws IOException {
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8)
                        + "; filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.getOutputStream().write(bytes);
    }
}
