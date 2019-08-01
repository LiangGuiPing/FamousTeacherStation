$(function () 
{
	if(msg == "1")
	{
		var that = $("#info_error");
		that.fadeIn();
		window.setTimeout(function(){that.fadeOut();},2000);
	}
	
	$("#user_mobile").blur(function()
	{
		var v = $(this).val();
		validationMobile(v);
	});
	
	$("#login_submit").click(function()
	{
		var m = validationMobile($("#user_mobile").val());
		if(m)
		{
			var p = validationPassword($("#user_password").val());
			if(p)
			{
				$("#form-submit").submit();
			}
		}
	});
});



function isMobileNo(phone)
{
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

function validationMobile(v)
{
	if("" == v || null == v || v == undefined)
	{
		$("#form_mobile").html("手机号码不能为空！").fadeIn();
		return false;
	}
	else
	{
		if(!isMobileNo(v))
		{
			$("#form_mobile").html("手机号码不正确！").fadeIn();
			return false;
		}
		else
		{
			$("#form_mobile").html("").fadeOut();
			return true;
		}
	}
}

function validationPassword(v)
{
	var v = $("#user_password").val();
	if("" == v || null == v || v == undefined)
	{
		$("#form_password").html("密码不能为空！").fadeIn();
		return false;
	}
	return true;
}

function keySubmit(e)
{
	var evt = window.event || e; 
	if (evt.keyCode == 13)
	{
		$("#login_submit").click();
	}
}

function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if(cval != null)
	document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function getCookie(name)
{
	var arr,reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
} 
