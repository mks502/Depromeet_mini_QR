<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html' charset='UTF-8' />
    <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
    <link rel='stylesheet' type='text/css' href='../css/style.css' />
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <!-- <script src="../js/index.js"></script> -->
    <script src="/Users/kimshinje/git/Depromeet_mini_QR/src/main/webapp/WEB-INF/js/index.js"></script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  GooooooooooooooooooooooooD The time on the server is ${serverTime}. </P>

 <!-- 새 세미나 방 등록 -->
  <div class="initial-box-1">
      <div class="seminar-title">세미나 이름</div>
      <form method="POST" action="/mini_QR/api/seminar/create">
          <input name="seminarTitle" type="text" placeholder="세미나 이름을 입력해 주세요" value="2222">
          <div class="under-line"></div>
          <div class="box-row">
          	<input type="submit" value="전송"/>
              <!-- <button type="submit" disabled class="create-room-button">만들기</button> -->
          </div>
      </form>
  </div>

</body>
</html>
