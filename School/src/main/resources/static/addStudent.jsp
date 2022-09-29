<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Add Student</title>
    </head>
    <body>
        <div style="text-align:center;">
        <h1><b>SCHOOL DASHBOARD</b></h1>
        <h2 style="">ADD STUDENT PORTAL</h2>
        <form:form action="/school/addStudent" method="post" modelAttribute="student">
            <form:label path="classno">ClassNo: </form:label> <form:input type="text" path="classno"/>
            <br>
            <form:label path="studentName">StudentName: </form:label> <form:input type="text" path="studentName"/>
            <br>
            <form:label path="mathsMarks">MathsMarks: </form:label> <form:input path="mathsMarks"/>
            <br>
            <form:label path="scienceMarks">ScienceMarks: </form:label> <form:input path="scienceMarks"/>
            <br>
            <input type="submit" value="SUBMIT"/>
        </form:form>
        <form action ="/school/getAllstudents">
             <input type="submit" value="SHOW DATA" >
             </form>
        </div>
    </body>
</html>