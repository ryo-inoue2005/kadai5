<%@page import="dto.PastListDto"%>
<%@page import="java.util.List"%>
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
<title>過去半年の結果リスト画面</title>
</head>
<body>



	<h1>${sessionScope.birthday}の過去半年の結果</h1>

	<table border="5">
		<tr>
			<th>占った日</th>
			<th>運勢</th>
			<th>願い事</th>
			<th>商い</th>
			<th>学問</th>
		</tr>

		<logic:iterate id="data" name="pastListForm" property="pastList"
			type="PastListDto">
			<tr>
				<td><bean:write name="data" property="createDate" /></td>
				<td><bean:write name="data" property="unsei" /></td>
				<td><bean:write name="data" property="negaigoto" /></td>
				<td><bean:write name="data" property="akinai" /></td>
				<td><bean:write name="data" property="gakumon" /></td>
			</tr>
		</logic:iterate>
	</table>

	<a href="javascript:history.back();">戻る</a>
	<html:link action="/form">占いフォームに戻る</html:link>

</body>
</html:html>