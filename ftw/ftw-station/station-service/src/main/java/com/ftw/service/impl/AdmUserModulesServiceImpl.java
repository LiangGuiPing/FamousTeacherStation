package com.ftw.service.impl;


import com.alibaba.fastjson.JSON;
import com.ftw.dao.adm.AdmUsersModulesDAO;
import com.ftw.entity.adm.AdmUsersModules;
import com.ftw.entity.adm.AdmUsersModulesExample;
import com.ftw.service.AdmUserModulesService;
import com.util.constant.RedisKeyConstants;
import com.util.page.PagingUtil;
import com.util.page.ResultEntity;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AdmUserModulesServiceImpl implements AdmUserModulesService
{
	@Resource
    private AdmUsersModulesDAO admUsersModulesMapper;

	@Resource
    private AdmUsersModulesDAO admusersmodulesmapper;

	@Resource
	private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<AdmUsersModules> selectByExample(AdmUsersModulesExample ame) {
        return admUsersModulesMapper.selectByExample(ame);
    }

    @Override
    public Integer deleteByPrimaryKey(Integer id) {
        return admUsersModulesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteModuleByModuleIdAndUserId(Map<String, Object> param) {
        return admUsersModulesMapper.deleteModuleByModuleIdAndUserId(param);
    }

    @Override
    public Integer insertSelective(AdmUsersModules admusersmodules2) {
        return admUsersModulesMapper.insertSelective(admusersmodules2);
    }

    @Override
    public List<Map<String, Object>> getsecondModulesByParentId(Map<String, Object> param) {
        return admUsersModulesMapper.getsecondModulesByParentId(param);
    }

    @Override
    public List<Map<String, Object>> getAllParentModules(Map<String, Object> param) {
        return admUsersModulesMapper.getAllParentModules(param);
    }

    @Override
    public List<Map<String, Object>> getAllModules(Map<String, Object> parm) {
        return admUsersModulesMapper.getAllModules(parm);
    }

    @Override
    public Integer getAllModulesSize(Map<String, Object> parm) {
        return admUsersModulesMapper.getAllModulesSize(parm);
    }

    @Override
    public List<Map<String, Object>> fetchModules() {
        return admUsersModulesMapper.fetchModules();
    }

    @Override
    public List<Map<String, Object>> getParentModuleByUser(Map<String, Object> param) {
        return admUsersModulesMapper.getParentModuleByUser(param);
    }

    @Override
    public List<Map<String, Object>> getSubModuleByParentId(Map<String, Object> param) {
        return admUsersModulesMapper.getSubModuleByParentId(param);
    }

	@Override
	public ResultEntity saveOrUpdateUserModule(Integer userId, String ids)
	{
		ResultEntity resultentity = ResultEntity.getErrorResult();
		AdmUsersModules admusersmodules = null;
		List<AdmUsersModules> items = new ArrayList<>();
		if(ids.contains(","))
		{
			String[] array = ids.split(",");
			for(String s : array)
			{
				admusersmodules = new AdmUsersModules();
				admusersmodules.setUserid(userId);
				admusersmodules.setModuleid(Integer.parseInt(s));
				items.add(admusersmodules);
			}
		}
		else
		{
			admusersmodules = new AdmUsersModules();
			admusersmodules.setUserid(userId);
			admusersmodules.setModuleid(Integer.parseInt(ids));
			items.add(admusersmodules);
		}
		Map<String, Object> param = PagingUtil.getMapByVariableParam("userId", userId);
		admUsersModulesMapper.deleteUserAllModuleByUserId(param);
		admUsersModulesMapper.insertByBatch(items);
		GetmodulesFromDB(userId);

		return ResultEntity.getSuccessResult(resultentity);
	}
	@Override
	public List<Map<String, Object>> GetmodulesFromDB(Integer userId) {
		//查询模块保存到REDIS
		List<Map<String, Object>> datas = new ArrayList<>();
		if(null != userId)
		{
			Map<String, Object> param =new HashMap<>();
			param.put("userId", userId);
			List<Map<String, Object>> parentModules = admusersmodulesmapper.getParentModuleByUser(param);
			if(null != parentModules && parentModules.size() > 0)
			{
				for(Map<String, Object> m : parentModules)
				{
					Map<String, Object> parentMap = new HashMap<>();
					int moduleId = Integer.parseInt(m.get("moduleId").toString());
					parentMap.put("moduleId", moduleId);
					parentMap.put("moduleName", m.get("moduleName"));

					param.put("parentId", moduleId);
					List<Map<String, Object>> subModules = admusersmodulesmapper.getSubModuleByParentId(param);
					parentMap.put("subModules", subModules);
					datas.add(parentMap);
				}
			}
		}
		if(datas.size() > 0)
		{
			redisTemplate.opsForValue().set(RedisKeyConstants.FTW_USER_MODULES + userId, JSON.toJSONString(datas), 365, TimeUnit.DAYS);
		}
		return datas;
	}
}
