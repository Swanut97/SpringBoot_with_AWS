// static/js/app 로 작성해야하는데 static.js.app 으로 작성해서 버튼이 정상 작동하지 않음...
// 패키지가 아니라 디렉토리인 점을 감안하지 못했음. 방심 금지
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () { // btn-update라는 id를 가진 HTML 엘리먼트에 클릭이벤트 등록
            _this.update();
        });
        $('#btn-delete').on('click', function () { // btn-delete라는 id를 가진 HTML 엘리먼트에 클릭이벤트 등록
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST', // HTTP 메소드 중 POST
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다');
            window.location.href = '/'; // 글 등록 성공시 / 로 돌아감 (메인 페이지)
        }).fail(function (error) {
            alert(JSON.stringify(error)); // 글 등록 실패시 에러메세지
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(), // 괄호 잘 구분해서 쓸 것.
            content: $('#content').val() // 찾는데 한 세월 걸렸음.
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // HTTP 메소드 중 PUT
            url: '/api/v1/posts/'+id, // 수정할 게시글의 path를 구분하기 위해 path에 id 추가
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();