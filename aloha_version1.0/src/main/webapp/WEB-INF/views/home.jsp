<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P>	 <c:forEach items="${users}" var="element"> 
  <tr>
    <td>${element}</td>
  </tr>
</c:forEach>
</P>

<p> <a href="friends/">Friends List</a><br/>
<a href="Login.jsp">Login</a>
		</p>
       </jsp:body>


