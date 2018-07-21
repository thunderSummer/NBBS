'use strict';

	var cm='',cp='',ca='',cf='';
	//验证邮箱
	function isEmail(strEmail) {
	    if (strEmail.search(/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/) != -1)
	        return true;
	    else
	        return false;
	}

	function checkMail(node) {
	    var tip = document.getElementById("sign-up-mail").getElementsByClassName("check")[0];
	    var mail = node.value;
	    if(mail==""){
	    	tip.style.opacity='0';
	    	$("#verification").css("display","none");
	    	$("#refresh").css("display","none");
	    	cm=false;
			$("#verif-code").off("click");
	    	return false;
	    }
	    if( ! isEmail(mail) ){
	    	tip.style.opacity='1';
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	    	cm=false;
	    	$("#verification").css("display","none");
	    	$("#refresh").css("display","none");
			$("#verif-code").off("click");
	    	return false;
	    }
	    else{
	    	tip.style.opacity='1';
	    	tip.className="fa fa-check check";
	    	tip.style.color="green";
	    	cm=true;
	    	$("#verification").css("display","block");
	    	// $("#refresh").css("display","block");
			$("#verif-code").on("click",function(){
				getVerification(mail,1);
			});
	    }
	}

	//验证密码
	function isPsw(strPsw) {
	    if (strPsw.search(/^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{6,20}$/) != -1){
	    // if(strPsw.length!=0){
	        return true;
	    }
	    else{
	        return false;
	    }
	}

	function checkPsw(node) {
	    var tip = document.getElementById("sign-up-psw").getElementsByClassName("check")[0];
	    var pwd = node.value;
	    if(pwd==""){
	    	tip.style.opacity='0';
	    	cp=false;
	    	return false;
	    }
	    if( ! isPsw(pwd) ){
	    	tip.style.opacity='1';
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	    	cp=false;
	    	return false;
	    }
	    else{
	    	cp=true;
	    	tip.style.opacity='1';
	    	tip.className="fa fa-check check";
	    	tip.style.color="green";
	    }
	    var conf=document.getElementById("cf-psw").value;
	    if(conf!=""){
	    	confirmPsw(document.getElementById("cf-psw"));
	    }
	    if(pwd==""){
	    	confirmPsw(document.getElementById("cf-psw"));
	    }
	}
	//确认密码
	function confirmPsw(node){
		var tip = document.getElementById("confirm-psw").getElementsByClassName("check")[0];
	    var pwd = node.value;
	    var proto=document.getElementById("psw").value;
	    if(pwd==""){
	    	tip.style.opacity='0';
	    	cf=false;
	    	return false;
	    }
	    if( pwd == proto){
	    	tip.style.opacity='1';
	    	tip.className="fa fa-check check";
	    	tip.style.color="green";
	    	cf=true;
	    	if(proto==""){
	    		tip.style.opacity='0';
	    		cf=false;
	    	}
	    }
	    else{
	    	tip.style.opacity='1';
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	    	cf=false;
	    	return false;
	    }
	}

	//验证账号
	function checkAccount(node) {
	   	var tip = document.getElementById("sign-up-name").getElementsByClassName("check")[0];
	    var account = $.trim(node.value);
	    var name= node.value;
	    var reg=/^[\u4e00-\u9fff\w]{2,10}$/;/*由字母、数字、_或汉字组成;*/
	    if(name==""){
	    	tip.style.opacity=0;
	        ca=false;
	        return false;
	    }
	    console.log(reg.test(name));
	    if (reg.test(name)!=true) {
	       	tip.style.opacity=1;
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	        ca=false;
	        return false;
	    }
	    else {
	    	tip.style.opacity='1';
	    	tip.className="fa fa-check check";
	    	tip.style.color="green";
    		ca=true;
	   //  	$.ajax({
		  //       type:"POST",
		  //       url:"/index/userCheck/",
		  //       // async:false,
		  //       cache:false,
		  //       data:{
	   //              "username": name
		  //       },
		  //       success:function(data){
		  //       	if(data.meta.success == true) {
				// 	    	tip.style.opacity='1';
				// 	    	tip.className="fa fa-check check";
				// 	    	tip.style.color="green";
				//     		ca=true;
				// 	    }
				// 	else{
				// 			alertInformation(data.meta.message);
				// 	    	tip.style.opacity='1';
				// 	    	tip.className="fa fa-close check";
				// 	    	tip.style.color="red";
				// 	    	ca=false;
				// 	    }
				// 	},
				// fail: function() {
				// 	e.preventDefault();
	   //              alertInformation("failed");
	   //          },
				// error: function(response) {
    //                 e.preventDefault();
    //                 alertInformation("error");
    //                 // $("html").load("test.php");
    //             }
		  //   });
	    }
	}


	function checkInv(node){
		var ci;
		var tip = document.getElementById("invitation").getElementsByClassName("check")[0];
		if(node.value==""){
			tip.style.opacity='1';
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	        ci=false;
			return false;
		}
		else{
			tip.style.opacity='0';
	    	tip.className="fa check";
	    	ci=true;
		}
	}
	function checkPro(node){
		var cg;
		var tip = document.getElementById("project").getElementsByClassName("check")[0];
		if(node.value=="----"){
			tip.style.opacity='1';
	    	tip.className="fa fa-close check";
	    	tip.style.color="red";
	        cg=false;
			return false;
		}
		else{
			tip.style.opacity='0';
	    	tip.className="fa check";
	    	tip.style.color="green";
	    	cg=true;
		}
	}


$("#proGroup").on("change",function(){
	var ci;
	if(this.value=="User"){
		$("#invitation").css("display","none");
		ci=true;
	}
	else{
		$("#invitation").css("display","block");
		ci=false;
	}
})



	var showHint=function(e){
		var id=e.name;
		var getHint="hint-"+id;
		var hintEl=document.getElementById(getHint);
		$(e).siblings("span.hint").css("opacity","1");
		if(id=="mail"){
			hintEl.innerHTML="";
		}
		else if(id=="name"){
			hintEl.innerHTML="请输入2-10位由字母、数字、_或汉字组成的用户名";
			$(hintEl).siblings("i").css("opacity","1");
		}
		else if(id=="password"){
			hintEl.innerHTML="请输入6-20位密码，可包含字母、数字、横线或下划线";
			$(hintEl).siblings("i").css("opacity","1");
		}
		else if(id=="confirm-password"){
			hintEl.innerHTML="";
		}
		else
			hintEl.innerHTML="";
	}
	var hideHint=function(e){
		var id=e.name;
		var getHint="hint-"+id;
		var hintEl=document.getElementById(getHint);
		$(e).siblings("span.hint").css("opacity","0");
		$(hintEl).siblings("i").css("opacity","0");
	}

	$("#sign-up").on("submit",function(e){
		e.preventDefault();
        var verification=$("#verif").val();
		if(cm!=true || ca!=true || cp!=true ||cf!=true || verification==""){
			alertInformation("请完善表单!");
			return false;
		}
		var username = $("#name").val();
		var email = $("#mail").val();
        var psw = $("#psw").val();

        var jsonstr=JSON.stringify({
            "user":{
                "nickname": username,
                "email": email,
                "password": psw
            },
            "vCode": verification
        });
		$.ajax({
	        type:"POST",
	        url:"/user/register",
            headers:{
                Accept:"application/json",
                "Content-Type":"application/json"
            },
	        async:false,
	        cache:false,
	        data:jsonstr,
	        success:function(result){
	        	var data=eval('(' + result + ')');
	        	if(data.meta.success == true) {
					alertInfoWithJump("注册成功！","home.html");
				}
				else {
                    alertInformation(data.meta.message);
                }
	        },
            fail: function() {
				e.preventDefault();
                alertInformation("failed");
            },
            error: function(response) {
				e.preventDefault();
                alertInformation("error");
            }
	    });
	});
