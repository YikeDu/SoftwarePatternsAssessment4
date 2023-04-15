$(function () {
    $("#sub-nav-news").attr("class", "active");

    $(".del-news-btn").click(function () {
        var newsId = $(this).parent().parent().attr("pid");
        $.ajax({
            url: "/admin/news/delete/" + newsId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("Notice deleted success...");
                    $("tr[pid="+newsId+"]").remove();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened");
            }
        })
    });
})