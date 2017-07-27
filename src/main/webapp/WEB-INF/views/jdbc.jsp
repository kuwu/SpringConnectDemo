<%--
  Created by IntelliJ IDEA.
  User: kuwu
  Date: 2017/07/26
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>JDBC DEMO</title>
</head>
<body>
<h1>Northwind DB results</h1>
<%--    --%>
<table>
    <c:forEach items="${dbResult}" var="list">
        <tr>
            <td><c:out value="${list}"></c:out></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
