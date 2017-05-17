$(document).ready(function(){

    var maxLineNum = 500;
    var timeVes = 3000;
	function checkLog() {

        if($(".logContent").length >0){
            var url =  formatUrl("/gm/log");

            var _type = "";
            $(".logContent").each(function(){
                _type += $(this).attr("rel")+",";
            });

            $.post(url,{
                types:_type
            } ,function(dataMap){
                console.log(jsonStr(dataMap));
                console.log("=========ret=>"+(typeof dataMap == "object"));

                if(typeof dataMap != "object"){
                    setTimeout(checkLog, timeVes);
                    return
                }

                var _curCount = 0;
                jQuery.each(dataMap, function(i, val) {  // i 指向键, val指定值
                    console.log(i+"=====>"+val+"   "+(val == "" ));
                    if(val != "" &&  val.length != 0){
                        var _parent = $("#logParent"+i);
                        var _firstChild = $(".logChild"+i).eq(0);
                        if($(".logChild"+i).length ==0){
                            _firstChild = $("<div class='.logChild'"+i+">"+"</div>");
                            _parent.append(_firstChild);
                        }

                        _curCount++;
                        if(_firstChild.children("p").length < maxLineNum){
                            for(var j in val){
                                _firstChild.append("<p>"+val[j]+"</p>");
                            }
                            return true;
                        }

                        var _secondChild = $(".logChild"+i).eq(1);
                        if($(".logChild"+i).length == 1 ){
                            _secondChild = $("<div class='.logChild'"+i+">"+"</div>");
                            _parent.append(_secondChild);
                        }

                        if(_secondChild.children("p").length < maxLineNum){
                            for(var j in val){
                                _secondChild.append("<p>"+val[j]+"</p>");
                            }
                            return true;
                        }

                        if($(".logChild"+i).length == 2 ){
                            _secondChild = $("<div class='.logChild'"+i+">"+"</div>");
                            _parent.append(_secondChild);
                        }else {
                            _secondChild = $(".logChild"+i).eq(2);
                        }

                        for(var j in val){
                            _secondChild.append("<p>"+val[j]+"</p>");
                        }

                        _firstChild.remove();
                    }
                });

                if(_curCount > 0){
                    timeVes = 500;
                }else{
                    timeVes+=1000;
                    timeVes = timeVes > 10000?10000:timeVes;
                }

                console.log(_curCount+"-------ssss----------->"+timeVes)
                setTimeout(checkLog, timeVes);

            });
            console.log("-----------ssss------->")
        }else {
            console.log("----222-------------->")
            setTimeout(checkLog, timeVes);
        }
    }

    checkLog();
});