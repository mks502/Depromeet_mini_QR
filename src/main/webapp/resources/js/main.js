// UI interactions
const main = function changeRankingHeight(foldedHeight) {
    const $questionRanking = $('.Question-ranking');
    const $questionRankingMore = $('.Question-ranking-more');
    const $questionContents = $('.question-contents');
    const $foldButton = $('.fold-button');
    const $moreButton = $('.more-button');

    $questionRankingMore.hide();
    $foldButton.hide();

    if ($('.ranking-text-rank-1').text() === '') {
        $('.circle-button').attr('src', './images/more-button-dim.png');
    } else {
        $moreButton.click(function(){
            $questionRanking.hide();
            $questionRankingMore.show();
            $foldButton.show();
            $questionContents.css({
                'height': parseInt(Math.max(document.documentElement.clientHeight, window.innerHeight || 0)-parseInt($('.Question-ranking-more').css('height').replace('px', ''))-172)+"px"
            });
        });
    
        $foldButton.click(function(){
            $questionRanking.show();
            $questionRankingMore.hide();
            $foldButton.hide();
            $questionContents.css({
                'height': foldedHeight,
            });
        });
    }
}

const mobile = function mobileChangeRankingHeight(foldedHeight, strechedHeight) {
    const $questionRanking = $('.Question-ranking');
    const $questionRankingMore = $('.Question-ranking-more');
    const $questionContents = $('.question-contents');
    const $circleButton = $('.mobile-circle-button');
    const $secondCircleButton = $('.mobile-circle-button-2')

    if ($('.ranking-text-rank-1').text() === '') {
        $('.mobile-circle-button').attr('src', './images/more-button-dim.png');
    } else {
        $circleButton.click(function() {
            $questionRanking.hide();
            $questionRankingMore.show();
            $questionContents.css({
                'height': strechedHeight,
            });
        });
    
        $secondCircleButton.click(function() {
            $questionRanking.show();
            $questionRankingMore.hide();
            $questionContents.css({
                'height': foldedHeight,
            });
        });
    }
}

const showQRcode = function showOrHideQuickResponsiveCode() {
    const $modal = $('.modal');

    $('.box-qr-code').hide();
    $('.qr-code-fold-button').hide();

    $('.qr-code-more-button').click(function() {
        $('.box-5').css({
            'height': '384px'
        })
        $('.box-qr-code').show();
        $('.qr-code-fold-button').show();
        $('.qr-code-more-button').hide();
    });

    $('.qr-code-fold-button').click(function() {
        $('.box-5').css({
            'height': '208px'
        })
        $('.box-qr-code').hide();
        $('.qr-code-fold-button').hide();
        $('.qr-code-more-button').show();
    });

    $('.box-qr-code').click(function() {
        $modal.show();
    });
    $('.mobile-show-qr-code').click(function() {
        $modal.show();
    });
    $modal.click(function() {
        $modal.hide();
    });
}

const backgroundColor = function ChangeBackgroundColorYellowOrDark() {
    const $yellowButton = $('.yellow-button');
    const $darkButton = $('.dark-button');
    const $body = $('body');
    const $blank = $('#blank');
    const $initialBody = $('#initial-body');
    const $questionRankingText = $('.question-ranking-text');
    const $pleaseInputQuestions = $('.please-input-questions');
    const $modal = $('.modal');
    const $modalContents = $('.modal-content');

    $yellowButton.click(function() {
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
    $darkButton.click(function() {
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
}

//
const newElement = function createNewElement() {
    const $textarea = $('textarea');
    const $ul = $('ul');
    const $inputSend = $('.input-send');
    const $mobileInputSend = $('.mobile-input-send');
    const $beforeQuestionInput = $('.before-question-contents')

    $inputSend.click(function() {
        if ($inputSend.hasClass('input-send-dim')) {
            console.log('질문을 입력하세요.');
        } else {
            $ul.append('<div><ol></ol><span></span></div>');
            $('ol:last').append($textarea.val());
            $('span:last').append('<img src="./images/white-star.png" class="yellow-star" alt="Button to recommend questions"><div>0</div>')
            $('span:last > img').addClass('white-star');
            $('span:last > div').addClass($('body').attr('class'));

            $textarea.val('')
            $inputSend.addClass('input-send-dim');
            $beforeQuestionInput.hide();
            changeStarColor();
        }
    });

    $mobileInputSend.click(function() {
        if (!$mobileInputSend.hasClass('mobile-input-send-dim')) {
            console.log('질문을 입력하세요.');
        } else {
            $ul.append('<div><ol></ol><span></span></div>');
            $('ol:last').append($textarea.val());
            $('span:last').append('<img src="./images/white-star.png" class="yellow-star" alt="Button to recommend questions"><div>0</div>')
            $('span:last > div').addClass($('body').attr('class'));

            $textarea.val('');
            $mobileInputSend.removeClass('mobile-input-send-dim');
            $beforeQuestionInput.hide();
            changeStarColor();
        }
    });
}

//
const questionRanking = function questionRankingTopThreeTextMoreOrFold() {

    const currentHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

    for (var i = 0; i < 4; i++) {
        if ($('.ranking-text-rank-'+i).text().length < 172) {
            $('.more-rank-'+i).hide();
        }
    }
    
    $('.fold-rank-'+1).hide();

    $('.more-rank-'+1).click(function() {
        $('.ranking-text-rank-'+1).removeClass('text-more');
        $('.more-rank-'+1).hide();
        $('.fold-rank-'+1).show();
        
        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+1).click(function() {
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

    $('.more-rank-'+2).click(function() {
        $('.ranking-text-rank-'+2).removeClass('text-more');
        $('.more-rank-'+2).hide();
        $('.fold-rank-'+2).show();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+2).click(function() {
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

    $('.more-rank-'+3).click(function() {
        $('.ranking-text-rank-'+3).removeClass('text-more');
        $('.more-rank-'+3).hide();
        $('.fold-rank-'+3).show();

        const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
        const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
        $('.question-contents').css({
            'height': mainStrechedHeight,
        });
    });
    $('.fold-rank-'+3).click(function() {
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

const copyURL = function pressCopyButtonThenCopyClipboard() {
    $('.box-5-col-1-contents-2 > img').click(function() {
        var $temp = $("<input>");
        $("body").append($temp);
        $temp.val($('.url-address').text()).select();
        document.execCommand("copy");
        $temp.remove();

        $('.url-copy-button').hide();
        $('.url-copy-animation').show();
        setTimeout(function() {
            $('.url-copy-animation').hide();
            $('.url-copy-button').show();
        }, 4500);
    });
}

const changeStarColor = function pressLikedButtonSoThatTransformToYellowStar() {
    var counterLikedNumber = 0;
    
    $("ul > div:last > span > img").click(function() {
        const $this = $(this);

        if ($this.hasClass('yellow-star')) {
            $this.attr('src', './images/Star_interaction_' + Math.floor(Math.random() * 6) + '.gif');
            setTimeout(function() {
                $this.attr('src', './images/one_star.png');
            }, 2800);
            $this.toggleClass('yellow-star');
            counterLikedNumber++;
            $this.next().text(counterLikedNumber);
        } else {
            $this.attr('src', './images/white-star.png');
            $this.toggleClass('yellow-star');
            counterLikedNumber--;
            $this.next().text(counterLikedNumber);
        }
    });
}

const dim = function beforeKeyPressInputSendButtonIsDim() {
    const $inputSend = $('.input-send');
    const $textarea = $('textarea');

    $textarea.keyup(function(){
        if ($textarea.val() === '') {
            $inputSend.addClass('input-send-dim');
        } else {
            $inputSend.removeClass('input-send-dim');
        }
    });

    $textarea.keyup(function() {
        if ($textarea.val() === '') {
            $('.mobile-input-send').removeClass('mobile-input-send-dim');
        } else {
            $('.mobile-input-send').addClass('mobile-input-send-dim');
        }
    });
}

// When DOM render ready,
$(function () {
    // Implement UI interactions
    const currentHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
    const currentWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    
    const mobileStrechedHeight = parseInt(currentHeight-256)+"px";
    const mobileFoldedHeight = parseInt(currentHeight-110)+"px";
    
    const $questionRankingMoreHeight = $('.Question-ranking-more').css('height').replace('px', '');
    const mainStrechedHeight = parseInt(currentHeight-parseInt($questionRankingMoreHeight)-172)+"px";
    const mainFoldedHeight = parseInt(currentHeight-232)+"px";

    if ( currentWidth <= 425 ) {
        $('.question-contents').css( "height", mobileFoldedHeight );
    } else {
        $('.question-contents').css( "height", mainFoldedHeight );
    }
    
    main(mainFoldedHeight, mainStrechedHeight);
    mobile(mobileFoldedHeight, mobileStrechedHeight);
    showQRcode();
    backgroundColor();
    newElement();
    questionRanking();
    copyURL();
    changeStarColor();
    dim();

    // connection
    connectWebSockets();
    sendNewQuestion();
});

// http request-response handling (index.js로 옮기기)

// update seminar information (Ajax)
var seminarID = null;

// request QR code (Ajax)
// <img src="https://api.qrserver.com/v1/create-qr-code/?data=HelloWorld&amp;size=100x100" alt="" title="" />
// 위에 형식 사용하기 (embed in html and update image src by js)

// Web Socket configuration
var stompClient = null;
const connectWebSockets = () => {
	var socket = new SockJS('/mini_QR/q-rank-websock');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, (frame) => {
        setConnected(true);
        console.log('Connected: ' + frame);
        // 원래 path: `/seminar/${seminarID}`
        stompClient.subscribe(`/seminar/112`, (res) => {
            // parse JSON response
            // const type = JSON.parse(res.body).type;
            console.log(res);

            // update messages
            // if (data.type == "comment") {
                const ul = document.querySelector(".question-lists");
                const ol = document.createElement("ol");
                ol.textContent = res;
                ul.appendChild(ol);
            //}

            
            // update message likes
            // if (data.type == "likes") {}

            // update rankings
            // if (data.type == "ranking") {}
        });
    });
}


// Send message via web sockets
function sendNewQuestion() {
    $( ".send-question" ).click(function(){
        const seminarId = 112;
        const content = document.querySelector(".new-question").value;
        const message = JSON.stringify({'seminarId': seminarId, 'content': content});
        console.log("JSON: ", message);
        stompClient.send("/updates", {}, message);
    });
}