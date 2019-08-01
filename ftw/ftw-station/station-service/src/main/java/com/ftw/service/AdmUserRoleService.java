package com.ftw.service;

import com.ftw.entity.adm.AdmUserRole;
import com.ftw.entity.adm.AdmUserRoleExample;

import java.util.List;

public interface AdmUserRoleService
{
	List<AdmUserRole> selectByExample(AdmUserRoleExample admuserroleexample);

	int deleteByPrimaryKey(Integer id);

	int bathDeleteUserRole(List<AdmUserRole> list);

	int bathAddUserRole(List<AdmUserRole> recordlist);
}
