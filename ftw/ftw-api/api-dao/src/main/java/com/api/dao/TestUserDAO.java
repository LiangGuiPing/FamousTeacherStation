package com.api.dao;

import com.api.entity.result.TUserJsonResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestUserDAO {

    List<TUserJsonResult> listUser();
}
