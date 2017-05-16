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

function sendmsg() {
	var url = "/gm/testmsg";
	var module =  $("#module").val() ;
	var cmd =  $("#cmd").val() ;
	var msg = $("#target1").val();
	var uid = $("#uid").val();

	$("#target2").val( $("#key").val()+" 准备发送数据cmd:"+cmd+" module:"+module+" contect:"+msg+"  uid:"+uid);
	$.post(formatUrl(url),
		{
			module:module,
	        cmd: cmd,
			key: $("#key").val() ,
			uid:uid,
			msg:msg
		},
		function(data){
			$("#target2").val(jsonStr(data));
		});

}

function getVerion() {
	var url = formatUrl("/gameserver/version");

	var data = jsonStr({ version: "0.1.1"
		, gameId: $("#gameId").val()
		, srcId: $("#srcId").val()
	});

	$.post(url,{
			data:data
		}
		,function(data){
			$("#versions").val(jsonStr(data));
		});
}


function login() {
	//登录
	var url =  formatUrl("/gameserver/login");
	var data = jsonStr({ userName:  $("#userName").val()
		, pwd:  $("#userPwd").val()
		,dev:{
			plat:"iphone",
			udid:"xxddsp12",
			mac:"01-5953-xa",
			info:"testinfo"
		}
	}); //转JSON字符串
	$.post(url,
		{ data: data
		} ,function(s){
			if(s.error){
				$("#target2").val(jsonStr(s));
				return
			}
			$("#uid").val(s.suc.uid);
			$("#key").val(s.suc.key);
			$("#tipLable").text("uid:"+$("#uid").val()+"登录成功=>"+$("#key").val());
		});
}

function connect() {
	//登录
	var url =  formatUrl("/gm/first");
	var data = { uid: $("#uid").val() ,
        cmd: 2,
		msg: $("#target1").val(),
		key: $("#key").val(),
		serverId: $("#serverId").val()
	}; //转JSON字符串


	console.log(data);
	$.post(url,data ,function(s){
		$("#target2").val(jsonStr(s));
	});
}

function getJsonsss() {
	//登录
	var url =  formatUrl("/gm/testmsg");
	var data = { uid: $("#uid").val(),
		module:$("#module").val()
		, cmd: $("#cmd").val(),
		key: ""
	}; //转JSON字符串


	$.post(url,data ,function(s){
		$("#target1").val(jsonStr(s.obj));
		$("#target2").val(jsonStr(s.json));
	});
}