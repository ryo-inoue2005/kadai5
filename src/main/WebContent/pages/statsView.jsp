<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title>過去半年の統計表示画面</title>
</head>
<body>

	<h1>過去半年全体と比べた今日の運勢結果の割合</h1>
	
	<!-- 運勢結果を表示 -->
	${sessionScope.disp}

	<logic:iterate id="data" name="statsForm" property="statsMap">
		<p>
			<bean:write name="data" property="key" />
			：
			<bean:write name="data" property="value" />
			%
		</p>
	</logic:iterate>

	<a href="javascript:history.back();">戻る</a>
	<html:link action="/form">占いフォームに戻る</html:link>

</body>
</html:html>