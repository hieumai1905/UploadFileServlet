<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Upload file</title>
</head>
<body>
<h3>Upload File Single file</h3>
<form action="single-file" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <br><br>
    <input type="submit" value="Upload"/>
</form>
<hr>
<h3>Upload File Multiple file</h3>
<form action="multiple-file" method="post" enctype="multipart/form-data">
    <input type="file" name="file" multiple/>
    <br><br>
    <input type="submit" value="Upload"/>
</form>
<br>
<c:if test="${not empty message}">
    <p>${message}</p>
</c:if>
</body>
</html>