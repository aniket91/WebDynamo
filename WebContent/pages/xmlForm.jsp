<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XML Beautifier</title>
<script>
 
function submitForm(){
    var formVar = document.forms["form"];
    formVar.action="/WebDynamo/beautifyXml.htm";
    formVar.submit();
}
 
</script>
<style type="text/css">
	textarea
	{
	    overflow-y:scroll;
	    border:1px solid #999999;
	  	width:100%;
	}
</style>
<link rel="stylesheet" href="css/status.css"/>
</head>
<body>
	<form:form id="form" name="form"
        method="post" commandName="xmlForm">
      
        
        <h2><p><b>Enter you XML string below <span style="color:red">*</span> :</b></p></h2>
        <c:if test="${error != null }">
        	 <p class="error">${error}</p>
        	 <br/>
        </c:if>
       
        <form:textarea path="xml" rows="30"  placeholder="Enter your XML String here..."
        onfocus="this.placeholder = ''" 
        onblur="this.placeholder = 'Enter your XML String here...'"
        />
        
        <center>
        	<input type="submit" value="Beautify" onclick="submitForm()"/>
        </center>
         
    </form:form>

</body>
</html>