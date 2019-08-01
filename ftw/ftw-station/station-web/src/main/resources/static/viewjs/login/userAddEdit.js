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
                loginname:
                    {
                        validators:
                            {
                                notEmpty:
                                    {
                                        message: '登录名不能为空！'
                                    },
                            }
                    },
                realname:
                    {
                        validators:
                            {
                                notEmpty:
                                    {
                                        message: '请填写用户的真实姓名！'
                                    },
                            }
                    },
                password:
                    {
                        validators:
                            {
                                notEmpty:
                                    {
                                        message: '登录密码不能为空！'
                                    },
                            }
                    },
                bizType:
                    {
                        validators:
                            {
                                notEmpty:
                                    {
                                        message: '请选择用户分类！'
                                    },
                            }
                    },
                status:
                    {
                        validators:
                            {
                                notEmpty:
                                    {
                                        message: '请选择用户是否启用！'
                                    },
                            }
                    }
            }
    })
    .on('success.form.bv', function (e) {
        e.preventDefault();
        submitdata();
    });

function bizTypeClick(obj) {
    if ($(obj).val() == '30') {
        $('#divCampus').removeClass('hide');
    }
    else
        $('#divCampus').addClass('hide');
}

function userExist(tel) {
    var id = parseInt($('#id').val());
    if (null != tel && "" != tel && undefined != tel && id == 0) {
        $.post("/user/userExist?loginname=" + tel, null, function (data) {
            if (data == true) {
                layer.open({content: "该登录名已存在", skin: 'msg', time: 3});
            }
        });
    }
}

function submitdata() {
    var loginname = $.trim($("#loginname").val());
    var password = $.trim($("#password").val());
    var realname = $.trim($("#realname").val());
    var biztype = $("input[name='bizType']:checked").val();
    var status = $.trim($("#status").val());
    var gardenId = $.trim($("#gardenId").val());
    var id = $.trim($("#id").val());
    $.ajax(
        {
            type: "POST",
            url: "/user/postSaveOrUpdateUsers",
            data: {
                loginname: loginname,
                password: password,
                realname: realname,
                status: status,
                id: id,
                gardenId: gardenId,
                bizType: biztype
            },
            success: function (data) {
                if (data > 0) {
                    window.location.href = "/user/list";
                }
                if (data == -1) {
                    layer.open({content: "该用户已经存在！", skin: 'msg', time: 2});
                }
            },
            error: function (data) {
                layer.open({content: "服务器出错", skin: 'msg', time: 2});
            }
        });
}

