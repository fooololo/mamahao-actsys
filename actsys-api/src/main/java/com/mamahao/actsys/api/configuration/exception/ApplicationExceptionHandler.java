package com.mamahao.actsys.api.configuration.exception;

import com.mamahao.actsys.api.core.exception.BizException;
import com.mamahao.actsys.api.core.exception.IllegalParamException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   16:24
 * Description    :
 */
@ControllerAdvice
public class ApplicationExceptionHandler {
	/**
	 * 业务级别错误处理
	 * @param request
	 * @param bizex
	 * @return
	 */
	@ExceptionHandler(value = BizException.class)
	public ResponseEntity bizExceptionHandler(HttpServletRequest request,BizException bizex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bizex);
	}

	/**
	 * 参数合法性级别错误处理
	 * @param request
	 * @param illex
	 * @return
	 */
	@ExceptionHandler(value = IllegalParamException.class)
	public ResponseEntity IllExceptionHandler(HttpServletRequest request,IllegalParamException illex){
		return ResponseEntity.badRequest().body(illex);
	}

	/**
	 * jvm级别错误处理
	 * @param request
	 * @param t
     * @return
     */
	@ExceptionHandler(value = Throwable.class)
	public ResponseEntity throwableHandler(HttpServletRequest request,Throwable t){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t);
	}


	/**
	 * 运行时异常错误处理
	 * @param request
	 * @param rtex
	 * @return
	 */
	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity rtExceptionHandler(HttpServletRequest request,RuntimeException rtex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rtex);
	}

	/**
	 * 检查异常错误处理
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity exceptionHandler(HttpServletRequest request,Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
	}
}
