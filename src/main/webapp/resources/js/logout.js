$(function () {
    var loginUrl = "/gs/admin/logout";
    $('#logout').click(function () {
        $.ajax({
            url: loginUrl,
            async: false,
            cache: false,
            type: "get",
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    window.location.href = '/gs/admin/login';
                }
            }
        });
    })
})