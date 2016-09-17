<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
</head>
<body>
 
    <form:form id="form" name="form"
        method="post" commandName="employeeDetails">
         
        <table>
            <tr>
                <td>Employee Name : </td>
                <td>
                <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>Employee Age : </td>
                <td>
                <form:input path="age"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Submit" onclick="submitForm()"/>
         
    </form:form>
</body>
 
</html>