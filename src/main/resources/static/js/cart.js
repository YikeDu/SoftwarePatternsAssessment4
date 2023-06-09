$(function () {
    $(".addressItem").click(function () {
        var $kids = $(this).children();
        $kids.each(function (index) {
            if (index == 0) {
                $("#address").val($(this).text());
            }
            if (index == 1) {
                $("#consignee").val($(this).text());
            }
            if (index == 2) {
                $("#id").val($(this).val());
            }
            if (index == 3) {
                $("#zipcode").val($(this).val());
            }
            if (index == 4) {
                $("#phone").val($(this).val());
            }
        });
    });

    $(".delBtn").click(function () {
        var pid = $(this).attr("productid");
        $.ajax({
            url: "/cart/delete/" + pid,
            success: function (result) {
                if (result.status == "SUCCESS") {
                    $("tr[pid=" + pid + "]").remove();
                    $('#delSuccess').show();
                    setTimeout(function () {
                        $('#delSuccess').hide('slow');
                    }, 3000);
                } else {
                    alert(result.message);
                }
            },
            error: function () {
                alert("Error happened..");
            }
        })
    });
    $("#cleanCart").click(function () {
        $.ajax({
            url: "/cart/deleteAll",
            success: function (result) {
                if (result.status == "SUCCESS") {
                    $("tbody tr").remove();
                    $('#delAllSuccess').show();
                    setTimeout(function () {
                        $('#delAllSuccess').hide('slow');
                    }, 3000);
                } else {
                    alert(result.message);
                }
            },
            error: function () {
                alert("Error happened..");
            }
        })
    });
    $(".delTotal").click(function () {
        var dom = $(this).next();
        var currentTotal = parseInt(dom.html());
        if (currentTotal > 1) {
            $.ajax({
                url: "/cart/add/" + dom.attr("productid") + "/-1",
                success: function (result) {
                    if (result.status == "SUCCESS") {
                        dom.html(currentTotal - 1);
                    } else {
                        alert(result.message);
                    }
                },
                error: function () {
                    alert("Error happened..");
                }
            })
        }
    });
    $(".addTotal").click(function (event) {
        var dom = event.target.previousElementSibling;
        var currentTotal = parseInt(dom.getInnerHTML());
        if (currentTotal < 999) {
            $.ajax({
                url: "/cart/add/" + dom.getAttribute("productid") + "/1",
                success: function (result) {
                    if (result.status == "SUCCESS") {
                        dom.setHTML(currentTotal + 1);
                    } else {
                        alert(result.message);
                    }
                },
                error: function () {
                    alert("Error happened..");
                }
            })
        }
    });
})