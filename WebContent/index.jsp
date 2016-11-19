<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン</title>
<script type="text/javascript">

function changeImg() 
{
	document.getElementById("validateCodeImg").src="${pageContext.request.contextPath}/CheckCodeImage?" + Math.random();
}

</script>
</head>
<body>
<form action="ServletLogin">
    <table border="0" align="center">
   		<tr height="30"></tr>
        <tr align="center">
            <td colspan="2"><font size="20">JAVA WEB</font></td>
        </tr>
        <tr height="30"></tr>
        <tr height="50">
            <td>ユーザネーム</td>
            <td><input type="text" name="username" placeholder="名前を入力してください"></td>
        </tr>
        <tr height="50">
            <td>パスワード</td>
            <td><input type="password" name="password" placeholder="パスワードを入力してください"></td>
        </tr>

        <tr height="50">
            <td><img alt="認めない" src="${pageContext.request.contextPath}/CheckCodeImage" 
            id="validateCodeImg" onclick="changeImg()">
            </td>
            <td><input type="text" name="checkcode" placeholder="右側の文字を入力してください"></td>
        </tr>
        <tr height="50">
            <td colspan="2" align="center">
                <input type="submit" name="login" value="ログイン">
            </td>
        </tr>
    </table>
</form>

</body>
</html>