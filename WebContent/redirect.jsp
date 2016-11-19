<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
String username = null;
Cookie [] cookies = request.getCookies();


if (cookies != null) {
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("username")) {
			username = cookie.getValue();
		}
		
	}
	System.out.println("value:" + username);
	if (username == null) {
		response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("success.jsp");
		}
	 	
} 


/*

if (cookies != null) {
	for (Cookie cookie : cookies) {
		if(cookie.getName().equals("usename")) {
		System.out.println("deleted cookie name:" + cookie.getName() + ", value:" + cookie.getValue());
		cookie.setValue(null);
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
		}
	}
}

*/



		
	
		

 
%>