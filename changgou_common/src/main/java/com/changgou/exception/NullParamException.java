package com.changgou.exception;

/**
 * 空参错误提示
 * @author: hui.jin
 * @date: 2021/9/17 14:58
 */
public class NullParamException extends BaseException{

    private final static String message_temple = "参数[%s]不能为空";


    public NullParamException(String args) {
        super(String.format(message_temple, args));
    }
}
