$('form').bootstrapValidator(
{
        feedbackIcons: 
        {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: 
        {
            password: 
            {
            	validators: 
            	{
            		notEmpty: 
            		{
            			message: '请输入新密码！'
            		},
            	}
            },
        }
})
.on('success.form.bv', function(e)
{
    e.preventDefault();
    submitdata();
});

function submitdata()
{
	var password = $.trim($("#password").val());
	var userId = $.trim($("#userId").val());
    var isMe = $.trim($("#isMe").val());
    $.ajax(
    {
        type: "POST",
        url: "/user/changePassword",
        data: {password:password,userId:userId},
        success: function (data) 
        {
        	if(data > 0)
        	{
        	    if(isMe=='0')
                    window.location.href = "/user/list";
        	    else
                    layer.open({content : "密码修改成功！", skin: 'msg', time: 2});
        	}
        	else
        	{
        		layer.open({content : "密码修改失败，请稍后重试！", skin: 'msg', time: 2});
        	}
        },
        error: function (data)
        {
        	layer.open({content : "服务器出错", skin: 'msg', time: 2});
        }
    });
}

