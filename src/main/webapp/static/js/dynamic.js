
function formaterStatus(value,row,index){
	if(value==0){
		return "未读";
	}else if(row.attach_status==1){
		return "已提取";
	}else{
		return "已读";
	}

}
function formaterIsAttach(value,row,index){
    if(value != 0 || row.cash != 0){
    	return "有";
    }else{
    	return "无";
    }
}
function view(rowdata){
		updateTab("dynamic/toEditPage?id="+rowdata);	
}
function Preview(){
	var row = $(dgId).datagrid('getSelected');
	//updateTab("dynamic/toEditPage?id="+row.id);	
	openNewWindow("dynamic/toEditPage?id="+row.id,"君主详细信息");
}
function viewMessage(){
	var row = $(dgId).datagrid('getSelected');
	openNewWindow("email/toViewMessage?id="+row.id,"邮件详细信息");
}
function edit(){
	var row=$(dgId).datagrid('getSelected');
    if(row.is_freeze==1){
    	status=0;
    }else{
    	status=1;
    }
$.get("dynamic/updateUserCharacterStatus?id="+row.id+"+&status="+status+"" ,function(success){
	refreshTable('/dynamic/todynamic');
});	
}
function createElement(){
	 var createDiv = document.createElement("div");
	 createDiv.innerHTML = "Test create a div element!";
	 document.body.appendChild(createDiv);
}




	
	
	