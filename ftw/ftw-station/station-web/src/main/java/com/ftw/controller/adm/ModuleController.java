package com.ftw.controller.adm;

import com.ftw.entity.adm.AdmModules;
import com.ftw.entity.adm.AdmUsers;
import com.ftw.entity.adm.AdmUsersModules;
import com.ftw.entity.adm.AdmUsersModulesExample;
import com.ftw.service.AdmModulesService;
import com.ftw.service.AdmUserModulesService;
import com.util.RestTemplateUtil;
import com.util.constant.RedisKeyConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(ModuleController.PATH)
public class ModuleController
{
	static final String PATH = "/module";

	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
    @Resource
    private AdmUserModulesService admusersmodulesmapper;
    
    @Resource
    private AdmModulesService admmodulesmapper;

    @RequestMapping("/list")
    public String list()
    {
    	return "admin/modules.html";
    }
    
    /** Fetch modules list **/
    
    @PostMapping("/getList")
    @ResponseBody
    public Object getList(Model model, HttpSession session, HttpServletRequest request, String pageNo, String pageSize, String moduleName)
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
    	
    	if(!StringUtils.isEmpty(moduleName))
    	{
    		parm.put("moduleName", moduleName);
    		model.addAttribute("moduleName", moduleName);
    	}
    	
    	parm.put("pageStart", page_Start);
    	parm.put("pageSize", page_Size);
    	
    	List<Map<String, Object>> datas = new ArrayList<>();
		datas = admusersmodulesmapper.getAllModules(parm);
		int size = admusersmodulesmapper.getAllModulesSize(parm);
    	
    	result.put("datas", datas);
    	result.put("size", size);
    	return result;
    }
    
    /** show Add or edit module page **/
    
    @GetMapping({"/showAddEditModule", "/showAddEditModule/{id}"})
    public String showAddEditModule(@PathVariable(required = false) Integer id, Model model)
    {
    	AdmModules admmodules = new AdmModules();
    	if(null != id && id > 0)
    	{
    		admmodules = admmodulesmapper.selectByPrimaryKey(id);
    	}
    	model.addAttribute("admmodules", admmodules);
    	List<Map<String, Object>> allModules = admusersmodulesmapper.fetchModules();
    	model.addAttribute("allmodules", allModules);
    	
    	return "/admin/addEditModule.html";
    }
    
    /** Add or edit module **/
    
    @PostMapping("/postAddEditModule")
    @ResponseBody
    public int addEditModule(String modulename, String parentid, String visiturl, int displaysort, int isdisplay, String remark, Integer moduleid)
    {
    	AdmModules m = new AdmModules();
    	if(null != moduleid)
    	{
    		m = admmodulesmapper.selectByPrimaryKey(moduleid);
    	}
    	m.setModulename(modulename);
    	if(!StringUtils.isEmpty(parentid))
    	{
    		m.setParentid(Integer.parseInt(parentid));
    	}
    	m.setCreatetime(new Date());
    	m.setRemark(remark);
    	m.setVisiturl(visiturl);
    	m.setDisplaysort(displaysort);
    	m.setIsdisplay(isdisplay);
    	int row = 0;
    	if(null != moduleid)
    	{
    		row = admmodulesmapper.updateByPrimaryKey(m);
    	}
    	else
    	{
    		row = admmodulesmapper.insertSelective(m);
    	}
    	return row;
    }
    
    /** Delete module and adm_users_modules data **/
    
    @PostMapping("/delete")
    @ResponseBody
    public int delete(Integer moduleid)
    {
    	int row = 0;
    	if(null != moduleid)
    	{
    		AdmUsersModulesExample ame = new AdmUsersModulesExample();
    		ame.createCriteria().andModuleidEqualTo(moduleid);
    		List<AdmUsersModules> amms = admusersmodulesmapper.selectByExample(ame);
    		if(null != amms && amms.size() > 0)
    		{
    			for(AdmUsersModules amm : amms)
    			{
    				admusersmodulesmapper.deleteByPrimaryKey(amm.getId());
    			}
    		}
    		row = admmodulesmapper.deleteByPrimaryKey(moduleid);
    	}
    	return row;
    }
    
    /** Fetch user module after login **/
    
	@PostMapping("/getModulesByUser")
    @ResponseBody
    public Object getLoginUserModules(HttpSession session)
    {
    	AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
    	Map<String, Object> result = new HashMap<>();
    	List<Map<String, Object>> datas = new ArrayList<>();
    	if(null != admuser)
    	{
    		int userId = admuser.getId();
			String str = redisTemplate.opsForValue().get(RedisKeyConstants.FTW_USER_MODULES + userId);
			if (!StringUtils.isEmpty(str)) {
				datas = (List<Map<String, Object>>) RestTemplateUtil.jsonToBean(str, List.class, false);
			}
    		if(datas.size() <= 0)
    		{
				datas = admusersmodulesmapper.GetmodulesFromDB(userId);
			}
    	}
    	result.put("datas", datas);
    	return result;
    }
    
    
    
}
