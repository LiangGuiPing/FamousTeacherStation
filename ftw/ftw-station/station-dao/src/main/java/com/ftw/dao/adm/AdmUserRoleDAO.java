package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmUserRole;
import com.ftw.entity.adm.AdmUserRoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdmUserRoleDAO {
    long countByExample(AdmUserRoleExample example);

    int deleteByExample(AdmUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmUserRole record);

    int insertSelective(AdmUserRole record);

    List<AdmUserRole> selectByExample(AdmUserRoleExample example);

    AdmUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmUserRole record, @Param("example") AdmUserRoleExample example);

    int updateByExample(@Param("record") AdmUserRole record, @Param("example") AdmUserRoleExample example);

    int updateByPrimaryKeySelective(AdmUserRole record);

    int updateByPrimaryKey(AdmUserRole record);

    int bathAddUserRole(List<AdmUserRole> recordlist);

    int bathDeleteUserRole(List<AdmUserRole> admUserRoles);
}