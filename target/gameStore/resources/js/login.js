$(function () {
    var loginUrl = "/gs/admin/logincheck";
    // 登录次数，累积登录三次失败之后自动弹出验证码要求输入
    var loginCount = 0;
    $('#submit').click(function () {
        // 获取输入的帐号
        var username = $('#username').val();
        // 获取输入的密码
        var password = $('#psw').val();
        // 获取验证码信息
        var verifyCodeActual = $('#jVerify').val();
        // 是否需要验证码验证，默认为false,即不需要
        var needVerify = false;
        // 如果登录三次都失败
        if (loginCount >= 3) {
            // 那么就需要验证码校验了
            if (!verifyCodeActual) {
                $('#toastsuccess').html('请输入验证码！').show();
                setTimeout(function () {
                    $('#toastsuccess').hide();
                }, 2000);
                return;
            } else {
                needVerify = true;
            }
        }
        $.ajax({
            url: loginUrl,
            async: false,
            cache: false,
            type: "post",
            dataType: 'json',
            data: {
                username: username,
                password: password,
                verifyCodeActual: verifyCodeActual,
                //是否需要做验证码校验
                needVerify: needVerify
            },
            success: function (data) {
                if (data.success) {
                    $('#toastsuccess').html('登录成功！').show();
                    setTimeout(function () {
                        $('#toastsuccess').hide();
                        window.location.href = '/gs/admin/main';
                    }, 1000);
                } else {
                    $('#toastwarngin').html('登录失败！' + data.errMsg).show();
                    setTimeout(function () {
                        $('#toastwarngin').hide()
                    }, 2000);
                    loginCount++;
                    if (loginCount >= 3) {
                        // 登录失败三次，需要做验证码校验
                        $('#verifyPart').show();
                    }
                }
            }
        });
    })
})