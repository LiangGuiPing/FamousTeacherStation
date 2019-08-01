package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmPlatform;
import com.ftw.entity.adm.AdmPlatformExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdmPlatformDAO {
    long countByExample(AdmPlatformExample example);

    int deleteByExample(AdmPlatformExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmPlatform record);

    int insertSelective(AdmPlatform record);

    List<AdmPlatform> selectByExample(AdmPlatformExample example);

    AdmPlatform selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmPlatform record, @Param("example") AdmPlatformExample example);

    int updateByExample(@Param("record") AdmPlatform record, @Param("example") AdmPlatformExample example);

    int updateByPrimaryKeySelective(AdmPlatform record);

    int updateByPrimaryKey(AdmPlatform record);
}