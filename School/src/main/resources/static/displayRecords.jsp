  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Add Adhaar</title>
    </head>
    <body>

<table border="1" style="text-align:center;">
        <tr style="font-size: 13">
            <td>ID</td>
            <td>First name</td>
            <td>Last name</td>
            <td>Age</td>
            <td>Phone number</td>
            <td>Update</td>
        </tr>
        <c:forEach var="record" items="${records}">
            <tr style="font-size: 20">
                <td>${record.id}</td>
                <td>${record.studentName}</td>
                <td>${record.classno}</td>
                <td>${record.mathsMarks}</td>
                <td>${record.scienceMarks}</td>
                <td>${record.totalMarks}</td>
            </tr>
            </c:forEach>
    </table>
    </body>

 </html>