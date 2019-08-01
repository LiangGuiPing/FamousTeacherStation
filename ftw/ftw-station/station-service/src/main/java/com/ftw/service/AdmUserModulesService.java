package com.ftw.service;


import com.ftw.entity.adm.AdmUsersModules;
import com.ftw.entity.adm.AdmUsersModulesExample;
import com.util.page.ResultEntity;

import java.util.List;
import java.util.Map;

public interface AdmUserModulesService
{
    List<AdmUsersModules> selectByExample(AdmUsersModulesExample ame);

    Integer deleteByPrimaryKey(Integer id);

    Integer deleteModuleByModuleIdAndUserId(Map<String, Object> param);

    Integer insertSelective(AdmUsersModules admusersmodules2);

    List<Map<String, Object>> getsecondModulesByParentId(Map<String, Object> param);

    List<Map<String, Object>> getAllParentModules(Map<String, Object> param);

    List<Map<String, Object>> getAllModules(Map<String, Object> parm);

    Integer getAllModulesSize(Map<String, Object> parm);

    List<Map<String, Object>> fetchModules();

    List<Map<String, Object>> getParentModuleByUser(Map<String, Object> param);

    List<Map<String, Object>> getSubModuleByParentId(Map<String, Object> param);
    
    public ResultEntity saveOrUpdateUserModule(Integer userId, String ids);

    List<Map<String, Object>> GetmodulesFromDB(Integer userId);
}
