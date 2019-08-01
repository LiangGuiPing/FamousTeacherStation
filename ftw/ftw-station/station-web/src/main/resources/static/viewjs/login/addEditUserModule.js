//父模块的选择与取消
function _selectAllSecondModules(obj)
{
	var isChecked = $(obj).prop("checked");
	var v = obj.value;
	var ids = "";
	
	//选中
	if(isChecked)
	{
		$("#parentid_"+v).find("input").each(function()
		{
			var p = $(this).val();
			ids += p + ",";
			$(this).prop('checked', true);
		});
		ids = ids.substring(0, ids.length - 1);
		$("#ids_sets_" + v).val(v + "," + ids);
	}
	
	//取消选中
	else
	{
		$("#parentid_"+v).find("input").each(function()
		{
			$(this).prop('checked', false);
		});
		$("#ids_sets_" + v).val("");
	}
}

//子模块的选择与取消
function _selectSigleSecondModules(obj)
{
	var parentId = $(obj).parent().parent().parent().attr("id");
	parentId = parentId.replace("parentid_", "");
	var isChecked = $(obj).prop("checked");
	var v = obj.value;
		
	//所有ID集合
	var pids = $("#ids_sets_" + parentId).val();
		
	//选中
	if(isChecked)
	{
		$(obj).prop('checked', true);
		$("#parentModuleId_"+parentId).prop('checked', true);
		pids = pids + "," + v;
		$("#ids_sets_" + parentId).val(pids);
	}
		
	//取消选中
	else
	{
		$(obj).prop('checked', false);
		pids = cancelChecked(pids, v);
		if(pids.indexOf(",") != -1)
		{
			pids = pids.substring(0, pids.length - 1);
		}
		$("#ids_sets_" + parentId).val(pids);
	}
}

function cancelChecked(b, n)
{
	var a = "";
	if(b.indexOf(",") != -1)
	{
		var c = b.split(",");
		for(var i in c)
		{
			if(parseInt(c[i]) != parseInt(n))
			{
				a += c[i] + "," ;
			}
		}
	}
	else
	{
		a = b;
	}
	return a;
}

var ids = "";

$("#saveBtn").click(function()
{
	ids = "";
	$(".tbody").find("tr").each(function()
	{
		var id = $(this).find("input[type='hidden']").val();
		var isChecked = $(this).find("input[type='checkbox']").prop("checked");
		if(null != id && "" != id && undefined != id && isChecked)
		{
			ids += id + ",";
		}
	});
	if(ids.indexOf(",") != -1)
	{
		ids = ids.substring(0, ids.length - 1);
	}
	var userId = $.trim($("#userid").val());
	$(".spinner").show();
	$(".mask-div-gray").show();
	window.setTimeout(function()
	{
		$.post("/user/saveOrUpdateUserModule", {"userId":userId, "ids":ids}, function(data)
		{
			$(".spinner").hide();
			$(".mask-div-gray").hide();
			window.setTimeout(function()
			{
				if(data == 0)
				{
					layer.open({content : "操作成功", skin: 'msg', time: 2});
				}
				else
				{
					layer.open({content : "操作失败", skin: 'msg', time: 2});
				}
			},100);
			window.setTimeout(function()
			{
				window.location.reload();
			},1000);
		});
	},500);
});
