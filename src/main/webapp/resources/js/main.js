// 웹 소켓 기본 설정
let stompClient = null;
let paths = window.location.pathname.split("/");
let seminarId = paths[paths.length - 1];

// 별 아이콘에 색깔 변경 및 like 개수 변경 기능 추가
const addChangeLike = (star) => {
    const $starImg = star;

    // 웹소켓을 통해 서버로 보낼 JSON 메세지 작성
    const $commentDiv = $starImg.parent().parent();
    const $commentId = $commentDiv[0].dataset.commentid;
    const message = JSON.stringify({'seminarId': seminarId, 'commentId': $commentId});
    
    // like를 의미하는 별 아이콘 클릭 시, 색깔 변경 및 like 개수 업데이트
    $starImg.click(() => {
        // like를 선택했을 경우,
        if ($starImg.hasClass('yellow-star')) {
            // 인터랙션 실행
            $starImg.attr('src', '/mini_QR/images/star_interaction_final.gif');
            setTimeout(() => {
                $starImg.attr('src', '/mini_QR/images/one_star.png');
            }, 300);
            $starImg.toggleClass('yellow-star');

            // 웹소켓을 통해 서버로 like 상태 변경 전달
            console.log("like 합니다: ", message);
            stompClient.send("/like", {}, message);
        } 
        // unlike를 선택했을 경우,
        else {
            // 인터랙션 실행
            $starImg.attr('src', '/mini_QR/images/white-star.png');
            $starImg.toggleClass('yellow-star');

            // 웹소켓을 통해 서버로 like 상태 변경 전달
            console.log("unlike 합니다: ", message);
            stompClient.send("/unlike", {}, message);
        }
    });
};


// 색깔 버튼 클릭 시, 배경 색깔 변경
const changeBackgroundColor = () => {
    const $yellowButton = $('.yellow-button');
    const $darkButton = $('.dark-button');
    const $body = $('body');
    const $blank = $('#blank');
    const $initialBody = $('#initial-body');
    const $questionRankingText = $('.question-ranking-text');
    const $pleaseInputQuestions = $('.please-input-questions');
    const $modal = $('.modal');
    const $modalContents = $('.modal-content');

    $yellowButton.click(() => {
        $body.removeClass('dark-version');
        $body.css({
            'background-color': '#f1f1f1'
        });
        $blank.css({
            'background-color': '#f1f1f1'
        });
        $initialBody.css({
            'background-color': '#feb519'
        })
        $questionRankingText.css({
            'color': 'rgba(0, 0, 0, 0.87)'
        })
        $pleaseInputQuestions.css({
            'color': 'rgba(0, 0, 0, 0.87)'
        })
        $modal.css({
            'background-color': 'rgba(0, 0, 0, 0.54)',
            'border': '1px solid #888'
        })
        $modalContents.css({
            'border': '1px solid #888'
        })
        $('span > div').removeClass('dark-version');
    });

    $darkButton.click(() => {
        $body.addClass('dark-version');
        $body.css({
            'background-color': '#363a43'
        });
        $blank.css({
            'background-color': '#363a43'
        });
        $initialBody.css({
            'background-color': '#363a43'
        })
        $questionRankingText.css({
            'color': 'rgba(255, 255, 255, 0.87)'
        })
        $pleaseInputQuestions.css({
            'color': 'rgba(255, 255, 255, 0.87)'
        })
        $modal.css({
            'background-color': 'rgba(255, 255, 255, 0.72)',
        })
        $modalContents.css({
            'border': '0'
        })
        $('span > div').addClass('dark-version');
    });
};

// 웹소켓 연결하기
const connectWebSockets = () => {
    let socket = new SockJS('/mini_QR/q-rank-websock');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        console.log('소켓 연결되었습니다!');

        // 서버로부터 STOMP 메세지를 전달받으면, 콘텐츠 업데이트
        stompClient.subscribe(`/subscribe/seminar/${seminarId}`, (res) => {

            console.log("메세지 도착: ", res);
            // JSON response 파싱
            const data = JSON.parse(res.body);
            console.log("메세지 파싱: ", data);

            // 메세지가 종류가 새 질문 업데이트이면, 
            if (data.type === "comment") {
                // 새 질문 화면에 표시
                console.log("질문을 업데이트...")
                postNewQuestion(data);
            } 
            // 메세지가 like로 상태 업데이트면,
            else if (data.type === "like" || data.type === "unlike") {
                // 해당 질문의 like 개수 변경
                console.log("like 개수를 업데이트...")
                updateLikeCount(data);
            }
            // 메세지가 랭킹 업데이트면,
            else if (data.type === "ranking") {
                console.log("랭킹 순위 업데이트...")
                updateRanking(data);
            }
        });
    });
};

// URL 복사 아이콘 클릭 시, URL을 클립보드에 복사
const copyURL = () => {
    const $urlAdress = $('.url-address');
    const $urlContents = $('.box-5-col-1-contents-2');

    if ($urlAdress.text().length < 21) {
        $urlContents.css({
            'height': '24px'
        });
    };
  
    $('.box-5-col-1-contents-2 > img').click(() => {
        var $temp = $("<input>");
        $("body").append($temp);
        $temp.val($('.url-address').text()).select();
        document.execCommand("copy");
        $temp.remove();

        $('.url-copy-button').hide();
        $('.url-copy-animation').show();
        setTimeout(() => {
            $('.url-copy-animation').hide();
            $('.url-copy-button').show();
        }, 500);
    });
};

// 새 질문 텍스트 입력 시, 보내기 버튼 활성
const enableOrDisableSendButton = () => {
    const $inputButton = $('.input-send');
    const $mobileInputButton = $('.mobile-input-send');
    const $textarea = $('textarea');
  
    $textarea.keyup(() => {
        // 새 질문 입력 텍스트가 없다면, 보내기 버튼 비활성
        if ($textarea.val() === '') {
            $inputButton.addClass('input-send-dim');
            $mobileInputButton.removeClass('mobile-input-send-dim');
        // 텍스트 입력 시, 보내기 버튼 활성
        } else {
            $inputButton.removeClass('input-send-dim');
            $mobileInputButton.addClass('mobile-input-send-dim');
        }
    });
};

// 새 질문 업데이트
const postNewQuestion = (message) => {
    const comment = message.comment;
    const commentText = comment.content;
    const commentId = comment.commentId;

    const $ul = $('ul');
    const $inputButton = $('.input-send');
    const $mobileInputButton = $('.mobile-input-send');
    const $beforeQuestionInput = $('.before-question-contents')
    const $textarea = $('textarea');

    // 새 질문 올리기
    $ul.append(`<div data-commentId=${commentId}><ol></ol><span class="comment-likes"></span></div>`);
    $('ol:last').append(commentText);
    $('span:last').append('<img src="/mini_QR/images/white-star.png" class="yellow-star" alt="Button to recommend questions"><div>0</div>')
    $('span:last > img').addClass('white-star');
    $('span:last > div').addClass($('body').attr('class'));

    // 새 질문 입력 다시 디폴트 설정으로 변경
    $textarea.val('')
    $inputButton.addClass('input-send-dim');
    $mobileInputButton.removeClass('mobile-input-send-dim');
    $beforeQuestionInput.hide();

    // like 별 아이콘 상태 변경 기능 추가
    const $img = $('span:last > img');
    addChangeLike($img);
};

// 웹소켓을 통해 서버에게 새 질문 전달
const sendNewQuestion = () => {
    const content = document.querySelector("textarea").value;
    const message = JSON.stringify({'seminarId': seminarId, 'content': content});
    console.log("데이터 전송합니다: ", message);
    stompClient.send("/updates", {}, message);
};

// 랭킹 버튼 클릭 시, 랭킹 순위 공개 및 숨김
const showOrFoldRanking = (foldedHeight) => {
    const $questionRanking = $('.Question-ranking');
    const $questionRankingMore = $('.Question-ranking-more');
    const $questionContents = $('.question-contents');
    const $foldButton = $('.fold-button');
    const $moreButton = $('.more-button');

    $questionRankingMore.hide();
    $foldButton.hide();

    if ($('.ranking-text-rank-1').text() === '') {
        $('.circle-button').attr('src', '/mini_QR/images/more-button-dim.png');
    } else {
        $moreButton.click(() => {
            $questionRanking.hide();
            $questionRankingMore.show();
            $foldButton.show();
            $questionContents.css({
                'height': parseInt(Math.max(document.documentElement.clientHeight, window.innerHeight || 0)-parseInt($('.Question-ranking-more').css('height').replace('px', ''))-172)+"px"
            });
        });
    
        $foldButton.click(() => {
            $questionRanking.show();
            $questionRankingMore.hide();
            $foldButton.hide();
            $questionContents.css({
                'height': foldedHeight,
            });
        });
    }
};

// 모바일 화면에서 랭킹 버튼 클릭 시, 랭킹 순위 공개 및 숨김
const showOrFoldRankingMobile = (foldedHeight, strechedHeight) => {
    const $questionRanking = $('.Question-ranking');
    const $questionRankingMore = $('.Question-ranking-more');
    const $questionContents = $('.question-contents');
    const $circleButton = $('.mobile-circle-button');
    const $secondCircleButton = $('.mobile-circle-button-2')

    if ($('.ranking-text-rank-1').text() === '') {
        $('.mobile-circle-button').attr('src', '/mini_QR/images/more-button-dim.png');
    } else {
        $circleButton.click(() => {
            $questionRanking.hide();
            $questionRankingMore.show();
            $questionContents.css({
                'height': strechedHeight,
            });
        });
    
        $secondCircleButton.click(() => {
            $questionRanking.show();
            $questionRankingMore.hide();
            $questionContents.css({
                'height': foldedHeight,
            });
        });
    }
};

// 랭킹 Top 3 질문 내의 More 버튼 클릭 시, 글 공개 및 숨김
const showOrFoldRankingText = () => {

    const currentHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    for (var i = 0; i < 4; i++) {
        if ($('.ranking-text-rank-'+i).text().length < 172) {
            $('.more-rank-'+i).hide();
        }
    }
    
    $('.fold-rank-'+1).hide();

    $('.more-rank-'+1).click(() => {
        $('.ranking-text-rank-'+1).removeClass('text-more');
        $('.more-rank-'+1).hide();
        $('.fold-rank-'+1).show();
        
        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+1).click(() => {
        $('.ranking-text-rank-'+1).addClass('text-more');
        $('.more-rank-'+1).show();
        $('.fold-rank-'+1).hide();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });

    $('.fold-rank-'+2).hide();

    $('.more-rank-'+2).click(() => {
        $('.ranking-text-rank-'+2).removeClass('text-more');
        $('.more-rank-'+2).hide();
        $('.fold-rank-'+2).show();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+2).click(() => {
        $('.ranking-text-rank-'+2).addClass('text-more');
        $('.more-rank-'+2).show();
        $('.fold-rank-'+2).hide();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });

    $('.fold-rank-'+3).hide();

    $('.more-rank-'+3).click(() => {
        $('.ranking-text-rank-'+3).removeClass('text-more');
        $('.more-rank-'+3).hide();
        $('.fold-rank-'+3).show();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+3).click(() => {
        $('.ranking-text-rank-'+3).addClass('text-more');
        $('.more-rank-'+3).show();
        $('.fold-rank-'+3).hide();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
}

// QR 코드를 클릭할 시, QR코드 보여주기
const showQRcode = () => {
    const $modal = $('.modal');
    const $currentSeminarBoxHeight = $('.box-5').css('height').replace('px', '');

    // QR 코드를 클릭할 시, QR코드 보기 또는 접기
    $('.box-qr-code').hide();
    $('.qr-code-fold-button').hide();

    $('.qr-code-more-button').click(() => {
        $('.box-5').css({
            'height': parseInt($currentSeminarBoxHeight)+176+"px"
        })
        $('.box-qr-code').show();
        $('.qr-code-fold-button').show();
        $('.qr-code-more-button').hide();
    });

    $('.qr-code-fold-button').click(() => {
        $('.box-5').css({
            'height': $currentSeminarBoxHeight+"px"
        })
        $('.box-qr-code').hide();
        $('.qr-code-fold-button').hide();
        $('.qr-code-more-button').show();
    });

    // QR 코드 클릭 시, 모달로 QR 코드 보기 또는 접기
    $('.box-qr-code').click(() => {	
        $modal.show();	
    });	
    $('.mobile-show-qr-code').click(() => {	
        $modal.show();	
    });	
    $modal.click(() => {	
        $modal.hide();	
    });
};

// 웹소켓으로 받은 like 개수를 업데이트
const updateLikeCount = (message) => {
    const commentId = message.comment.commentId;
    const likeCount = message.comment.likeCount;

    const comment = document.querySelector(`[data-commentId="${commentId}"]`);
    const likeDiv = comment.lastElementChild.lastElementChild;
    likeDiv.textContent = likeCount;
}

// 웹소켓으로 받은 랭킹 순위 업데이트
const updateRanking = (message) => {
    const commentList = message.commentList;
    const likeNumDivList = document.querySelectorAll(".star-and-number");

    // 랭킹 리스트에 포함된 질문 업데이트
    commentList.forEach((comment, i) => {
        // 질문 콘텐츠 업데이트 
        const commentDiv = document.querySelector(`.ranking-text-rank-${i + 1}`);
        commentDiv.textContent = comment.content;

        // 질문 like 개수 업데이트
        const likeNumDiv = likeNumDivList[i].lastElementChild;
        likeNumDiv.textContent = comment.likeCount;
    });
}

// 웹소켓으로 새 질문 (JSON) 서버로 전달
const uploadNewQuestion = () => {
    const $inputButton = $('.input-send');
    const $mobileInputButton = $('.mobile-input-send');
    const $newQuestionText = $('textarea');

    // send 버튼을 누르면, 서버에게 새 질문 전달
    $inputButton.click(function() {
        if ($inputButton.hasClass('input-send-dim')) {
            console.log('질문을 입력하세요.');
        } else {
            sendNewQuestion($newQuestionText);
        }
    });
    $mobileInputButton.click(function() {
        if ($mobileInputButton.hasClass('mobile-input-send-dim')) {
            console.log('질문을 입력하세요.');
        } else {
            sendNewQuestion($newQuestionText);
        }
    });
};

// shortURL 주소를 QR 코드로 변환하여 표시
const uploadQRcode = () => {
    const shortURL = $( ".url-address" ).text();
    const size = 168;
    const modalSize = 298;
    jQuery('#qrcode').qrcode({width: size, height: size, text: shortURL});
    jQuery('#modalqrcode').qrcode({width: modalSize, height: modalSize, text: shortURL});
}

// DOM 렌더링이 완료되면, 실행
$(function () {

    // UI 인터랙션을 위한 변수 선언
    const currentHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
    const currentWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    
    const mobileStrechedHeight = parseInt(currentHeight-256)+"px";
    const mobileFoldedHeight = parseInt(currentHeight-110)+"px";
    
    const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
    const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
    const mainFoldedHeight = parseInt(currentHeight-232)+"px";

    // 
    if ( currentWidth <= 425 ) {
        $('.question-contents').css( "height", mobileFoldedHeight );
        $('.box-5-col-1-contents-2').css( "height", '24px' );
    } else {
        $('.question-contents').css( "height", mainFoldedHeight );
    }

    // 모든 메세지 별 아이콘에 업데이트 기능 추가
    const $likeList = $('.comment-likes');
    $likeList.each(function() {
        const $img = $(this).find('img')  
        addChangeLike($img);
    });

    // URL 복사 기능
    copyURL();

    // shortURL 주소를 QR 코드로 변환하여 표시
    uploadQRcode();
    
    // QR 코드 모달로 띄우기 기능
    showQRcode();

    // 랭킹 순위 (Top 3) 질문 공개 및 숨김 기능
    showOrFoldRanking(mainFoldedHeight);
    showOrFoldRankingMobile(mobileFoldedHeight, mobileStrechedHeight);

    // 랭킹 순위 내 질문의 글 공개 및 숨김 기능
    showOrFoldRankingText();

    // 배경 색깔 변경 기능 
    changeBackgroundColor();

    // 새 질문 보내기 버튼 활성/비활성화 기능
    enableOrDisableSendButton();

    // 웹소켓 연결
    connectWebSockets();

    // 새 질문 등록
    uploadNewQuestion();
});