package com.ftw.service.impl;

import com.ftw.dao.adm.AdmPermissionDAO;
import com.ftw.dao.adm.AdmRolesDAO;
import com.ftw.dao.adm.AdmUserRoleDAO;
import com.ftw.dao.adm.AdmUsersDAO;
import com.ftw.entity.adm.*;
import com.ftw.service.AdmUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * Fetch ADM user info service
 * @author LGP
 * @date 2018/09/14
 */
@Service
public class AdmUserServiceImpl implements AdmUserService
{
    @Resource
    private AdmUsersDAO admusersdao;
    
    @Resource
    private AdmUserRoleDAO admuserroledao;
    
    @Resource
    private AdmRolesDAO admrolesdao;
    
    @Resource
    private AdmPermissionDAO admpermissiondao;
    
    /**
     * Get AdmUser by telephone
     */
	@Override
	public AdmUsers findUserByMobile(String telephone)
	{
		AdmUsersExample admUserExample = new AdmUsersExample();
		admUserExample.createCriteria().andTelephoneEqualTo(telephone);
		List<AdmUsers> admusers = admusersdao.selectByExample(admUserExample);
		if(null != admusers && admusers.size() > 0)
		{
			return admusers.get(0);
		}
		return null;
	}
	@Override
	public AdmUsers findUserByLoginName(String loginName)
	{
		AdmUsersExample admUserExample = new AdmUsersExample();
		admUserExample.createCriteria().andLoginnameEqualTo(loginName);
		List<AdmUsers> admusers = admusersdao.selectByExample(admUserExample);
		if(null != admusers && admusers.size() > 0)
		{
			return admusers.get(0);
		}
		return null;
	}
	/**
	 * Get roles of AdmUser
	 */
	@Override
	public Set<String> getRoles(AdmUsers admusers)
	{
		List<AdmUserRole> admuserroles = this.getAdmUserRole(admusers);
		Set<String> roles = new HashSet<String>();
		if(null != admuserroles && admuserroles.size() > 0)
		{
			for(AdmUserRole aur : admuserroles)
			{
				Integer roleId = aur.getRoleid();
				AdmRolesExample admrolesexample = new AdmRolesExample();
				admrolesexample.createCriteria().andIdEqualTo(roleId);
				List<AdmRoles> admRoles = admrolesdao.selectByExample(admrolesexample);
				if(null != admRoles && admRoles.size() > 0)
				{
					for(AdmRoles ars : admRoles)
					{
						roles.add(ars.getRolename());
					}
				}
			}
			return roles;
		}
		return null;
	}
	
	/**
	 * Get Permissions By RoleId
	 */
	@Override
	public List<String> getPermissionsByRoleId(AdmUsers admusers)
	{
		List<String> permissions = new ArrayList<String>();
		List<AdmUserRole> admuserroles = this.getAdmUserRole(admusers);
		if(null != admuserroles && admuserroles.size() > 0)
		{
			for(AdmUserRole aur : admuserroles)
			{
				Integer roleId = aur.getRoleid();
				//Fetch Permission by roleId
				AdmPermissionExample admpermissionexample = new AdmPermissionExample();
				admpermissionexample.createCriteria().andRoleidEqualTo(roleId);
				List<AdmPermission> aPerms = admpermissiondao.selectByExample(admpermissionexample);
				if(null != aPerms && aPerms.size() > 0)
				{
					for(AdmPermission p : aPerms)
					{
						permissions.add(p.getName());
					}
				}
			}
		}
		return permissions;
	}
	
	/**
	 * GetAdmUserRole List
	 */
	@Override
	public List<AdmUserRole> getAdmUserRole(AdmUsers admusers)
	{
		AdmUserRoleExample admuserroleexample = new AdmUserRoleExample();
		admuserroleexample.createCriteria().andUseridEqualTo(admusers.getId());
		List<AdmUserRole> admuserroles = admuserroledao.selectByExample(admuserroleexample);
		return admuserroles;
	}

	@Override
	public List<Map<String, Object>> getAllAdmUserOrderByCreateTime(Map<String, Object> parm) {
		return admusersdao.getAllAdmUserOrderByCreateTime(parm);
	}

	@Override
	public int getAllAdmUserCountOrderByCreateTime(Map<String, Object> parm) {
		return admusersdao.getAllAdmUserCountOrderByCreateTime(parm);
	}

	@Override
	public AdmUsers selectByPrimaryKey(Integer id) {
		return admusersdao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(AdmUsers u) {
		return admusersdao.updateByPrimaryKey(u);
	}

	@Override
	public List<AdmUsers> selectByExample(AdmUsersExample admusersexample) {
		return admusersdao.selectByExample(admusersexample);
	}

	@Override
	public int insertSelective(AdmUsers u) {
		return admusersdao.insertSelective(u);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return admusersdao.deleteByPrimaryKey(id);
	}

	@Override
	public int updatePwdByPrimaryKey(int userId, String md5Hex) {
		return admusersdao.updatePwdByPrimaryKey(userId,md5Hex);
	}

}
