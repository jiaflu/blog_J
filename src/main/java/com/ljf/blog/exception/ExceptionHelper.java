package com.ljf.blog.exception;

import org.slf4j.Logger;
import com.ljf.blog.bo.RestResponse;

/**
 * Created by lujiafeng on 2018/9/18.
 */
public class ExceptionHelper {
    /**
     * 统一异常处理
     * @param logger
     * @param msg
     * @param e
     * @return
     */
    public static RestResponse handlerException(Logger logger, String msg, Exception e) {
        if (e instanceof TipException) {
            msg = e.getMessage();
        } else {
            logger.error(msg, e);
        }
        return RestResponse.fail(msg);
    }
}
