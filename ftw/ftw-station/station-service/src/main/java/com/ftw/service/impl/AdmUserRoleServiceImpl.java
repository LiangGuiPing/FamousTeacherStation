package com.ftw.service.impl;

import com.ftw.dao.adm.AdmUserRoleDAO;
import com.ftw.entity.adm.AdmUserRole;
import com.ftw.entity.adm.AdmUserRoleExample;
import com.ftw.service.AdmUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdmUserRoleServiceImpl implements AdmUserRoleService
{
	@Resource
	private AdmUserRoleDAO admUserRoleMapper;

	@Override
	public List<AdmUserRole> selectByExample(AdmUserRoleExample admuserroleexample) {
		return admUserRoleMapper.selectByExample(admuserroleexample);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return admUserRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int bathDeleteUserRole(List<AdmUserRole> list) {
		return admUserRoleMapper.bathDeleteUserRole(list);
	}

	@Override
	public int bathAddUserRole(List<AdmUserRole> recordlist) {
		return admUserRoleMapper.bathAddUserRole(recordlist);
	}
}
