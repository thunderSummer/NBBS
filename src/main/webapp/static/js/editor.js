//加载版块选项

	$.ajax({
		url: '/allSection',
		type: 'GET',
		dataType: 'json',
        headers:{
            Accept:"application/json",
            "Content-Type":"application/json"
        },
		data: null,
		success:function(res){
			var select=$("#selectSec");
			$.each(res.data, function(i, sec) {
				$(select).append(`<option value="${sec.id}">${sec.title}</option>`);
			});
		},
		fail:function(){

		},
		error:(function() {
			/* Act on the event */
		})
	});

//发帖
function submit(){

	var title=$("input[name='title']").val();
	var content=$("#ts").val();
	var section=$("select[name='selectSec']");
	$.ajax({
		url: '/post',
		type: 'POST',
		dataType: 'json',
        headers:{
            Accept:"application/json",
            "Content-Type":"application/json"
        },
		data: {
			"title":title,
			"content":content,
			"section":{
				"id":section
			}
		},
		success:function(){
			window.alert("上传成功！！");
		},
		fail:function(){
			window.alert("上传失败！！");
		},
		error:(function() {
			/* Act on the event */
			window.alert("出现错误！！");
		})
	});
}