//序列化form表单字段为json对象格式
$.fn.serializeFormToJson = function(){
    var arr = $(this).serializeArray();//form表单数据 name：value
    var param = {};
    $.each(arr,function(i,obj){ //将form表单数据封装成json对象
        param[obj.name] = obj.value;
    });
    return param;
};

/**
 * 注意：这里使用了上面的方式，没有使用这种-------------------------------------------
 * 将form里面的内容序列化成json
 * 相同的checkbox用分号拼接起来
 * @param {dom} 指定的选择器
 * @param {obj} 需要拼接在后面的json对象
 * @method serializeJson
 * */
$.fn.serializeJson=function(otherString){
    var serializeObj={},
        array=this.serializeArray();
    $(array).each(function(){
        if(serializeObj[this.name]){
            serializeObj[this.name]+=';'+this.value;
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    if(otherString!=undefined){
        var otherArray = otherString.split(';');
        $(otherArray).each(function(){
            var otherSplitArray = this.split(':');
            serializeObj[otherSplitArray[0]]=otherSplitArray[1];
        });
    }
    return serializeObj;
};

/**
 * 将josn对象赋值给form
 * @param {dom} 指定的选择器
 * @param {obj} 需要给form赋值的json对象
 * @method serializeJson
 * */
$.fn.setForm = function(jsonValue,obj){
    //var obj = this;
    //var pIframe = $('iframe');//找到上一级父页面
    //var subForm = $(pIframe).find(obj);//在父页面里找到editForm表格，这个Id为所编辑form的ID值
    //console.log(pIframe,subForm);
    console.log("表单修改",jsonValue);
    $.each(jsonValue,function(name,ival){
        var $oinput = obj.find("input[name='"+name+"']");
        if($oinput.attr("type")==="checkbox"){
            if(ival !== null){
                var checkboxObj = $("[name='"+name+"']");
                var checkArray = ival.split(";");
                for(var i=0;i<checkboxObj.length;i++){
                    for(var j=0;j<checkArray.length;j++){
                        if(checkboxObj[i].value === checkArray[j]){
                            checkboxObj[i].click();
                        }
                    }
                }
            }
        } else if($oinput.attr("type")==="radio"){
            $oinput.each(function(){
                var radioObj = $("['name="+name+"']");
                for(var i=0;i<radioObj.length;i++){
                    if(radioObj[i].value === ival){
                        radioObj[i].click();
                    }
                }
            });
        } else if($oinput.attr("type")==="textarea"){
            obj.find("[name='"+name+"']").html(ival);
        } else{
            obj.find("[name='"+name+"']").val(ival);
        }
    })
};
function getOpenFrom(layero,id) {
    var t = $(layero.find(id));
    var inDate = t.serializeArray();
    var data = {};
    $(inDate).each(function(index, obj){
        data[obj.name] = obj.value;
    });
    return data;
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: tsvico  tsxygwj@gmail.com
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};