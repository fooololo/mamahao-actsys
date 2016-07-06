package com.mamahao.actsys.api.application.exception;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   16:20
 * Description    :	 	非法参数异常
 */
public class IllegalParamException extends IllegalArgumentException {
    public IllegalParamException() {
        super();
    }

    public IllegalParamException(String s) {
        super(s);
    }

    public IllegalParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParamException(Throwable cause) {
        super(cause);
    }
}
