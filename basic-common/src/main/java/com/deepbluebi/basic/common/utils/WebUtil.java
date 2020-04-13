package com.deepbluebi.basic.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class WebUtil {

	public static String getFileAttachment(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {

		// 默认IE浏览器
		String attachment = "filename=\"" + fileName + "\"";

		String userAgent = request.getHeader("User-Agent");
		if (StringUtils.isNotBlank(userAgent)) {
			userAgent = userAgent.toLowerCase();
			// IE浏览器，只能采用URLEncoder编码
			if (userAgent.indexOf("msie") != -1) {
				attachment = "filename=\"" + fileName + "\"";
			}
			// Opera浏览器只能采用filename*
			else if (userAgent.indexOf("opera") != -1) {
				attachment = "filename*=UTF-8''" + fileName;
			}
			// Safari浏览器，只能采用ISO编码的中文输出
			else if (userAgent.indexOf("safari") != -1) {
				attachment = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
			else if (userAgent.indexOf("applewebkit") != -1) {
				fileName = MimeUtility.encodeText(fileName, "UTF8", "B");
				attachment = "filename=\"" + fileName + "\"";
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			else if (userAgent.indexOf("mozilla") != -1) {
				attachment = "filename*=UTF-8''" + fileName;
			}
		}
		return attachment;
	}

}
