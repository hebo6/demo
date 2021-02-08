package com.example;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;

class HelloWorld {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1. 数据有丢失, 无法复原
        checkCharset("你好啊");
        //2. 数据无丢失
        checkCharset("你好");
        //2.1. 复原
        checkCharset("浣犲ソ", false);
    }

    public static void checkCharset(String source) throws UnsupportedEncodingException {
        checkCharset(source, true);
    }

    public static void checkCharset(String source, boolean utf8First) throws UnsupportedEncodingException {
        System.out.println();
        System.out.println(source + ":");

        String first = utf8First ? "UTF-8" : "GBK";
        String second = !utf8First ? "UTF-8" : "GBK";
        byte[] bytes = source.getBytes(first);

        for (int i = 0; i < 2; i++) {
            bytes = dealWithCharset(bytes, first);
            bytes = dealWithCharset(bytes, second);
        }
    }

    static byte[] dealWithCharset(byte[] source, String charsetName) {
        System.out.println("================ " + charsetName + " ================");
        String s = new String(source, Charset.forName(charsetName));
        byte[] target = s.getBytes(Charset.forName(charsetName));
        System.out.println("source = " + Arrays.toString(source));
        System.out.println("   str = " + s);
        return target;
    }
}