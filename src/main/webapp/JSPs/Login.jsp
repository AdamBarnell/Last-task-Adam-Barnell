<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logga in</title>
    <link rel="stylesheet" type="text/css" href="/All.css">
</head>
<body>
    <h1>Logga in</h1>
    <form action="/login" method="post">
        Användarnamn: <input type="text" name="username" required/><br />
        Lösenord: <input type="password" name="password" required/><br />
        <input type="submit" value="Logga in" class=backbutton/>
    </form>
    <form action="/login" method="post">
        <input type="hidden" name="anonymous" value="true"/>
        <input type="submit" value="Fortsätt som Anonym" class=backbutton/>
    </form>
    <div style="height: 250px;"></div>
    <%@ include file="footer.jsp" %>
</body>
</html>
