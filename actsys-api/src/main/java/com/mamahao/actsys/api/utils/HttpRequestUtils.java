package com.mamahao.actsys.api.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   13:48
 * Description    :
 */
public class HttpRequestUtils {
	public static String parseOrigin(){
		HttpServletRequest request = getCurrentRequest();
		String origin = request.getHeader("Origin");
		if(StringUtils.isBlank(origin)){
			origin = request.getHeader("origin");
		}
		if(StringUtils.isBlank(origin)){
			origin = request.getHeader("host");
		}
		return origin;
	}

	public static String parseUA() {
		HttpServletRequest request = getCurrentRequest();
		String ua = request.getHeader("User-Agent");
		if(StringUtils.isBlank(ua)){
			ua = request.getHeader("user-agent");
		}
		return ua;
	}

	public static HttpServletRequest getCurrentRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(request == null){
			throw new RuntimeException("Request not found");
		}
		return request;
	}
}
