<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:if test="${ENTITY_TYPE ==  'XML'}">
    	<title>XML Beautifier</title>
	</c:if>
	<c:if test="${ENTITY_TYPE ==  'JSON'}">
		<title>Json Beautifier</title>
	</c:if>

<script>
 
function submitForm(){
    var formVar = document.forms["form"];
    <c:if test="${ENTITY_TYPE ==  'XML'}">
    	formVar.action="/WebDynamo/beautifyXml.htm";
	</c:if>
	<c:if test="${ENTITY_TYPE ==  'JSON'}">
	formVar.action="/WebDynamo/beautifyJson.htm";
	</c:if>
    
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
        method="post" commandName="unformattedStringForm">
      
        
        <h2><b>
     	<c:if test="${ENTITY_TYPE ==  'XML'}">
    	Enter you XML string below <span style="color:red">*</span> :
		</c:if>
		<c:if test="${ENTITY_TYPE ==  'JSON'}">
		Enter you Json string below <span style="color:red">*</span> :
		</c:if>       
        
        </b></h2>
        
        <c:if test="${error != null }">
        	 <p class="error">${error}</p>
        	 <br/>
        </c:if>
       
        <form:textarea path="unformattedString" rows="30"  placeholder="Enter your unformatted String here..."
        onfocus="this.placeholder = ''" 
        onblur="this.placeholder = 'Enter your unformatted String here...'"
        />
        
        <center>
        	<input type="submit" value="Beautify" onclick="submitForm()" style="font-size: 20px;background-color: #4CAF50;color: white;"/>
        </center>
         
    </form:form>

</body>
</html>