<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Students</h1>
<form action="students" method="get">
    <button type="submit">show students</button>
</form>
<c:if test="${students ne null}">
    <table>
        <tr>
            <th>card number</th>
            <th>first name</th>
            <th>second name</th>
            <th>faculty</th>
            <th>course</th>
        </tr>
        <tr>
            <form action="students" method="post">
                <td><input type="text" name="cardNumber"></td>
                <td><input type="text" name="firstName"></td>
                <td><input type="text" name="secondName"></td>
                <td><input type="number" name="faculty"></td>
                <td><input type="number" name="course"></td>
                <td>
                    <button type="submit">add</button>
                </td>
            </form>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <form action="students/update" method="get">
                    <td><input type="text" name="cardNumber" value="${student.cardNumber}"></td>
                    <td><input type="text" name="firstName" value="${student.firstName}"></td>
                    <td><input type="text" name="secondName" value="${student.secondName}"></td>
                    <td><input type="number" name="faculty" value="${student.faculty}"></td>
                    <td><input type="number" name="course" value="${student.course}"></td>
                    <td><button type="submit">update</button></td>
                </form>
           <form action="students/delete" method="get">
               <td><button type="submit">delete</button></td>
           <input type="hidden" name="cardNumber" value="${student.cardNumber}">
           </form>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
