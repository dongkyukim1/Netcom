$(document).ready(function() {
    $('#signupButton').click(function() {
        if (!validateForm()) {
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/signup',
            contentType: 'application/json',
            data: JSON.stringify({
                name: $('#name').val(),
                phoneNumber: $('#phoneNumber').val(),
                department: $('#department').val(),
                position: $('#position').val(),
                email: $('#email').val(),
                password: $('#password').val()
            }),
            success: function(response) {
                alert(response);
                if ($('#position').val() === '차장' || $('#position').val() === '부장') {
                    window.location.href = '/login';
                } else {
                    window.location.href = '/pending-approval';
                }
            },
            error: function(xhr, status, error) {
                alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });

    function validateForm() {
        var fields = ['name', 'phoneNumber', 'department', 'position', 'email', 'password'];
        for (var i = 0; i < fields.length; i++) {
            if (!$('#' + fields[i]).val()) {
                alert('모든 필드를 입력해주세요.');
                return false;
            }
        }
        return true;
    }
});