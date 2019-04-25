// UI interaction
const backgroundColor = function ChangeBackgroundColorYellowOrDark() {
    const $yellowButton = $('.yellow-button');
    const $darkButton = $('.dark-button');
    const $body = $('body');
    const $questionRankingText = $('.question-ranking-text');
    
    const $initialBody = $('.container');

    $yellowButton.click(function() {
        $body.removeClass('dark-version');
        $body.css({
            'background-color': '#f1f1f1'
        });
        $initialBody.css({
            'background-color': '#feb519'
        })
        $questionRankingText.css({
            'color': 'rgba(0, 0, 0, 0.87)'
        });
    });
    $darkButton.click(function() {
        $body.addClass('dark-version');
        $body.css({
            'background-color': '#363a43'
        });
        
        $initialBody.css({
            'background-color': '#363a43'
        })
        $questionRankingText.css({
            'color': 'rgba(255, 255, 255, 0.87)'
        });
        $('span > div').addClass('dark-version');
    });
}

const dim = function beforeKeyPressCreateRoomButtonIsDim() {
    const $createRoomButton = $('.create-room-button');
    const $input = $('input');

    $input.keyup(function(){
        if ($input.val() === '') {
            $createRoomButton.removeClass('room-button-active');
        } else {
            $createRoomButton.addClass('room-button-active');
            $createRoomButton.prop('disabled', false);
        }
    });
}

$(function () {
    backgroundColor();
    dim();
});