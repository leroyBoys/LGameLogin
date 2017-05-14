function formatUrl(url){
	if(url == undefined){
		return undefined;
	}
	var basePath = $(":input[name='basePath']").eq(0).val();
	if(dataValidation.isURL(url) && url.search(/http/) != -1){
		return url;
	}else if(url.charAt(0) == '/'){
		return basePath+ url;
	}else{
		return basePath+"/"+ url;
	}
}

function jsonStr(obj){
	return JSON.stringify(obj); //转JSON字符串
}

function doNow() {
	eval($("#target1").val());
}

function doNow2() {
	var url = "/gm/testmsg";
	var cmd_c = $("#cmd").val();
	console.log(cmd_c);
	console.log( cmd_c.split(",").length);
	if(cmd_c == "" || cmd_c.split(",").length == 1){
		$("#target1").val("cmd must is:module,cmd");
		return;
	}
	var module = cmd_c.split(",")[0];
	var cmd = cmd_c.split(",")[1];

	console.log( module+"    "+cmd);
	$.post(formatUrl(url),
		{
			modlue:module,
	        cmd: cmd,
			key: $("#key").val() ,
		    msg:$("#target1").val()
		},
		function(data){
			$("#target1").val(jsonStr(data));
		});

}