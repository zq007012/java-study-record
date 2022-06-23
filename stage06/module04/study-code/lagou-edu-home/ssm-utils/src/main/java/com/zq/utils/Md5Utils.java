package com.zq.utils;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

    public final static String MD5KEY = "zq";

    /**
     * 对字符串进行加盐操作后再进行md5加密, 返回加盐字符串md5加密后的值
     *
     * @param text 明文
     * @param key  密钥, 要加的盐
     * @return 密文
     */
    public static String md5(String text, String key) {
        //密钥不为空的话, 就对明文加盐
        if (!EmptyUtils.isEmpty(key)) {
            text = text + key;
        }

        //加密后的字符串
        return DigestUtils.md5Hex(text);
    }

    /**
     * 对字符串进行加盐操作后再进行md5加密, 验证获取到的值是否跟指定的md5密文匹配(忽略大小写)
     *
     * @param text 明文
     * @param key  密钥, 要加的盐
     * @param md5  密文
     * @return true/false
     */
    public static boolean verify(String text, String key, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }
}
