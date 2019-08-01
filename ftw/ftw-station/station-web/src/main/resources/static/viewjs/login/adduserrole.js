var vm = new Vue(
    {
        el: '#vueapp',
        data: {
            model: admAllRoles,
            userId:userId
        },
        methods: {
            submitdata: function () {
                var that = this.model;
                var roleIds = "";
                 roleIds = $("input:checkbox[name='roleIdL']:checked").map(function(index,elem) {
                    return $(elem).val();
                }).get().join(',');
                $.ajax(
                    {
                        type: "post",
                        url: "/user/userBindRole",
                        data: {userId: this.userId, roleIds: roleIds},
                        success: function (data) {
                            if (data > 0) {
                                window.location.href = "/user/list";
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
$('#form3').bootstrapValidator(
    {
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }

    })
    .on('success.form.bv', function (e) {
        e.preventDefault();
        vm.submitdata();
    });


