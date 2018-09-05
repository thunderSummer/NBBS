


var showAlert=function(){
	$("#model-alertInfo").css("display","block");
	$("#model-alertInfo .model-container").animate({opacity:"1",top:"30%"},300);
}
var closeAlert=function(){
	$("#model-alertInfo").css("display","none");
	$("#model-alertInfo .model-container").css("opacity","0.2");
	$("#model-alertInfo .model-container").css("top","29%");
}

var showAlert0=function(){
	$("#model-alertInfo_0").css("display","block");
	$("#model-alertInfo_0 .model-container").animate({opacity:"1",top:"30%"},300);
}

function alertInfoWithJump0(msg){
	showAlert0();
	$("#model-alertInfo_0 .model-body .message")[0].innerHTML=msg;
}


function alertInfoWithJump(msg,url){
	// $("#model").css("display","none");
	showAlert();
	$("#model-alertInfo .model-body .message")[0].innerHTML=msg;
	$("#model-alertInfo .model-end .get").click(function(){
		location.href=url;
	})
	$("#model-alertInfo .model-container .fa-close").click(function(){
		location.href=url;
	});
}
function alertInfoWithJump2(msg,url){
	// $("#model").css("display","none");
	//alertInformation(msg);
	showAlert();
	
	$("#model-alertInfo .model-body .message")[0].innerHTML=msg;
	$("#model-alertInfo .model-end .get").click(function(){
		location.href=url;
	})
	$("#model-alertInfo .model-container .fa-close ").click(function(){
		closeAlert();
	});
	var x= document.getElementById("cancle");
	x.style.display='none';
	var y=document.getElementById("confirm");
	y.style.display='inline-block';
}

function alertInfoWithJump3(msg,url){
	// $("#model").css("display","none");
	//alertInformation(msg);
	showAlert();
	
	$("#model-alertInfo .model-body .message")[0].innerHTML=msg;
	$("#model-alertInfo .model-end .get").click(function(){
		location.href=url;
	})
	$("#model-alertInfo .model-container .fa-close ").click(function(){
		closeAlert();
	});
	var x=document.getElementById("confirm");
	var y=document.getElementById("cancle");
	x.style.display='none';
	y.style.display='inline-block';
}

function alertInformation(msg){
	// $("#model").css("display","none");
	showAlert();
	$("#model-alertInfo .model-body .message")[0].innerHTML=msg;
	$("#model-alertInfo .model-end .get").click(function(){closeAlert()});
	$("#model-alertInfo .model-container .fa-close").click(function(){closeAlert()});
}

// $("#model-alertInfo .model-end .shut").click(function(){
// 		$("#model-alertInfo").css("display","none");
// })