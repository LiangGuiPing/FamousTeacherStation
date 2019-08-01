package com.ftw.controller.adm;

import com.ftw.controller.GetLoginSession;
import com.ftw.entity.adm.*;
import com.ftw.service.AdmRolesService;
import com.ftw.service.AdmUserModulesService;
import com.ftw.service.AdmUserRoleService;
import com.ftw.service.AdmUserService;
import com.util.CodeEnums;
import com.util.CommonException;
import com.util.page.ResultEntity;
import com.util.token.EncryptBySHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping(AdmUserController.PATH)
public class AdmUserController
{
	static final String PATH = "/user";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AdmUserService admuserservice;
    
    @Resource
    private AdmUserRoleService admuserroleservice;
    
    @Resource
    private AdmUserModulesService admusermodulesservice;

	@Resource
	private AdmRolesService admrolesservice;
	
	@RequestMapping("/list")
    public String list(Model model, HttpSession session)
    {
    	return "admin/users.html";
    }
    
    /** Fetch user list **/
    
    @PostMapping("/getList")
    @ResponseBody
    public Object getList(Model model, HttpSession session, HttpServletRequest request, String pageNo, String pageSize, String loginName)
    {
    	Map<String, Object> parm = new HashMap<>();
    	Map<String, Object> result = new HashMap<>();
    	int page_Start = 0;
    	int page_Size = 10;
    	if(!StringUtils.isEmpty(pageSize))
    	{
    		page_Size = Integer.parseInt(pageSize);
    	}
    	
    	if(!StringUtils.isEmpty(pageNo))
    	{
    		page_Start = (Integer.parseInt(pageNo) - 1) * page_Size;
    	}
    	
    	if(!StringUtils.isEmpty(loginName))
    	{
    		loginName = loginName.trim();
    		parm.put("loginName", loginName);
    	}
    	
    	parm.put("pageStart", page_Start);
    	parm.put("pageSize", page_Size);
    	
    	AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
    	int hasLoginUserId = 0;
    	if(null != admuser)
    	{
    		hasLoginUserId = admuser.getId();
    	}
    	List<Map<String, Object>> datas = admuserservice.getAllAdmUserOrderByCreateTime(parm);
    	List<Map<String, Object>> newDatas = new ArrayList<>();
    	for(Map<String, Object> nm : datas)
    	{
    		Map<String, Object> newMap = new HashMap<>();
    		int userid = Integer.parseInt(nm.get("id").toString());
    		if(hasLoginUserId == userid)
    		{
    			newMap.put("showStartEndBtn", 1);
    		}
    		else
    		{
    			newMap.put("showStartEndBtn", 0);
    		}
    		newMap.put("id", userid);
    		newMap.put("telephone", nm.get("telephone"));
    		newMap.put("loginName", nm.get("loginName"));
    		newMap.put("realName", nm.get("realName"));
    		newMap.put("isuabled", nm.get("isuabled"));
    		newMap.put("createTime", nm.get("createTime"));
    		newMap.put("expireTime", nm.get("expireTime"));
    		newMap.put("creator", nm.get("creator"));
    		newMap.put("lastEditTime", nm.get("lastEditTime"));
    		newDatas.add(newMap);
    	}
    	
    	int size = admuserservice.getAllAdmUserCountOrderByCreateTime(parm);
    	result.put("datas", newDatas);
    	result.put("size", size);
    	return result;
    }
    
    /** show Add or edit user page **/
    @GetMapping({"/userAddEdit", "/userAddEdit/{id}"})
    public String userAddEdit(@PathVariable(required = false) Integer id, Model model) throws ParseException
    {
    	AdmUsers admusers = new AdmUsers();
    	if(null != id && id > 0)
    	{
    		admusers = admuserservice.selectByPrimaryKey(id);
    	}
		model.addAttribute("admusers", admusers);
    	return "admin/userAddEdit.html";
    }
    
    
    /** Add or edit user **/
    
	@PostMapping("/postSaveOrUpdateUsers")
    @ResponseBody
    public int saveOrUpdateUsers(HttpSession session, HttpServletRequest request, String loginname, String password, String realname, int status, Integer id) throws UnsupportedEncodingException, ParseException
    {
		AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
    	AdmUsers u = null;
    	if(null != id && id > 0)
    	{
    		u = admuserservice.selectByPrimaryKey(id);
    	}
    	else
    	{
    		//新增
			u = new AdmUsers();
			u.setId(0);
			u.setLoginname(loginname);
			u.setCreatetime(new Date());
			u.setCreator(admuser.getId());
			Calendar calendar= Calendar.getInstance();
			calendar.set(2030,1,1);
			u.setExpiretime(calendar.getTime());
		}
		u.setTelephone("");
		u.setRealname(realname);
		u.setStatus(status);
    	int row = 0;
    	if(null != id&&id>0)
    	{
    		u.setLastedittime(new Date());
    		row = admuserservice.updateByPrimaryKey(u);
    	}
    	else
    	{
			AdmUsersExample admusersexample = new AdmUsersExample();
			admusersexample.createCriteria().andLoginnameEqualTo(loginname);
			List<AdmUsers> datas = admuserservice.selectByExample(admusersexample);
			if(datas.size() > 0)
			{
				return -1;
			}
    		if(!StringUtils.isEmpty(password))
    		{
    			u.setPassword(EncryptBySHA256.getEncryptString(password.trim()));
    		}
    		row = admuserservice.insertSelective(u);
    	}
    	return row;
    }
    
	/** check the user exist or not **/
	
	@PostMapping("/userExist")
    @ResponseBody
	public boolean userExist(String loginname)
	{
		loginname = loginname.trim();
     	AdmUsersExample admusersexample = new AdmUsersExample();
    	admusersexample.createCriteria().andLoginnameEqualTo(loginname);
    	List<AdmUsers> datas = admuserservice.selectByExample(admusersexample);
    	if(datas.size() > 0)
    	{
    		return true;
    	}
    	return false;
	}
	
	
    /** Delete all user data **/
    
    @PostMapping("/delete")
    @ResponseBody
    public int delete(Integer id)
    {
    	int row = 0;
    	if(null != id)
    	{
    		//delete role
    		AdmUserRoleExample admuserroleexample = new AdmUserRoleExample();
    		admuserroleexample.createCriteria().andUseridEqualTo(id);
    		List<AdmUserRole> admuserroles = admuserroleservice.selectByExample(admuserroleexample);
    		if(null != admuserroles && admuserroles.size() > 0)
    		{
    			for(AdmUserRole ar : admuserroles)
    			{
    				row = admuserroleservice.deleteByPrimaryKey(ar.getId());
    			}
    		}
    		//delete modules
    		AdmUsersModulesExample ame = new AdmUsersModulesExample();
    		ame.createCriteria().andUseridEqualTo(id);
    		List<AdmUsersModules> amms = admusermodulesservice.selectByExample(ame);
    		if(null != amms && amms.size() > 0)
    		{
    			for(AdmUsersModules amm : amms)
    			{
    				row = admusermodulesservice.deleteByPrimaryKey(amm.getId());
    			}
    		}
    		//delete user
    		row = admuserservice.deleteByPrimaryKey(id);
    	}
    	return row;
    }
    
    /** Disabling user **/
    @PostMapping("/disabledUser")
    @ResponseBody
    public int disabledUser(Integer id, Integer status)
    {
    	int row = 0;
    	if(null != id)
    	{
    		AdmUsers admuser = admuserservice.selectByPrimaryKey(id);
    		admuser.setStatus(status);
    		row = admuserservice.updateByPrimaryKey(admuser);
    	}
    	return row;
    }
    
	@GetMapping({"/showUserRole", "/showUserRole/{id}"})
	public Object showUserRole(@PathVariable(required = false) Integer id, Model model)
	{
		List<AdmRoles> admAllRoles  = new ArrayList<AdmRoles>();
		List<AdmUserRole> admUserRoles  = new ArrayList<AdmUserRole>();
        /*1.get user role  by userId*/
		if (null != id)
		{
			AdmUserRoleExample admuserroleexample = new AdmUserRoleExample();
			admuserroleexample.createCriteria().andUseridEqualTo(id);
			admUserRoles = admuserroleservice.selectByExample(admuserroleexample);
		}
        /*2.get all role  */
		admAllRoles = admrolesservice.selectByExample(new AdmRolesExample());
		/*3  the stage cant't ,the param spilcing with the end*/
		if (null != admUserRoles && admUserRoles.size() != 0)
		{
			for (AdmUserRole aURoles  : admUserRoles) 
			{
				if (null == admAllRoles || admAllRoles.size() == 0)
				{
					break;
				}
				for (AdmRoles aRoles  : admAllRoles) 
				{
					if (aRoles.getId() == aURoles.getRoleid()){
						aRoles.setFlag(1);
					}
				}
			}
		}
		model.addAttribute("admAllRoles", admAllRoles);
		model.addAttribute("userId", id);
		return "admin/addEditUserRole.html";
	}
	
	@PostMapping(value="/userBindRole")
	@ResponseBody
	public int userBindRole(Integer userId,String roleIds)
	{
		int result = 1;
		//1 check user exist
		AdmUsers resultModel=this.admuserservice.selectByPrimaryKey(userId);
		if(null == resultModel)
		{
			throw new CommonException(CodeEnums.NOTFIND);
		}
		/*2  bath delete   user  role  data*/
		List<AdmUserRole> admUserRoles  = new ArrayList<>();
		AdmUserRoleExample admuserroleexample = new AdmUserRoleExample();
		admuserroleexample.createCriteria().andUseridEqualTo(userId);
		admUserRoles = admuserroleservice.selectByExample(admuserroleexample);
		if (null != admUserRoles && admUserRoles.size() != 0)
		{
			this.admuserroleservice.bathDeleteUserRole(admUserRoles);
		}
		//3 and this  moddle  table  bind  relation
		List<AdmUserRole> recordlist = new ArrayList<>();
		if (!StringUtils.isEmpty(roleIds))
		{
			String[] split = roleIds.split(",");
			if (null != split  && split.length != 0) 
			{
				for (String str : split) 
				{
					AdmUserRole model = new AdmUserRole();
					model.setUserid(userId);
					model.setRoleid(Integer.valueOf(str));
					recordlist.add(model);
				}
				result = this.admuserroleservice.bathAddUserRole(recordlist);
			}
		}
		return result;
	}
	
	/** show configuration of the user module **/
	
	@GetMapping("/addEditUserModule/{userid}")
    public Object addEditUserModule(Model model, @PathVariable(required = true) Integer userid)
    {
		AdmUsers u = admuserservice.selectByPrimaryKey(userid);
		if(null != u)
		{
			model.addAttribute("telphone", u.getLoginname());
			model.addAttribute("userid", u.getId());
			
			Map<String, Object> param = new HashMap<String, Object>();
			List<Map<String, Object>> allParentModules = admusermodulesservice.getAllParentModules(param);
			List<Map<String, Object>> newDatas = new ArrayList<Map<String,Object>>();
			
			
			if(null != allParentModules && allParentModules.size() > 0)
			{
				for(Map<String, Object> m : allParentModules)
				{
					String ids = "";
					String isChecked = "false";
					Integer id = Integer.parseInt(m.get("id").toString());
					Map<String, Object> newMAP = new HashMap<String, Object>();
					newMAP.put("id", id);
					newMAP.put("moduleName", m.get("moduleName"));
					ids = id.toString() + ",";
					//Search is exist from adm_users_modules
					AdmUsersModulesExample aume = new AdmUsersModulesExample();
					aume.createCriteria().andUseridEqualTo(userid).andModuleidEqualTo(id);
					List<AdmUsersModules> admusersmoduleslist = admusermodulesservice.selectByExample(aume);
					if(null != admusersmoduleslist && admusersmoduleslist.size() > 0)
					{
						isChecked = "checked";
					}
					newMAP.put("isChecked", isChecked);
					
					param.put("parentId", id);
					List<Map<String, Object>> allSecondModules = admusermodulesservice.getsecondModulesByParentId(param);
					List<Map<String, Object>> secondSecondDatas = new ArrayList<Map<String,Object>>();
					if(null != allSecondModules && allSecondModules.size() > 0)
					{
						for(Map<String, Object> n : allSecondModules)
						{
							Map<String, Object> secondMAP = new HashMap<String, Object>();
							Integer secondId = Integer.parseInt(n.get("id").toString());
							secondMAP.put("id", secondId);
							secondMAP.put("moduleName", n.get("moduleName"));
							String isChecked2 = "false";
							AdmUsersModulesExample aume2 = new AdmUsersModulesExample();
							aume2.createCriteria().andUseridEqualTo(userid).andModuleidEqualTo(secondId);
							List<AdmUsersModules> admusersmoduleslist2 = admusermodulesservice.selectByExample(aume2);
							if(null != admusersmoduleslist2 && admusersmoduleslist2.size() > 0)
							{
								isChecked2 = "checked";
								ids += secondId.toString() + ",";
							}
							secondMAP.put("isChecked", isChecked2);
							secondSecondDatas.add(secondMAP);
						}
					}
					if(ids.indexOf(",") != -1)
					{
						ids = ids.substring(0, ids.length() - 1);
					}
					newMAP.put("secondModules", secondSecondDatas);
					newMAP.put("ids", ids);
					newDatas.add(newMAP);
				}
			}
			model.addAttribute("allModules", newDatas);
		}

    	return "admin/addEditUserModule.html";
    }
	
    @PostMapping("/saveOrUpdateUserModule")
    @ResponseBody
    public Object saveOrUpdateUserModule(Integer userId, String ids)
    {
    	ResultEntity resultentity = ResultEntity.getErrorResult();
    	try
    	{
    		resultentity = admusermodulesservice.saveOrUpdateUserModule(userId, ids);
    	}
    	catch(Exception e)
    	{
    		logger.error("保存或更新用户模块出错:{}", e);
    	}
    	return resultentity.getResult();
    }
    
    @RequestMapping("/showChangePassword")
    public String showChangePassword(Integer userId, String realName, Model model, HttpServletRequest request)
    {
    	try
    	{
    		if(userId!=null&&userId>0)
    		{
				model.addAttribute("userId", userId);
				model.addAttribute("realName", realName);
				model.addAttribute("isMe", 0);
			}
			else
			{
				AdmUsers admuser = GetLoginSession.GetUser();
				if(null != admuser)
				{
					model.addAttribute("userId", admuser.getId());
					model.addAttribute("realName", admuser.getRealname());
					model.addAttribute("isMe", 1);
				}
			}
    	}
    	catch(Exception e)
    	{
    		logger.error("显示修改密码页出错:{}", e);
    	}
    	return "admin/showChangePassword.html";
    }
    
    @PostMapping("/changePassword")
    @ResponseBody
    public Object changePassword(String password,int userId, HttpServletRequest request)
    {
    	int row = 0;
    	
    	try
    	{
			row = admuserservice.updatePwdByPrimaryKey(userId, EncryptBySHA256.getEncryptString(password));
    	}
    	catch(Exception e)
    	{
    		logger.error("修改密码出错:{}", e);
    	}
    	return row;
    }
	

}
