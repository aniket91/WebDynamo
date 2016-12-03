<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Firebase Cloud Messaging Sender</title>
<script>
 
function submitForm(){
    var formVar = document.forms["form"];
    formVar.action="/WebDynamo/submitFcmNotification.htm";
    formVar.submit();
}
 
</script>
<link rel="stylesheet" href="css/status.css"/>
</head>
<body>
	<c:if test="${status == 'success'}">
		<p class="success">FCM request sent successfully!</p>
		<p class="success">${output}</p>
	</c:if>
	<c:if test="${status == 'failure'}">
		<p class="error">Something went wrong!</p>
		<p class="error">${output}</p>
	</c:if>
	<c:if test="${status == 'warning'}">
		<p class="warning">All fields are mandatory!</p>
	</c:if>
    <form:form id="form" name="form"
        method="post" commandName="fcmConfig">
         
        <table>
            <tr>
                <td>Server Api Key<span style="color:red">*</span> : </td>
                <td>
                <form:input path="serverApiKey"/>
                </td>
            </tr>
            <tr>
                <td>FCM Registration Id<span style="color:red">*</span> : </td>
                <td>
                <form:input path="fmRegid"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit" onclick="submitForm()"/>
         
    </form:form>

</body>
</html>