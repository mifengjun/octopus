fetchDouBanData = function () {
    $.ajax({
        async: false,
        //请求方式
        type: "POST",
        //请求地址
        url: "/movie/fetch",
        //数据，json字符串
        data: {id: '1'},
        //请求成功
        success: function (result) {
            alert(result);
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
};