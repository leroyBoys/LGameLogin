$(document).ready(function(){

	function checkLog() {
		if($("#logContent").length >0){
			var url =  formatUrl("/gm/log");
			setTimeout(function () {
				$.post(url,{
					type:$("#logContent").attr("rel")
				} ,function(slist){
					console.log(slist);
					if(slist.length() == 0){
						console.log("======>empty");
						return
					}
					for(var line in slist){
						$("#logContent").append("<p>"+line+"</p>");
					}

				});

			} , 1500);
		}
	}
	

});