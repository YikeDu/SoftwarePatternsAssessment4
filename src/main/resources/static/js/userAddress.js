$(function () {
    $("#sub-nav-address").attr("class","active");
    //删除用户地址
    $(".delAddresslBtn").click(function () {
        var addressId = $(this).attr("addressId");
        $.ajax({
            url: "/user/userAddress/delete/" + addressId,
            success: function (result) {
                if(result.status=="SUCCESS"){
                    $("tr[addressId="+addressId+"]").remove();
                    toastr.info("Address info deleted success...");
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened...");
            }
        })
    });

    //保存用户地址
    $("#addAddressBtn").click(function () {
        var addressId = $(this).attr("addressId");
        $.ajax({
            url: "/user/userAddress/add/",
            method:"post",
            data:{
                "id":$("#id").val(),
                "address":$("#address").val(),
                "phone":$("#phone").val(),
                "zipcode":$("#zipcode").val(),
                "consignee":$("#consignee").val()
            },
            success: function (result) {
                if(result.status=="SUCCESS"){
                    $('#addAddressSuccess').show();
                    toastr.info("Deleted success");
                } else {
                    toastr.error(result.message);
                }
            },
            error: function () {
                toastr.error("Error happened...");
            }
        })
    });

    //修改用户地址
    $(".editAddresslBtn").click(function () {
        var addressId = $(this).attr("addressId");
        $.ajax({
            url: "/user/userAddress/" + addressId,
            method:"get",
            success: function (result) {
                toastr.info("功能暂未实现...  ;)");
                // alert(result);
            },
            error: function () {
                toastr.error("Error happened...");
            }
        })
    });
})