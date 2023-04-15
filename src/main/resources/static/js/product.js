$(function() {
    $("#sub-nav-product").attr("class","active");
    $(".addCart").click(function () {
        var productId = $(this).attr("productid");
        $.ajax({
            url:"/cart/add/"+productId+"/1",
            success:function(result){
                if(result.status=="SUCCESS"){
                    toastr.info("Add to card success.");
                }else{
                    toastr.warn(result.message);
                }
            },
            error:function(){
                toastr.warn("error happened.");
            }
        })
    })
})