package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmPermission;
import com.ftw.entity.adm.AdmPermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdmPermissionDAO {
    long countByExample(AdmPermissionExample example);

    int deleteByExample(AdmPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmPermission record);

    int insertSelective(AdmPermission record);

    List<AdmPermission> selectByExample(AdmPermissionExample example);

    AdmPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmPermission record, @Param("example") AdmPermissionExample example);

    int updateByExample(@Param("record") AdmPermission record, @Param("example") AdmPermissionExample example);

    int updateByPrimaryKeySelective(AdmPermission record);

    int updateByPrimaryKey(AdmPermission record);

    List<Map<String,Object>> getAllPermissions(Map<String, Object> parm);

    int getAllPermissionsSize(Map<String, Object> parm);
}