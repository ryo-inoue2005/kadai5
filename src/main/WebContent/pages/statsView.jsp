<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>過去半年の統計表示画面</title>
</head>
<body>

	<h1>過去半年全体と比べた今日の運勢結果の割合</h1>
	<p>
		<bean:write name="stats" />
		%
	</p>
	
	<a href="javascript:history.back();">戻る</a>
	<html:link action="/form">占いフォームに戻る</html:link>
	
</body>
</html:html>