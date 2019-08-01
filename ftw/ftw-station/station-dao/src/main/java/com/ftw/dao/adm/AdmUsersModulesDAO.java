package com.ftw.dao.adm;


import com.ftw.entity.adm.AdmUsersModules;
import com.ftw.entity.adm.AdmUsersModulesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdmUsersModulesDAO 
{
    long countByExample(AdmUsersModulesExample example);

    int deleteByExample(AdmUsersModulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmUsersModules record);

    int insertSelective(AdmUsersModules record);

    List<AdmUsersModules> selectByExample(AdmUsersModulesExample example);

    AdmUsersModules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmUsersModules record, @Param("example") AdmUsersModulesExample example);

    int updateByExample(@Param("record") AdmUsersModules record, @Param("example") AdmUsersModulesExample example);

    int updateByPrimaryKeySelective(AdmUsersModules record);

    int updateByPrimaryKey(AdmUsersModules record);
    
    List<Map<String, Object>> getAllModules(Map<String, Object> map);
    
    int getAllModulesSize(Map<String, Object> map);
    
    List<Map<String, Object>> fetchModules();
    
    List<Map<String, Object>> getParentModuleByUser(Map<String, Object> map);
    
    List<Map<String, Object>> getSubModuleByParentId(Map<String, Object> map);
    
    List<Map<String, Object>> getAllParentModules(Map<String, Object> map);
    
    List<Map<String, Object>> getsecondModulesByParentId(Map<String, Object> map);
    
    Integer deleteModuleByModuleIdAndUserId(Map<String, Object> map);
    
    /**
     * 根据用户ID删除该用户所有模块
     * @param param
     */
    public void deleteUserAllModuleByUserId(Map<String, Object> param);
    
    /**
     * 批量插入数据
     * @param items
     */
    public void insertByBatch(@Param("items") List<AdmUsersModules> items);
    
}