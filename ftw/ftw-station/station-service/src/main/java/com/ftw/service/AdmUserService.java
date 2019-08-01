package com.ftw.service;

import com.ftw.entity.adm.AdmUserRole;
import com.ftw.entity.adm.AdmUsers;
import com.ftw.entity.adm.AdmUsersExample;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Fetch ADM user info service
 * @author LGP
 * @date 2018/09/14
 */
public interface AdmUserService
{
    /**
     * Get AdmUser by telephone
     */
	public AdmUsers findUserByMobile(String telephone);
	public AdmUsers findUserByLoginName(String loginName);
	/**
	 * Get roles of AdmUser
	 */
	public Set<String> getRoles(AdmUsers admusers);
	
	/**
	 * Get Permissions By RoleId
	 */
	public List<String> getPermissionsByRoleId(AdmUsers admusers);
	
	/**
	 * GetAdmUserRole List
	 */
	public List<AdmUserRole> getAdmUserRole(AdmUsers admusers);
	
	List<Map<String, Object>> getAllAdmUserOrderByCreateTime(Map<String, Object> parm);
	
	int getAllAdmUserCountOrderByCreateTime(Map<String, Object> parm);

	AdmUsers selectByPrimaryKey(Integer id);

	int updateByPrimaryKey(AdmUsers u);

	List<AdmUsers> selectByExample(AdmUsersExample admusersexample);

	int insertSelective(AdmUsers u);

	int deleteByPrimaryKey(Integer id);

	int updatePwdByPrimaryKey(int userId, String md5Hex);
}
