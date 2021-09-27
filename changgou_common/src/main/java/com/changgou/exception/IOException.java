package com.changgou.exception;

/**
 * 文件流传输异常
 * @author: hui.jin
 * @date: 2021/9/17 14:58
 */
public class IOException extends BaseException{

    private final static String message_temple = "[%s]IO流传输失败";


    public IOException(String args) {
        super(String.format(message_temple, args));
    }
}
