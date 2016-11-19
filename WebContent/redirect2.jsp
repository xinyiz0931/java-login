<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

Cookie[] cookies = request.getCookies();
if (null == cookies) {
	System.out.println("no cookies");
} else {
	Cookie cookie = new Cookie("username", null);
	cookie.setMaxAge(0);
	cookie.setPath("/");
	System.out.println("deleted value: " + cookie.getValue());
	response.addCookie(cookie);
	response.sendRedirect("index.jsp");
	
	
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>