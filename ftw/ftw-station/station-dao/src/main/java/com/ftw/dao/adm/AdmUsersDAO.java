package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmUsers;
import com.ftw.entity.adm.AdmUsersExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdmUsersDAO {
    long countByExample(AdmUsersExample example);

    int deleteByExample(AdmUsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmUsers record);

    int insertSelective(AdmUsers record);

    List<AdmUsers> selectByExample(AdmUsersExample example);

    AdmUsers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmUsers record, @Param("example") AdmUsersExample example);

    int updateByExample(@Param("record") AdmUsers record, @Param("example") AdmUsersExample example);

    int updateByPrimaryKeySelective(AdmUsers record);

    int updateByPrimaryKey(AdmUsers record);
    int updatePwdByPrimaryKey(@Param("id") int id, @Param("password") String password);

    
    /**
     * Get all management users with paging
     */
    List<Map<String, Object>> getAllAdmUserOrderByCreateTime(Map<String, Object> param);
    
    /**
     * Get all management users count
     */
    int getAllAdmUserCountOrderByCreateTime(Map<String, Object> param);
    
}