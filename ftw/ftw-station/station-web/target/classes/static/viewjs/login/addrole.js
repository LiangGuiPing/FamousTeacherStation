        var vm = new Vue(
            {
                el: '#vueapp',
                data: {
                    model: admRole
                },
                methods: {
                    submitdata: function () {
                        var that = this.model;
                        $.ajax(
                            {
                                type: "post",
                                url: "/role/postAddEditRole",
                                data: {roleDesc: that.roledesc, roleId: that.id,roleName:that.rolename},
                                success: function (data) {
                                    if (data > 0) {
                                        window.location.href = "/role/list";
                                    }
                                    else {
                                        layer.open({content: "服务器出错", skin: 'msg', time: 2});
                                    }
                                },
                                error: function (data) {
                                    layer.open({content: "服务器出错", skin: 'msg', time: 2});
                                }
                            });
                    }
                },
                created: function () {
                }
            });
        $('#form2').bootstrapValidator(
            {
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }
                ,
                fields: {
                    rolename: {
                        validators: {
                            notEmpty: {
                                message: '权限名称不能为空！'
                            },
                        }
                    }
                }
            })
            .on('success.form.bv', function (e) {
                e.preventDefault();
                vm.submitdata();
            });


