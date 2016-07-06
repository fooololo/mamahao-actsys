package com.mamahao.actsys.api.application.exception;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   12:12
 * Description    :		业务异常
 */
public class BizException extends Exception {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
