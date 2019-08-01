package com.ftw.service.impl;

import com.ftw.dao.adm.AdmModulesDAO;
import com.ftw.entity.adm.AdmModules;
import com.ftw.service.AdmModulesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdmModulesServiceImpl implements AdmModulesService {

    @Resource
    private AdmModulesDAO admModulesMapper;

    @Override
    public AdmModules selectByPrimaryKey(Integer id) {
        return admModulesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(AdmModules m) {
        return admModulesMapper.updateByPrimaryKey(m);
    }

    @Override
    public int insertSelective(AdmModules m) {
        return admModulesMapper.insertSelective(m);
    }

    @Override
    public int deleteByPrimaryKey(Integer moduleid) {
        return admModulesMapper.deleteByPrimaryKey(moduleid);
    }
}
