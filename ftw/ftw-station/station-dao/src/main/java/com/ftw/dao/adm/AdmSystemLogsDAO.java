package com.ftw.dao.adm;

import com.ftw.entity.adm.AdmSystemLogs;
import com.ftw.entity.adm.AdmSystemLogsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdmSystemLogsDAO {
    long countByExample(AdmSystemLogsExample example);

    int deleteByExample(AdmSystemLogsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdmSystemLogs record);

    int insertSelective(AdmSystemLogs record);

    List<AdmSystemLogs> selectByExample(AdmSystemLogsExample example);

    AdmSystemLogs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdmSystemLogs record, @Param("example") AdmSystemLogsExample example);

    int updateByExample(@Param("record") AdmSystemLogs record, @Param("example") AdmSystemLogsExample example);

    int updateByPrimaryKeySelective(AdmSystemLogs record);

    int updateByPrimaryKey(AdmSystemLogs record);
}