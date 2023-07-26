<%@page import="bean.OmikujiBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<%
List<OmikujiBean> list = (List<OmikujiBean>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>過去半年の結果リスト画面</title>
</head>
<body>

	<h1><%=session.getAttribute("birthday")%>の過去半年の結果
	</h1>
	<table border="5">
		<tr>
			<th>占った日</th>
			<th>運勢</th>
			<th>願い事</th>
			<th>商い</th>
			<th>学問</th>
		</tr>
		<%
		for (OmikujiBean bean : list) {
		%>
		<tr>
			<td><%=bean.getCreateDate()%></td>
			<td><%=bean.getUnsei()%></td>
			<td><%=bean.getNegaigoto()%></td>
			<td><%=bean.getAkinai()%></td>
			<td><%=bean.getGakumon()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<a href="javascript:history.back();">戻る</a>
	<html:link action="/form">占いフォームに戻る</html:link>

</body>
</html:html>