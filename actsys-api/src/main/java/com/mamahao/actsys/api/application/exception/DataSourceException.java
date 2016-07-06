package com.mamahao.actsys.api.application.exception;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/30
 * Time           :   14:22
 * Description    :
 */
public class DataSourceException extends RuntimeException {

	public DataSourceException(String message) {
		super(message);
	}

	public DataSourceException() {
		super();
	}

	public DataSourceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}
}
