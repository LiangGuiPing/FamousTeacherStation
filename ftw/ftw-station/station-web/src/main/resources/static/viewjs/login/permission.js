$(function () {
var vm = new Vue(
    {
        el: '#vueapp',
        data:
        {
            dataList: [],  //数据
            roleLists: [],  //数据
            all: 0, //总页数
            pagesize: 10, //每页显示的条数
            cur: 1, //当前页码
            total: 0  //总记录数
        },
        watch:
        {
            cur: function(oldValue , newValue) {}
        },
        methods:
        {
            deletem : function (permissionId)
            {
                layer.open(
                    {
                        content: '您确定要删除该记录吗？',btn: ['确定', '取消'],yes: function(index)
                    {
                        $.post("/permission/delete?permissionId=" + permissionId, null, function(f)
                        {
                            if(f == 1)
                            {
                                layer.open({content : "删除成功！", skin: 'msg', time: 2});
                                window.setTimeout(function()
                                {
                                    window.location.reload();
                                },1000);
                            }
                            else
                            {
                                layer.open({content : "服务器出错！", skin: 'msg', time: 2});
                            }
                        });
                        setTimeout(function(){layer.close(index);},300)
                    }
                    });
            },
            search: function () {
                this.goto(1);
            },
            goto: function (index) {
                if (index == this.current && this.current != 1) return;
                var permissionName = $("#permissionName").val();
                var roleId = $("#roleIds").find("option:selected").val();

                // console.log("----------------------roleId="+roleId)

                this.current = index;
                $.ajax(
                    {
                        type: "post",
                        url: "/permission/getList",
                        data: {pageNo: index, pageSize: this.pagesize, permissionName: permissionName,roleId:roleId},
                        success: function (data)
                        {
                            vm.dataList = data.datas;
                            vm.total = data.size;
                            vm.all = parseInt(data.size / vm.pagesize) + (data.size % vm.pagesize > 0 ? 1 : 0);
                            if (vm.dataList.length > 0)
                            {
                                $("#dataListPanel").removeClass("hide");
                            }
                        },
                        error: function (data)
                        {
                            layer.open({content : "服务器出错！", skin: 'msg', time: 2});
                        }
                    });
            },
            //页码点击事件
            btnClick: function(data)
            {
                if(data != this.cur)
                {
                    this.cur = data
                }
                this.goto(data);
            },
            //上一页、下一页点击事件
            pageClick: function()
            {
                console.log('现在在'+this.cur+'页');
                this.goto(this.cur);
            },
            searchRole: function (index) {
                $.ajax(
                    {
                        type: "post",
                        url: "/permission/getRole",
                        data: {},
                        success: function (data)
                        {
                            vm.roleLists = data.datas;
                            // alert(JSON.stringify(data.datas));

                            if (vm.roleLists.length > 0)
                            {
                                $("#dataListPanel").removeClass("hide");
                            }
                        },
                        error: function (data)
                        {
                            layer.open({content : "服务器出错！", skin: 'msg', time: 2});
                        }
                    });
            }
        },
        created: function ()
        {
            this.goto(1);
            this.searchRole();
        },
        computed:
        {
            indexs: function()
            {
                var left = 1;
                var right = this.all;
                var ar = [];
                if(this.all >= 5)
                {
                    if(this.cur > 3 && this.cur < this.all-2)
                    {
                        left = this.cur - 2
                        right = this.cur + 2
                    }
                    else
                    {
                        if(this.cur<=3)
                        {
                            left = 1
                            right = 5
                        }
                        else
                        {
                            right = this.all
                            left = this.all -4
                        }
                    }
                }
                while (left <= right)
                {
                    ar.push(left)
                    left ++
                }
                return ar
            }
        }
    });
});