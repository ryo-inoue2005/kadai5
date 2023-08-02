<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>誕生日入力画面</title>
</head>
<body>
	<h1>生年月日で今日の運勢を占います</h1>
	<h2>yyyyMMddの形式で生年月日を入力してください</h2>

	<html:errors />

	<html:form action="/result">
		<p>誕生日：<html:text property="birthday" /></p>
		<html:submit property="submit" value="送信" />
	</html:form>

</body>
</html:html>