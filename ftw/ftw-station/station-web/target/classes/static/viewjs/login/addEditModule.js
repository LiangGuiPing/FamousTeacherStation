$(function () 
{
    var vm = new Vue(
    {
        el: '#vueapp',
        data: 
        {
        	model: admmodules
        },
        methods: 
        {
            submitdata: function () 
            {
            	var that = this.model;
                $.ajax(
                {
                    type: "post",
                    url: "/module/postAddEditModule",
                    data: {modulename:that.modulename,parentid:that.parentid,visiturl:that.visiturl,displaysort:that.displaysort,isdisplay:that.isdisplay,remark:that.remark,moduleid:that.id},
                    success: function (data) 
                    {
                    	if(data > 0)
                    	{
                    		window.location.href = "/module/list";
                    	}
                    	else
                    	{
                    		layer.open({content : "服务器出错", skin: 'msg', time: 2});
                    	}
                    },
                    error: function (data)
                    {
                    	layer.open({content : "服务器出错", skin: 'msg', time: 2});
                    }
                });
            }
        },
        created: function () {}
    });
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
            	modulename: 
                {
                    validators: 
                    {
                        notEmpty: 
                        {
                            message: '模块名称不能为空！'
                        },
                    }
                },
                /**************************************
                parentid: 
                {
                    validators: 
                    {
                        notEmpty: 
                        {
                            message: '请选择父模块！'
                        },
                    }
                },
                visiturl: 
                {
                	validators: 
                	{
                		notEmpty: 
                		{
                			message: '请求URL不能为空！'
                		},
                	}
                },
                **************************************/
                displaysort: 
                {
                	validators: 
                	{
                		notEmpty: 
                		{
                			message: '模块的显示顺序不能为空！'
                		},
                		regexp: 
                		{
                            regexp: /^[0-9]+$/,
                            message: '模块的显示顺序只能为0-9的数字！'
                        },
                	}
                },
                isdisplay: 
                {
                	validators: 
                	{
                		notEmpty: 
                		{
                			message: '是否显示该模块不能为空！'
                		},
                	}
                }
            }
    })
    .on('success.form.bv', function(e) 
    {
        e.preventDefault();
        vm.submitdata();
    });
});