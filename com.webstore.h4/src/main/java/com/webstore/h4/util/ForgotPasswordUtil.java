package com.webstore.h4.util;

import javax.servlet.http.HttpServletRequest;

public class ForgotPasswordUtil {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
		
	}
}
