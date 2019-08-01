package com.ftw.service;


import com.ftw.entity.adm.AdmModules;

public interface AdmModulesService
{


    AdmModules selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdmModules m);

    int insertSelective(AdmModules m);

    int deleteByPrimaryKey(Integer moduleid);
}
