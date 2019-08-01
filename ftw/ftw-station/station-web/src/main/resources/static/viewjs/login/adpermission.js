// $(function () {
        var vm = new Vue(
            {
                el: '#vueapp',
                data: {
                    model: admPermission
                },
                methods: {
                    submitdata: function () {
                        var that = this.model;
                        $.ajax(
                            {
                                type: "post",
                                url: "/permission/postAddEditPermission",
                                data: {roleId: that.roleid, permissionId: that.id,permissionName:that.name,permissionDesc:that.permissionDesc},
                                success: function (data) {
                                    if (data > 0) {
                                        window.location.href = "/permission/list";
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
        $('#form1').bootstrapValidator(
            {
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }
                ,
                fields: {
                    permissionName: {
                        validators: {
                            notEmpty: {
                                message: '权限名称不能为空！'
                            },
                        }
                    },
                    roleId: {
                        validators: {
                            notEmpty: {
                                message: '请选择角色类型！'
                            },
                        }
                    }
                }
            })
            .on('success.form.bv', function (e) {
                e.preventDefault();
                vm.submitdata();
            });
       // });


