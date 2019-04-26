<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title></title>
        <meta charset='UTF-8' />
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
        <script src="http://code.jquery.com/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
        <!-- Custom Stylesheet & Script -->
        <link rel='stylesheet' type='text/css' href='<%=request.getContextPath() %>/css/main.css' />
        <script src="<%=request.getContextPath() %>/js/jquery.qrcode.min.js"></script>
        <script src='<%=request.getContextPath() %>/js/main.js' ></script> 
    </head>
    <body class="">
        <div class="container">
            
            <!-- 세미나 방 정보 표시 -->
            <div class="column-1">
                <div class="box-5">
                    <div class="mobile-box-5-title-1">
                        <div>Our Question</div>
                        <div>
                            <img src="<%=request.getContextPath() %>/images/change-background-color-to-yellow.png" class="yellow-button" alt="Change the background color to yellow">
                            <img src="<%=request.getContextPath() %>/images/change-background-color-to-dark.png" class="dark-button" alt="Change the background color to dark">
                        </div>
                    </div>
                    <!-- 세미나 metadata 표시 (이름 & 인원 수)-->
                    <div class="box-5-col-1">
                        <div class="box-5-title-1">세미나 이름</div>
                        <div class="box-5-col-1-contents-1">
                            <div>${SeminarRoomDto.seminarTitle}</div>
                        </div>
                    </div>
                    <!-- URL 정보 표시  -->
                    <div class="box-5-col-2">
                        <div class="box-5-title-2">URL
                        </div>
                        <div class="box-5-col-1-contents-2">
                            <div class="url-address">${SeminarRoomDto.shortURL}</div>
                            <img src="<%=request.getContextPath() %>/images/url_copy_button.png" class="url-copy-button" alt="Copy button of URL">
                            <img src="<%=request.getContextPath() %>/images/Copy_interaction_2.gif" class="url-copy-animation" alt="url copy animation">
                        </div>
                    </div>
                    <!-- QR 코드 표시 -->
                    <div class="box-5-title-3">
                        <div class="show-qr-code-button">
                            <div class="show-qr-code">QR코드 보기</div>
                            <div class="mobile-show-qr-code">QR코드 보기</div>
                            <img src="<%=request.getContextPath() %>/images/qr-code-fold-button.png" class="qr-code-fold-button" alt="qr-code-fold-button">
                            <img src="<%=request.getContextPath() %>/images/qr-code-more-button.png" class="qr-code-more-button" alt="qr-code-more-button">
                        </div>
                        <div id="qrcode" class="box-qr-code"></div>
                    </div>
                </div>
            </div>

            <!-- 질문 랭킹 및 질문 코멘트 로그 -->
            <div class="column-2">

                <!-- 질문 랭킹 집계 (접힘) -->
                <div class="Question-ranking">
                    <div class="Question-ranking-row-1">Question Ranking</div>
                    <div class="more-button">
                        <img src="<%=request.getContextPath() %>/images/test.png" class="circle-button" alt="Question ranking fold and more button">
                    </div>
                    <div class="mobile-more-button">
                        <img src="<%=request.getContextPath() %>/images/test.png" class="mobile-circle-button" alt="Question ranking fold and more button">
                    </div>
                </div>
                <!-- 질문 랭킹 집계 (펴짐) -->
                <div class="Question-ranking-more">
                    <div class="top-1">
                        <div class="star-and-number">
                            <img src="<%=request.getContextPath() %>/images/one_star.png" alt="Star symbol">
                            <div>${rankingList[0].likes}</div>
                        </div>
                        <div class="ranking-text-rank-1 text-more">${rankingList[0].content}</div>
                        <span class="more-rank-1">More</span>
                        <span class="fold-rank-1">Fold</span>
                    </div>
                    <div class="top-2">
                        <div class="star-and-number">
                            <img src="<%=request.getContextPath() %>/images/one_star.png" alt="Star symbol">
                            <div>${rankingList[1].likes}</div>
                        </div>
                        <div class="ranking-text-rank-2 text-more">${rankingList[1].content}</div>
                        <span class="more-rank-2">More</span>
                        <span class="fold-rank-2">Fold</span>
                    </div>
                    <div class="top-3">
                        <div class="star-and-number">
                            <img src="<%=request.getContextPath() %>/images/one_star.png" alt="Star symbol">
                            <div>${rankingList[2].likes}</div>
                        </div>
                        <div class="ranking-text-rank-3 text-more">${rankingList[2].content}</div>
                        <span class="more-rank-3">More</span>
                        <span class="fold-rank-3">Fold</span>
                    </div>
                    <div class="fold-button">
                        <img src="<%=request.getContextPath() %>/images/question-ranking-fold.png" class="circle-button-2" alt="Question ranking fold and more button">
                    </div>
                    <div class="mobile-fold-button">
                        <img src="<%=request.getContextPath() %>/images/question-ranking-fold.png" class="mobile-circle-button-2" alt="Question ranking fold and more button">
                    </div>
                </div>

                <!-- 질문 코멘트 로그 -->
                <div class="question-contents">

                    <!-- 질문 로그 초기 메세지 -->
                    <div class="before-question-contents">
                        <img src="<%=request.getContextPath() %>/images/one_star.png" alt="Star symbol">
                        <div class="please-input-questions">질문을 입력해 주세요!</div>
                        <img src="<%=request.getContextPath() %>/images/one_star.png" alt="Star symbol">
                    </div>

                    <!-- 질문 코멘트 로그 -->
                    <ul>
                        <!-- allComments 리스트를 받아 질문 코멘트 출력 -->
                        <c:forEach items="${allComments}" var="comment">
                            <div data-commentId='<c:out value="${comment.commentId}"/>'>
                                <ol><c:out value="${comment.content}"/></ol>
                                <span class="comment-likes">
                                    <img src="<%=request.getContextPath() %>/images/white-star.png" class="yellow-star" alt="Button to recommend questions">
                                    <div><c:out value="${comment.likeCount}"/></div>
                                </span>
                            </div>
                        </c:forEach>
                    </ul>
                </div>

                <!-- 새 질문 등록 -->
                <div class="question-input">
                    <textarea type="text" placeholder="질문을 입력해주세요."></textarea>
                    <div class="input-send input-send-dim">Send</div>
                    <img class="mobile-input-send" src="<%=request.getContextPath() %>/images/mobile-send-button.png" alt="Send button">
                </div>
                <div id="blank"></div>
            </div>

            <!-- 페이지 색깔 변경 버튼 -->
            <div class="column-3">
                <div class="color-buttons">
                    <img src="<%=request.getContextPath() %>/images/change-background-color-to-yellow.png" class="yellow-button" alt="Change the background color to yellow">
                    <img src="<%=request.getContextPath() %>/images/change-background-color-to-dark.png" class="dark-button" alt="Change the background color to dark">
                </div>
            </div>

            <!-- QR 코드 모덜 -->
            <div class="modal">
                <div id="modalqrcode" class="modal-content"></div>
            </div>
        </div>
    </body>
</html>