$(function () {
    $("#sub-nav-order").attr("class", "active");

    $(".cancel-order-btn").click(function () {
        var orderId = $(this).parent().parent().parent().attr("pid");
        $.ajax({
            url: "/user/order/cancel/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("order cancelled successs...");
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened..");
            }
        })
    });
    $(".pay-order-btn").click(function () {
        var orderId = $(this).parent().parent().parent().attr("pid");
        $.ajax({
            url: "/user/order/pay/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("Pay success...Will ship product soon...");
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened..");
            }
        })
    });

    $(".confirm-order-btn").click(function () {
        var orderId = $(this).parent().parent().parent().attr("pid");
        $.ajax({
            url: "/user/order/confirm/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("Order completed...");
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened..");
            }
        });
    });
    $(".del-order-btn").click(function () {
        var orderId = $(this).parent().parent().parent().attr("pid");
        $.ajax({
            url: "/admin/order/delete/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("Order deleted success...");
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened..");
            }
        })
    });
    $(".ship-order-btn").click(function () {
        var orderId = $(this).parent().parent().parent().attr("pid");
        $.ajax({
            url: "/admin/order/ship/" + orderId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    toastr.info("Product shipped success...");
                    window.location.reload();
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened..");
            }
        })
    });
})