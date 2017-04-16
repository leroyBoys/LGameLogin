function addMoneyItem(_){
	var moneyItem = $(_).parent().find(".itemNo").first();
	var num = $(_).parent().find(".itemCount").first();
	if(moneyItem.val() == ""){
		openWindow(_);
		return;
	}
	addOptionAttach(_, moneyItem.val(), num.val());
	moneyItem.val("");
	num.numberbox("setValue",1);
	
}
function addOptionAttach(_,itemNo,itemNum){
	$.get(formatUrl($(_).attr("rel")),{itemNo:itemNo},function(data){
		if(data.id == null){
			alert("道具:"+itemNo+"不合法确认后再添加");
			return
		}
		var attachs = $(_).parentsUntil("table").find(".attachs").first();
		var first = attachs.children().first();
		if(first.val() == 0){
			first.remove();
		}
		var stackLimt = 1;
		if(data.stackable == 1){
			stackLimt = data.stack_limit;
		}
		var temp = itemNum;
		while(temp > 0){
			if(temp - stackLimt >= 0){
				attachs.append("<option   value='"+data.itemNo+","+stackLimt+","+data.itemType+"'>"+data.itemName+"("+data.itemNo+")*"+stackLimt+"</option>");  
			}else{
				attachs.append("<option    value='"+data.itemNo+","+temp+","+data.itemType+"'>"+data.itemName+"("+data.itemNo+")*"+temp+"</option>");  
			}
			temp = temp - stackLimt;
		}
		attachs.attr("size",attachs.children().length);
	});
}

function openWindow(_){
	var url = formatUrl($(_).attr("openURL"));
	var str = openDialog(url);
	if($.trim(str) != ''){
		var array = $.trim(str).split(";");
		for(var i = 0;i < array.length;i++){
			if(array[i] == ""){
				continue;
			}
			addOptionAttach(_, array[i].split(",")[0],array[i].split(",")[1]);
		}
	}
}
function removeSelect(_){
	var tt = $(_).children("option:selected");
	for(var i = 0;i < tt.length;i++){
		if(tt.eq(i).val != '0'){
			tt.eq(i).remove();
		}
	}
	if($(_).children().length <= 0){
		$(_).append("<option  value='0'>无</option>");
	}
	$(_).attr("size",$(_).children().length);
}
function moneySubmitForm(_){
	var reloadUrl = $(_).attr("reloadUrl");
	var options = $(_).parent().parent().find(".attachs").first().children();
	for(var i = 0 ;i < options.length;i++){
		if(options.eq(i).val() == 0){
			continue;
		}
		options.eq(i).attr("selected",true);
	}
	var form = $(_).parent().parent().find("form").first();
	form.form("submit",{
		success:function(data){
			var data = eval('(' + data + ')');
			if(data.status == 'succ'){
				alert("邮件发送成功");
				updateTab(reloadUrl);
			}else{
				alert(data.error);
			}
		}
	});
}
