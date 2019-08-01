package com.ftw.service.impl;


import com.ftw.dao.adm.AdmPermissionDAO;
import com.ftw.entity.adm.AdmPermission;
import com.ftw.entity.adm.AdmPermissionExample;
import com.ftw.service.AdmPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdmPermissionServiceImpl implements AdmPermissionService
{
    @Resource
    private AdmPermissionDAO admPermissionMapper;

    @Override
    public List<Map<String, Object>> getAllPermissions(Map<String, Object> parm) {
        return admPermissionMapper.getAllPermissions(parm);
    }

    @Override
    public int getAllPermissionsSize(Map<String, Object> parm) {
        return admPermissionMapper.getAllPermissionsSize(parm);
    }

    @Override
    public AdmPermission selectByPrimaryKey(Integer id) {
        return admPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(AdmPermission admPermission) {
        return admPermissionMapper.updateByPrimaryKey(admPermission);
    }

    @Override
    public int insertSelective(AdmPermission admPermission) {
        return admPermissionMapper.insertSelective(admPermission);
    }

    @Override
    public int deleteByPrimaryKey(Integer permissionId) {
        return admPermissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public List<AdmPermission> selectByExample(AdmPermissionExample example) {
        return admPermissionMapper.selectByExample(example);
    }
}
