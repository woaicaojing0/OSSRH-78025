package com.deepbluebi.basic.config.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroFilterUtils {
	private static final Logger logger = LoggerFactory.getLogger(ShiroFilterUtils.class);

	/**
	 * 
	 * @描述：判断请求是否是ajax
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request) {
		String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			logger.debug("shiro工具类【wyait-manager-->ShiroFilterUtils.isAjax】当前请求,为Ajax请求");
			return Boolean.TRUE;
		}
		logger.debug("shiro工具类【wyait-manager-->ShiroFilterUtils.isAjax】当前请求,非Ajax请求");
		return Boolean.FALSE;
	}

	/**
	 *      * response 设置超时      * @param hresponse      * @param resultMap     
	 * * @throws IOException     
	 */
	public static void setSessionTimeOut(ServletResponse servletResponse, String msg) {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setCharacterEncoding("UTF-8");
		// 在响应头设置session状态
		response.setHeader("session-status", msg);
	}

}
