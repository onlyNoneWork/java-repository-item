package com.changgou.exception;

/**
 * @author: hui.jin
 * @date: 2021/9/17 14:55
 */
public abstract class BaseException extends RuntimeException{


    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, Throwable cause){
        super(message, cause);
    }
}
