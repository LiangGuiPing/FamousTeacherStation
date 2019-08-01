$(function () 
{
    var vm = new Vue(
    {
        el: '#vueapp',
        data: 
        {
            dataList: [],  //数据
            all: 0, //总页数
            pagesize: 10, //每页显示的条数
            cur: 1, //当前页码
            total: 0  //总记录数
        },
        watch: 
        {
            cur: function(oldValue , newValue)
            {
                console.log(arguments);
            }
        },  
        methods:
        {
            disabledUser : function (id, status)
            {
            	var massage = "你确定要禁用该用户吗？禁用之后该用户是不能登录的哦！";
            	if(1 == status)
            	{
            		massage = "你确定要启用该用户吗？启用之后该用户就可以登录了哦！";
            	}
            	
        		layer.open(
        		{
        			content: massage, btn: ['确定', '取消'], yes: function(index)
        		    {
        	        	$.post("/user/disabledUser?id=" + id + "&status=" + status, null, function(f)
                    	{
                    		if(f > 0)
                    		{
                    			layer.open({content : "操作成功！", skin: 'msg', time: 2});
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
        				setTimeout(function(){layer.close(index);},300);     
        		    }
        		});
            },
            deletem : function (id)
            {
            	layer.open(
    			{
    				content: '您确定要删除该会员吗？其相应的模块和角色都会被删除，确定吗？',btn: ['确定', '取消'],yes: function(index)
    				{
    					$.post("/user/delete?id=" + id, null, function(f)
    					{
    						if(f > 0)
    						{
    							layer.open({content : "删除成功！", skin: 'msg', time: 2});
    							window.setTimeout(function(){window.location.reload();},1000);
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
            search: function ()
            {
                this.goto(1);
            },
            goto: function (index) 
            {
                if (index == this.current && this.current != 1) return;
                var loginName = $("#loginName").val();
                this.current = index;
                $.ajax(
                {
                    type: "post",
                    url: "/user/getList",
                    data: {pageNo: index, pageSize: this.pagesize, loginName: loginName},
                    success: function (data)
                    {
                        vm.dataList = data.datas;
                        //console.log(vm.dataList);
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
            }
        },
        created: function () 
        {
            this.goto(1);
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
