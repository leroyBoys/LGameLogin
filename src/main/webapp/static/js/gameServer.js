$(document).ready(function(){
	/**
	 * 静态导航
	 */
	$(".VersionButton").live("click",function(){
		//var title = $(this).text();
		var content = $("#sendMsgInput").val();
		var array = content.split(",");
		console.log(array);

		var url = "/gameserver/version?version=0.0.0&gameId=1&srcId=2";
		$.get(formatUrl(url),function(json){
			console.log("====ret");
			console.log(json);
		});
	});


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
});