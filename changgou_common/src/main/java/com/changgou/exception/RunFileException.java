package com.changgou.exception;

/**
 * 文件传输管理异常
 * @author: hui.jin
 * @date: 2021/9/17 14:58
 */
public class RunFileException extends BaseException{

    private final static String message_temple = "[%s]文件管理失败";


    public RunFileException(String args) {
        super(String.format(message_temple, args));
    }

    public RunFileException(String args, Exception e) {
        super(String.format(message_temple, args), e);
    }
}
