package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmModules;
import com.ftw.entity.adm.AdmModulesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdmModulesDAO {
    long countByExample(AdmModulesExample example);

    int deleteByExample(AdmModulesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmModules record);

    int insertSelective(AdmModules record);

    List<AdmModules> selectByExample(AdmModulesExample example);

    AdmModules selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmModules record, @Param("example") AdmModulesExample example);

    int updateByExample(@Param("record") AdmModules record, @Param("example") AdmModulesExample example);

    int updateByPrimaryKeySelective(AdmModules record);

    int updateByPrimaryKey(AdmModules record);
}