package com.ljf.blog.exception;

/**
 * Created by lujiafeng on 2018/9/13.
 */
public class TipException extends RuntimeException {
    public TipException() {}

    public TipException(String messsage) { super(messsage); }

    public TipException(Throwable cause) { super(cause); }

    public TipException(String messsage, Throwable cause) { super(messsage, cause); }
}
