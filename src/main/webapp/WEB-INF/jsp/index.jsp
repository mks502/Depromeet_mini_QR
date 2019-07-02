<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta charset='UTF-8' />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
        <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/css/index.css' />
        <script src="http://code.jquery.com/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/index.js"></script>
    </head>
    <body>
        <div class="container">

            <!-- 페이지 제목 및 버튼 -->
            <div class="initial-col-1">
                <div class="initial-col-1-1">
                    <div class="question-ranking-text">Our Question</div>
                    <div class="initial-background-button">
                        <img src="<%=request.getContextPath()%>/images/change-background-color-to-yellow.png" class="yellow-button" alt="Change the background color to yellow">
                        <img src="<%=request.getContextPath()%>/images/change-background-color-to-dark.png" class="dark-button" alt="Change the background color to dark">
                    </div>
                </div>
            </div>

            <!-- 새 세미나 방 등록 -->
            <div class="initial-col-2">
                <div class="initial-box-1">
                    <div class="seminar-title">세미나 이름</div>
                    <form method="POST" action="<%=request.getContextPath() %>/api/seminar/create" accept-charset="UTF-8">
                        <input name="seminarTitle" type="text" placeholder="세미나 이름을 입력해 주세요">
                        <div class="under-line"></div>
                        <div class="box-row">
                            <button type="submit" disabled class="create-room-button">만들기</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 페이지 하단 공간 -->
            <div class="initial-col-3">
            </div>
        </div>
    </body>
</html>