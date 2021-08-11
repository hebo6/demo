package com.example.demo.http;

import com.example.demo.utils.DownloadUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("download")
public class DownloadController {
    @GetMapping
    public void downloadChineseFileName(HttpServletResponse response) throws IOException {
        String fileName = "我是中文名.txt";
        byte[] bytes = "hello，我是内容！啊啊啊".getBytes(StandardCharsets.UTF_8);
        DownloadUtils.download(bytes, fileName, response);
    }
}
