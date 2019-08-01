package com.ftw.dao.adm;


import com.ftw.entity.adm.AdmRoles;
import com.ftw.entity.adm.AdmRolesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdmRolesDAO {
    long countByExample(AdmRolesExample example);

    int deleteByExample(AdmRolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmRoles record);

    int insertSelective(AdmRoles record);

    List<AdmRoles> selectByExample(AdmRolesExample example);

    AdmRoles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmRoles record, @Param("example") AdmRolesExample example);

    int updateByExample(@Param("record") AdmRoles record, @Param("example") AdmRolesExample example);

    int updateByPrimaryKeySelective(AdmRoles record);

    int updateByPrimaryKey(AdmRoles record);

    List<Map<String,Object>> getAllRoles(Map<String, Object> parm);

    int getAllRolesSize(Map<String, Object> parm);
}