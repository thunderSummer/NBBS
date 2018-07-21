
var getVerification=function(mail,int){
	var strjson=JSON.stringify({
    "email": mail,
    "type": int});
	$.ajax({
		 type:"POST",
	        url:"/user/verification",
	        async:false,
	        cache:false,
	        dataType:"json",
        headers:{
            Accept:"application/json",
            "Content-Type":"application/json"
        },

	        data:strjson,
		    beforeSend:function(){
		    	$("#refresh").css("display","inline-block");
		    	$("#refresh").children("i").addClass("fa-spin");
		    },
	        success:function(data){
	        	if(data.meta.success == true) {
	        		$("#verif-code").html("已发送至邮箱");
		    		$("#refresh").css("display","none");
		    		$("#refresh").children("i").removeClass("fa-spin");
				}
				else {
	        		$("#verif-code").html('无法获取');
		    		$("#refresh").css("display","none");
		    		$("#refresh").children("i").removeClass("fa-spin");
                }
	        },
            fail: function() {
                alertInformation("failed");
            },
            error: function(response) {
                alertInformation("error");
            }
	});
};

