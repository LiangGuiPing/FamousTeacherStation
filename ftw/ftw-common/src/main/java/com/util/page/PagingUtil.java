package com.util.page;

import java.util.HashMap;
import java.util.Map;

/**
 * @foundation : 分页自定义工具类
 * @author     ：LiangGuiPing
 * @createDate : 2018.10.31
 */
public class PagingUtil 
{
	/**
	 * Fetch MAP by variable parameter
	 */
	public static Map<String, Object> getMapByVariableParam(String strs, Object...values)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		if(strs.indexOf(",") != -1)
		{
			String[] keys = strs.split(",");
			if(values.length > 0)
			{
				for(int i=0; i<keys.length; i++)
				{
					for(int j=0; j<values.length; j++)
					{
						if(i == j)
						{
							param.put(keys[i], values[j]);
						}
					}
				}
			}
		}
		else
		{
			param.put(strs, values[0]);
		}
		return param;
	}
	
	
	public static void main(String[] args)
	{
		Map<String, Object> param = PagingUtil.getMapByVariableParam("offSet",1);
		System.out.println(param);
	}
}
