package com.ftw.controller.adm;

import com.ftw.entity.adm.AdmPermission;
import com.ftw.entity.adm.AdmRoles;
import com.ftw.entity.adm.AdmRolesExample;
import com.ftw.service.AdmPermissionService;
import com.ftw.service.AdmRolesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(PermissionController.PATH)
public class PermissionController{
	static final String PATH = "/permission";

    @Resource
    private AdmPermissionService admPermissionMapper;
    @Resource
    private AdmRolesService admRolesMapper;


	/*1.Initializing jump page*/
    @RequestMapping("/list")
    public String list() {
    	return "admin/permission.html";
    }
    
    /**2. Fetch permissions list **/
    @PostMapping("/getList")
    @ResponseBody
    public Object getList(Model model, HttpSession session, HttpServletRequest request, String pageNo, String pageSize, String permissionName, Integer roleId)
    {
    	Map<String, Object> parm = new HashMap<>();
    	Map<String, Object> result = new HashMap<>();
    	int page_Start = 0;
    	int page_Size = 10;
    	if(!StringUtils.isEmpty(pageSize)) {
    		page_Size = Integer.parseInt(pageSize);
    	}
    	if(!StringUtils.isEmpty(pageNo)) {
    		page_Start = (Integer.parseInt(pageNo) - 1) * page_Size;
    	}
        if(null != roleId && roleId != -99) {
            parm.put("roleId", roleId);
            model.addAttribute("roleId", roleId);
        }
    	if(!StringUtils.isEmpty(permissionName)) {
    		parm.put("permissionName", permissionName);
    		model.addAttribute("permissionName", permissionName);
    	}
    	parm.put("pageStart", page_Start);
    	parm.put("pageSize", page_Size);
    	List<Map<String, Object>> datas = new ArrayList<>();
		datas = admPermissionMapper.getAllPermissions(parm);
		int size = admPermissionMapper.getAllPermissionsSize(parm);
    	result.put("datas", datas);
    	result.put("size", size);
    	return result;
    }

    /** show Add or edit permission page **/
    @GetMapping({"/showAddEditPermission", "/showAddEditPermission/{id}"})
    public String showAddEditPermission(@PathVariable(required = false) Integer id, Model model) {
    	AdmPermission admPermission = new AdmPermission();
		if(null != id && id > 0) {
            admPermission = admPermissionMapper.selectByPrimaryKey(id);
    	}
		List<AdmRoles> admRoles  = new ArrayList<AdmRoles>();
		admRoles = admRolesMapper.selectByExample(new AdmRolesExample());
		model.addAttribute("admPermission", admPermission);
		model.addAttribute("admRoles", admRoles);
    	return "admin/addEditPermission.html";
    }

    /** Add or edit permission **/
    @PostMapping("/postAddEditPermission")
    @ResponseBody
    public int addEditPermission( Integer permissionId,String permissionName, Integer roleId,String permissionDesc)
    {
    	AdmPermission admPermission = new AdmPermission();
		if(null != permissionId){
			admPermission = admPermissionMapper.selectByPrimaryKey(permissionId);
    	}
		admPermission.setName(permissionName);
		admPermission.setRoleid(roleId);
		if (!StringUtils.isEmpty(permissionDesc)){
			admPermission.setPermissionDesc(permissionDesc);
		}

    	int row = 0;
    	if(null != permissionId)
    	{
    		row = admPermissionMapper.updateByPrimaryKey(admPermission);
    	}
    	else
    	{
    		row = admPermissionMapper.insertSelective(admPermission);
    	}
    	return row;
    }

    /** Delete permission  **/
	@PostMapping("/delete")
	@ResponseBody
	public int delete(Integer permissionId) {
		int row = 0;
		try {
			if(null != permissionId) {
				row = admPermissionMapper.deleteByPrimaryKey(permissionId);
			}
			return row;
		} catch (Exception e) {
			return row;
		}
	}
	/**seach by Role for permission**/
	@PostMapping("/getRole")
	@ResponseBody
	public Object getRole() {
         List<AdmRoles> admRoles  = new ArrayList<AdmRoles>();
         Map<String, Object> result = new HashMap<String, Object>();
         admRoles = admRolesMapper.selectByExample(new AdmRolesExample());
         result.put("datas", admRoles);
         return result;
		}

    
}
