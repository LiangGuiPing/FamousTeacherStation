package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 跨系统访问工具
 */
public class RestTemplateUtil 
{
	private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);

	/** 发送对象的POST请求 **/
    public static String  httpPostObject(String url, Object obj)
    {
        try 
        {
             RestTemplate restTemplate = RestTemplateUtil.getInstance("UTF-8");
             String result = restTemplate.postForObject(url, obj, String.class);
             if(null != result)
             {
            	 return result;
             }
        }
        catch (Exception e) 
        {
            logger.error("网络httpPostObject请求出错:{}", e);
        }
        return null;
    }
    
    /** RestTemplateUtil模板POST请求 **/
    public static String  httpPost(String url, Map<String, Object> paramMap)
    {
    	try 
    	{
    		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    		requestFactory.setConnectTimeout(1000);
    		requestFactory.setReadTimeout(1000);
    		RestTemplate restTemplate = new RestTemplate(requestFactory);
    		HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    		HttpEntity<MultiValueMap<String, Object>> maps = new HttpEntity<MultiValueMap<String, Object>>(popHeaders(paramMap), headers);
    		ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, maps, String.class);
    		String jsonResponse = stringResponseEntity.getBody();
    		if (StringUtils.isNotBlank(jsonResponse))
    		{
    			return jsonResponse;
    		}
    		return jsonResponse;
    	}
    	catch (Exception e) 
    	{
    		logger.error("网络POST请求出错:{}", e);
    	}
    	return null;
    }
    
    /** RestTemplateUtil模板GET请求 **/
    public static String  httpGet(String url)
    {
        try 
        {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);
            requestFactory.setReadTimeout(1000);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String jsonResponse = restTemplate.getForObject(url, String.class);
            if (StringUtils.isNotBlank(jsonResponse))
            {
                return jsonResponse;
            }
            return jsonResponse;
        }
        catch (Exception e) 
        {
            logger.error("网络GET请求出错:{}", e);
        }
        return null;
    }

    /** 对象转字符串 **/
	public static String formatObject(Object obj)
	{
		if(null == obj)
		{
			return "";
		}
		return obj.toString();
	}
	
	/** Model转MAP **/
    public static Map<String, Object> toMapByModel(Object obj)
    {
        Map<String, Object> map = new HashMap<>();
        if (obj == null)
        {
            return map;
        }
		Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try
        {
            for (Field field : fields)
            {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
            Class superClass = clazz.getSuperclass();
            if(null != superClass)
            {
            	Field[] superFields = superClass.getDeclaredFields();
                for (Field field : superFields)
                {
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(obj));
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Model转MAP出错:{}", e);
        }
        logger.info("Model转MAP结果为：{}" + map);
        return map;
    }

    /** 封装时间戳 **/
    public static JSONObject getPageJsonObject(String timeStamp, long total, Integer pageIndex, Integer pageSize, List lists)
    {
    	JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", total);
        jsonObject.put("pageIndex", pageIndex);
        jsonObject.put("pageSize", pageSize);
        jsonObject.put("timeStamp", timeStamp);
        jsonObject.put("datas", lists);
        return  jsonObject;
    }
    
    /** 传递参数封装 **/
    public static MultiValueMap<String, Object> popHeaders(Map<String, Object> map1)
    {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        for (Map.Entry<String, Object> entry : map1.entrySet())
        {
            map.add(entry.getKey(),entry.getValue());
        }
        return map;
    }
    
    /** 初始化RestTemplate **/
    public static RestTemplate getInstance(String charset)
    {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName(charset)));
        return restTemplate;
    }
    
    /** 对象转JSON并加密 **/
    public static String beanToJson(Object o)
    {
    	try
    	{
    		if(null == o)
    		{
    			return null;
    		}
			return URLEncoder.encode(JSON.toJSONString(o), "UTF-8");
		} 
    	catch (UnsupportedEncodingException e) 
    	{
    		logger.error("对象转JSON并加密:{}", e);
		}
    	return null;
    }
    
    /** 对象转JSON不加密 **/
    public static String objectToJson(Object o)
    {
    	try
    	{
    		if(null == o)
    		{
    			return null;
    		}
    		return JSON.toJSONString(o);
    	} 
    	catch (Exception e) 
    	{
    		logger.error("对象转JSON并加密:{}", e);
    	}
    	return null;
    }
    
    /**
     * @foundation       : JSON解密并转BEN对象
     * @param json       : JSON
     * @param obj        ：要转换的对象
     * @param needDecode ：是否需要解密
     * @return           : Object
     */
	public static Object jsonToBean(String json, Class obj, boolean needDecode)
    {
    	try 
    	{
    		if(StringUtils.isBlank(json))
    		{
    			return null;
    		}
    		if(needDecode)
    		{
    			return JSON.parseObject(URLDecoder.decode(json, "UTF-8"), obj);
    		}
    		else
    		{
    			return JSON.parseObject(json, obj);
    		}
		} 
    	catch (UnsupportedEncodingException e) 
    	{
    		logger.error("JSON解密并转BEN对象出错:{}", e);
		}
    	return null;
    }
    
}