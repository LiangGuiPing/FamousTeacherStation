package com.ftw.service.impl;


import com.ftw.dao.adm.AdmRolesDAO;
import com.ftw.entity.adm.AdmRoles;
import com.ftw.entity.adm.AdmRolesExample;
import com.ftw.service.AdmRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdmRolesServiceImpl implements AdmRolesService
{
    @Resource
    private AdmRolesDAO admRolesMapper;

    @Override
    public List<AdmRoles> selectByExample(AdmRolesExample admRolesExample) {
        return admRolesMapper.selectByExample(admRolesExample);
    }

    @Override
    public List<Map<String, Object>> getAllRoles(Map<String, Object> parm) {
        return admRolesMapper.getAllRoles(parm);
    }

    @Override
    public int getAllRolesSize(Map<String, Object> parm) {
        return admRolesMapper.getAllRolesSize(parm);
    }

    @Override
    public AdmRoles selectByPrimaryKey(Integer id) {
        return admRolesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(AdmRoles admRole) {
        return admRolesMapper.updateByPrimaryKey(admRole);
    }

    @Override
    public int insertSelective(AdmRoles admRole) {
        return admRolesMapper.insertSelective(admRole);
    }

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return admRolesMapper.deleteByPrimaryKey(roleId);
    }
}
