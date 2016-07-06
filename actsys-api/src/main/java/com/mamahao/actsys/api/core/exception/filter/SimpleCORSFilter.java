package com.mamahao.actsys.api.core.exception.filter;

import com.mamahao.actsys.api.utils.HttpRequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Company        :   mamahao.com
 * author         :   guxiaolong
 * Date           :   2016/5/31
 * Time           :   13:43
 * Description    :
 */
@Component
public class SimpleCORSFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		String origin = HttpRequestUtils.parseOrigin();
		System.out.println("origin>>>" + origin);
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		response.setHeader("Access-Control-Allow-Origin", origin);
//		response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
//		response.setHeader("Access-Control-Allow-Headers", "*");
		filterChain.doFilter(request,response);
	}
}
