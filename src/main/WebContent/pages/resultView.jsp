<%@page import="omikuji.Omikuji"%>
<%@page import="omikuji.Fortune"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>結果表示画面</title>
</head>
<body>

	<h1>運勢結果</h1>
	
	<p><bean:write name="resultForm" property="disp" scope="request" /></p>
	<p>願い事：<bean:write name="resultForm" property="negaigoto" scope="request" /></p>
	<p>商い：<bean:write name="resultForm" property="akinai" scope="request" /></p>
	<p>学問：<bean:write name="resultForm" property="gakumon" scope="request" /></p>

	<html:link action="/form">占いフォームに戻る</html:link>
	<html:link action="/pastList">過去のおみくじ結果を見る</html:link>
	<html:link action="/stats">統計を見る</html:link>


</body>
</html:html>