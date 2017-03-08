<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="org.owasp.esapi.ESAPI" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Text XSS page</title>
<script>
function submitForm(){
	var form = document.createElement("form");
	form.setAttribute("method", "GET");
	form.setAttribute("action", "testXss.htm");
	document.body.appendChild(form);
	form.submit();
}

function submitWithXssForm(){
    var formVar = document.forms["form"];
    formVar.action="testXss.htm";
    document.getElementById("xssProtect").value="no";
    formVar.submit();
}

function submitWithXssProtectForm(){
    var formVar = document.forms["form"];
    formVar.action="testXssProtect.htm";
    document.getElementById("xssProtect").value="yes";
    formVar.submit();
}

</script>
</head>
<body>
	
	<%
	String query = request.getParameter("q");
	if(query == null || query.length() == 0) {
	%>
	<form id="form" name="form" method="post" action="testXss.htm">
		<table>
			<tr>
				<td>Query : <span style="color: red">*</span> :
				</td>
				<td><input type="text" name="q" /></td>
				<td><input type="submit" value="Search" onclick="submitWithXssForm()"/></td>
				<td><input type="submit" value="Search Xss Protect" onclick="submitWithXssProtectForm()"/></td>
			</tr>
			<tr>
			<td colspan="3">
			<i><b>NOTE : </b> Try adding &lt;script&gt;alert(10)&lt;/script&gt; in search box above</i>
			</td>
			</tr>
		</table>
		<input type="hidden" name="xssProtect" id="xssProtect"/>	
	</form>
	
	<%
	}
	else {
		String xssProtect = request.getParameter("xssProtect");
		if(xssProtect != null && xssProtect.equalsIgnoreCase("yes")) {
			query = ESAPI.encoder().encodeForJavaScript(query);
		}
	%>
	Your input : <%=query %>
	<button onclick="submitForm()">Search Again</button>
	<%
	}
	%>

</body>
</html>