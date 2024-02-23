<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <title>Startsida</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.4.0/p5.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/All.css">
</head>
<body>
<%@ include file="NavBar.jsp" %>
<div style="height: 10px;"></div>
      <script>
      function setup() {
        createCanvas(windowWidth, windowHeight);
        noLoop();
      }

      function draw() {
        background(240);
        for (let i = 0; i < 10; i++) {
          fill(random(255), random(255), random(255), 100);
          noStroke();
          ellipse(random(width), random(height), random(20, 100));
        }
        textSize(32);
            fill(0);
            textAlign(CENTER, CENTER);
            text('Welcome to GritAcademy', width / 2, height / 2);
            textSize(16);
            text('This is the homepage, you can navigate through the navbar', width / 2, height / 2 + 40);
      }
    </script>
    <div class=afooter>
    <%@ include file="footer.jsp"%>
    </div>
</body>
</html>
