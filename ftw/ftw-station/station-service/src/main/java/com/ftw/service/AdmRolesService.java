package com.ftw.service;


import com.ftw.entity.adm.AdmRoles;
import com.ftw.entity.adm.AdmRolesExample;

import java.util.List;
import java.util.Map;

public interface AdmRolesService
{
    List<AdmRoles> selectByExample(AdmRolesExample admRolesExample);

    List<Map<String, Object>> getAllRoles(Map<String, Object> parm);

    int getAllRolesSize(Map<String, Object> parm);

    AdmRoles selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdmRoles admRole);

    int insertSelective(AdmRoles admRole);

    int deleteByPrimaryKey(Integer roleId);
}
