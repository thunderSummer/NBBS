
	var cm="",cp="";
	function isEmail(strEmail) {
	    if (strEmail.search(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/) != -1)
	        return true;
	    else
	        return false;
	}

	function checkMail(node) {
	    var tip = document.getElementById("check-mail");
	    var mail = node.value;
	    if( ! isEmail(mail) ){
	    	cm=false;
	    	$("#verification").css("display","none");
	    	$("#refresh").css("display","none");
			$("#verif-code").off("click");
	    	return false;
	    }
	    else{
	    	cm=true;
	    	$("#verification").css("display","block");
	    	// $("#refresh").css("display","block");
	    	// getVerification(mail,2);
			$("#verif-code").on("click",function(){
				getVerification(mail,2);
			});
	    }
	}
	function isPsw(strPsw) {
	    if (strPsw.search(/^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{6,20}$/) != -1){
	        return true;
	    }
	    else{
	        return false;
	    }
	}

	function checkPsw(node) {
	    var tip = document.getElementById("check-psw");
	    var pwd = node.value;
	    if( ! isPsw(pwd) ){
	    	cp=false;
	    	return false;
	    }
	    else{
	    	cp=true;
	    }
	}

	$("#login").click(function(e){
		e.preventDefault();
		var verification=$("#verif").val();
		$("#model-alertInfo span.model-title").html("登 录");
		if(cm!=true||cp!=true || verification==""){
			alertInformation("请完善表单");
			return false;
		}
		var mail = $("#mail").val();
        var psw = $("#psw").val();
        var strjson=JSON.stringify({
                "email": mail,
            "password": psw,
        });
		$.ajax({
	        type:"POST",
	        url:"/user/login",
	        cache:false,
            headers:{
                Accept:"application/json",
                "Content-Type":"application/json"
            },
	        data:strjson,
	        success:function(result){
	        	if(result.meta.success === true) {
						alertInfoWithJump("登录成功","editor.html");
				}
				else {
                    alertInformation(result.meta.message);
                }
	        },
            fail: function() {
                alertInformation("failed");
            },
            error: function(response) {
                alertInformation("error");
            }
	    });
	});


	$("#sign-btn").click(function(){
		$("#model").css("display","block");
		$("#model .model-container").css("opacity",1);
	})
	$("#model .model-container .fa-close").click(function(){
		$("#model").css("display","none");
		var input=$("#model .model-body input");
		var i=input.length;
        for(var n=0;n<i-1;n++){
            input[n].value='';
            $("#model .model-body i").css("opacity",0);
        }
        $("#verification").css("display","none");
        $("#refresh").css("display","none");
	})

//忘记密码
$("#forget").click(function(e){
		e.preventDefault();
		$("#model-alertInfo span.model-title").html("忘记密码");
		var mail = $("#mail").val();
		var newPassword=$("#psw").val();
		if(cm!=true || isPsw(newPassword)!=true ){
			alertInformation("请在该页面填上您的绑定邮箱和想设置的新密码，并获取验证码后点击此按钮进行密码重置");
			return false;
		}
		var strjson=JSON.stringify({
            "user":{
                "email": mail,
                "password": newPassword
            },
            "verificationCode": verification
        });
		$.ajax({
	        type:"PUT",
	        url:" /user/forget/password",
	        // async:false,
	        cache:false,
	        dataType:"json",
            headers:{
                Accept:"application/json",
                "Content-Type":"application/json"
            },
	        data:strjson,
	        success:function(result){
	        	var data=eval('(' + result + ')');
	        	if(data.meta.success == true) {
						alertInformation(data.meta.message);/*"密码已发送至您的邮箱。"或"邮箱未注册"*/
				}
				else {
                    alertInformation(data.info);
                }
	        },
            fail: function() {
                alertInformation("failed");
            },
            error: function(response) {
                alertInformation("error");
            }
	    });
	});
