<%@page import="omikuji.Omikuji"%>
<%@page import="omikuji.Fortune"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<%
// リクエストスコープからのデータの取得
Omikuji omikuji = (Omikuji) request.getAttribute("omikuji");
%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>結果表示画面</title>
</head>
<body>

	<h1>運勢結果</h1>

	<%
	if (omikuji != null) {
	%>
	<p><%=omikuji.disp()%></p>
	<p>
		願い事：<%=omikuji.getNegaigoto()%></p>
	<p>
		商い：<%=omikuji.getAkinai()%></p>
	<p>
		学問：<%=omikuji.getGakumon()%></p>

	<html:link action="/form">占いフォームに戻る</html:link>
	<html:link action="/pastList">過去のおみくじ結果を見る</html:link>
	<html:link action="/stats">統計を見る</html:link>

	<%} else {%>
	<p>エラーです</p>
	<html:link action="/form">占いフォームに戻る</html:link>
	<%
	}
	%>


</body>
</html:html>