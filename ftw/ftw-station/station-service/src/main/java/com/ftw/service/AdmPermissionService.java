package com.ftw.service;


import com.ftw.entity.adm.AdmPermission;
import com.ftw.entity.adm.AdmPermissionExample;

import java.util.List;
import java.util.Map;

public interface AdmPermissionService
{


    List<Map<String, Object>> getAllPermissions(Map<String, Object> parm);

    int getAllPermissionsSize(Map<String, Object> parm);

    AdmPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdmPermission admPermission);

    int insertSelective(AdmPermission admPermission);

    int deleteByPrimaryKey(Integer permissionId);

    List<AdmPermission> selectByExample(AdmPermissionExample example);
}
