String.prototype.EndWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substring(this.length - str.length) == str)
        return true;
    else
        return false;
    return true;
};

String.prototype.StartWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
    return true;
};
//zhangff
function IsEmpty(str) {
    if (str == null || $.trim(str) == '' || str == 'undefined') {
        return true;
    }
    return false;
};
function IsNum(num) {
    var reg = /^[0-9]{1,9}$/;
    if (!reg.test(num)) {
        return false;
    }
    return true;
};
function IsFloat(f) {
    var reg = /^-?([0-9]{0,9})\.?[0-9]*$/;
    if (!reg.test(f)) {
        return false;
    }
    return true;
};
function IsMobile(s) {
    s = s.Trim();
    var length = s.length;
    if (length == 11 && /^1[3456789]\d{9}$/.test(s)) {
        return true;
    } else {
        return false;
    }
}
String.prototype.Trim = function () {
    return this.replace(/^\s+/g, "").replace(/\s+$/g, "");
};
String.prototype.trimStart = function(c)
{
    if(c==null||c=="")
    {
        var str= this.replace(/^\s*/, '');  
        return str;
    }
    else
    {
        var rg=new RegExp("^"+c+"*");
        var str= this.replace(rg, '');
        return str;
    }
}

//去除字符串尾部空格或指定字符  
String.prototype.trimEnd = function(c)
{
    if(c==null||c=="")
    {
        var str= this;
        var rg =/\s/;  
        var i = str.length;
        while (rg.test(str.charAt(--i)));
        return str.slice(0, i + 1);
    }
    else
    {
        var str= this;
        var rg = new RegExp(c);
        var i = str.length;
        var s=i-c.length;
        while (rg.test(str.charAt(s)));
        return str.slice(0, s);
    }
}
function AjaxRequest(url, method, data, callback, iscache, processData, contentType) {
    if (!method) method = "get";
    if (!data) data = "";
    if (iscache == undefined) iscache = false;
    if (processData == undefined) processData = true;
    if (contentType == undefined) contentType = 'application/x-www-form-urlencoded';
    $.ajax({
        type: method,
        data:data,
        url: url,
        cache: iscache,
        async: true,
        processData: processData,//用于对data参数进行序列化处理 这里必须false
        contentType: contentType, //必须
        success: function (d) {
            if (callback)
                callback(d);
        },
        error: function (data) {
            alert('网络异常，请求失败');
        }
    });
}

function deepClone(obj) {
    var result = {}, oClass = isClass(obj);
     if(oClass==="Object"){
         result={};
     }else if(oClass==="Array"){
         result=[];
     }else{
         return obj;
     }
    for (key in obj) {
        var copy = obj[key];
        if (isClass(copy) == "Object") {
            result[key] = arguments.callee(copy);
        } else if (isClass(copy) == "Array") {
            result[key] = arguments.callee(copy);
        } else {
            result[key] = obj[key];
        }
    }
    return result;
}
function isClass(o) {
    if (o === null) return "Null";
    if (o === undefined) return "Undefined";
    return Object.prototype.toString.call(o).slice(8, -1);
}
function dateFormat(dateStr, fmt) { //author: meizz
    if (IsEmpty(dateStr))
        return "";
    var date;
    if (dateStr instanceof Date) {
        date = dateStr;
    }
    else if ( typeof dateStr === "string") {
        date = new Date(dateStr);
    }
    else {
        dateStr = dateStr.substring(1, dateStr.length - 1);// '/Date(1400046388387)/';
        date = eval('new ' + dateStr); //new Date()
    }
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function IsInArray(arr,val){
    var testStr=','+arr.join(",")+",";
    return testStr.indexOf(","+val+",")!=-1;
}
function parseDom(arg) {
    var objE = document.createElement("div");
    objE.innerHTML = arg;
    return objE;
};
function nodeToString ( node ) {
    var tmpNode = document.createElement( "div" );
    tmpNode.appendChild( node.cloneNode( true ) );
    var str = tmpNode.innerHTML;
    tmpNode = node = null; // prevent memory leaks in IE
    return str;
}
function uploadPicByUrl(url) {
    var aliUrl=url;
    $.ajax({
        type: "post",
        url: "/aliyun/uploadpicbyurl",
        data: {url:url},
        async:false,
        success: function (data) {
            if (data.code == 200) {
                aliUrl=data.data;
            }
            else {
                alert(data.msg);
            }
        },
        error: function (data) {
            alert('网络异常，查询失败');
        }
    });
    return aliUrl;
}
// 替换html中的样式标签
function replaceHtml(dom,pArray){
    if(dom.nodeType==3){
        if(!IsEmpty(dom.nodeValue.Trim()))
            pArray.push("<p>"+dom.nodeValue+"</p>");
        return pArray;
    }
    var patt = /h\d+/;
    var tagName=dom.tagName.toLowerCase();
    if(tagName=='br'||tagName=='style'||tagName=='script'){
        return pArray;
    }
    else if(tagName=='strong'||patt.test(tagName)){
        pArray.push(nodeToString(dom));
        return pArray;
    }
    else if(tagName=='img')
    {
        var src=dom.getAttribute('src');
        if(src)
        {
            dom.removeAttribute("crossorigin");
            if(dom.getAttribute("_width"))
            {
                dom.setAttribute("width",dom.getAttribute("_width"));
                dom.removeAttribute("_width");
                dom.style.cssText='margin:0 auto;';
            }
            src=src.substring(0,src.indexOf("?"));
            src=uploadPicByUrl(src);
            dom.setAttribute("src",src);
        }
        pArray.push('<p style="text-align: center;">'+nodeToString(dom)+'</p>');
        return pArray;
    }

    if(dom.childNodes.length!=0)
    {
        for (var i = 0; i < dom.childNodes.length; i++) {
            pArray=replaceHtml(dom.childNodes[i],pArray);
        }
    }
    else{
        if(dom.innerText&&!IsEmpty(dom.innerText.Trim()))
            pArray.push("<p>"+dom.innerText+"</p>");
    }
    return pArray
}