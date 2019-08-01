new Vue(
{
	el : "#nav",
	data : 
	{
		items : [ ]
	},
	methods : 
	{
		showToggle : function(item, items) 
		{
			for(var i in items)
			{
				if(item != items[i])
				{
					items[i].isSubshow = false;
				}
			}
			item.isSubshow = !item.isSubshow;
		},
		jumpToUrl : function(url)
		{
			$("#iframeSrc").attr("src", url);
		}
	},
	created : function()
	{
		var jsonItems = []; //[{"name":"", "isSubshow":false, "subItems":[{"name":"", "toUrl":""}]}]; 
		var t = this;
        $.ajax(
        {
            type: "POST",
            url: "/module/getModulesByUser",
            data: {},
            success: function (data)
            {
            	var z = data.datas;
            	console.log((JSON.stringify(z)));
            	for(var i in z)
            	{
            		var o = z[i];
            		var moduleName = o.moduleName;
            		var subModules = o.subModules;
            		var subItems = []; // [{"name":"", "toUrl":""}];
            		for(var j in subModules)
            		{
            			var name = subModules[j].name;
            			var url = subModules[j].url;
            			subItems.push({"name":name, "toUrl": url});
            		}
            		jsonItems.push({"name":moduleName, "isSubshow":false, "subItems":subItems});
            	}
            	t.items = jsonItems;
            },
            error: function (data) 
            {
            	layer.open({content : "服务器出错！", skin: 'msg', time: 2});
            }
        });
	}
});
