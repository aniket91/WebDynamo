<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Information Form</title>
<script>
 
function submitForm(){
    var formVar = document.forms["form"];
    formVar.action="/WebDynamo/submitEmployeeInfoForm.htm";
    formVar.submit();
}
 
</script>
<link rel="stylesheet" href="css/status.css"/>
</head>
<body>

	<c:if test="${status == 'success'}">
		<p class="success">Data saved successfully!</p>
	</c:if>
	<c:if test="${status == 'failure'}">
		<p class="error">All fields are mandatory. Age must be a positive value!</p>
	</c:if>
	<c:if test="${status == 'warning'}">
		<p class="warning">Data saved successfully. Watch your steps. 18+ only!</p>
	</c:if>
 	<c:if test="${status == 'info'}">
		<p class="info">Try adding age 18+, less than 18 and non integer values and notice the error statuses shown!</p>
	</c:if>
 
    <form:form id="form" name="form"
        method="post" commandName="employeeDetails">
         
        <table>
            <tr>
                <td>Employee Name<span style="color:red">*</span> : </td>
                <td>
                <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>Employee Age<span style="color:red">*</span> : </td>
                <td>
                <form:input path="age"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit" onclick="submitForm()"/>
         
    </form:form>
</body>
 
</html>