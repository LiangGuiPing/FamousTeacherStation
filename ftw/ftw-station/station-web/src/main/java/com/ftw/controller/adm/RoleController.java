package com.ftw.controller.adm;

import com.ftw.entity.adm.AdmPermission;
import com.ftw.entity.adm.AdmPermissionExample;
import com.ftw.entity.adm.AdmRoles;
import com.ftw.entity.adm.AdmUsers;
import com.ftw.service.AdmPermissionService;
import com.ftw.service.AdmRolesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(RoleController.PATH)
public class RoleController {
	static final String PATH = "/role";

    @Resource
    private AdmRolesService admRoleMapper;

	@Resource
	private AdmPermissionService admPermissionMapper;


	/** 1.Initializing jump page **/
    @RequestMapping("/list")
    public String list() {
    	return "admin/role.html";
    }
    
    /**2. Fetch Roles list **/
    @PostMapping("/getList")
    @ResponseBody
    public Object getList(Model model, String pageNo, String pageSize, String roleName)
    {
    	Map<String, Object> parm = new HashMap<>();
		parm.put("roleName", roleName);

		Map<String, Object> result = new HashMap<>();
    	int page_Start = 0;
    	int page_Size = 10;
    	if(!StringUtils.isEmpty(pageSize)) {
    		page_Size = Integer.parseInt(pageSize);
    	}
    	if(!StringUtils.isEmpty(pageNo)) {
    		page_Start = (Integer.parseInt(pageNo) - 1) * page_Size;
    	}
    	if(!StringUtils.isEmpty(roleName)) {
    		parm.put("roleName", roleName);
    		model.addAttribute("roleName", roleName);
    	}
    	parm.put("pageStart", page_Start);
    	parm.put("pageSize", page_Size);
    	List<Map<String, Object>> datas = admRoleMapper.getAllRoles(parm);
		int size = admRoleMapper.getAllRolesSize(parm);
    	result.put("datas", datas);
    	result.put("size", size);
    	return result;
    }

    /** show Add or edit Role page **/
    @GetMapping({"/showAddEditRole", "/showAddEditRole/{id}"})
    public String showAddEditRole(@PathVariable(required = false) Integer id, Model model) {
		AdmRoles admRole = new AdmRoles();
        if(null != id && id > 0) {
            admRole = admRoleMapper.selectByPrimaryKey(id);
    	}
		List<AdmRoles> admRoles  = new ArrayList<>();
		model.addAttribute("admRole", admRole);
    	return "admin/addEditRole.html";
    }

    /** Add or edit Role **/
    @PostMapping("/postAddEditRole")
    @ResponseBody
    public int addEditRole(HttpSession session, Integer roleId, String roleName, String roleDesc)
    {
		AdmRoles admRole = new AdmRoles();
		if(null != roleId){
			admRole = admRoleMapper.selectByPrimaryKey(roleId);
    	}
        admRole.setRolename(roleName);
        if (!StringUtils.isEmpty(roleDesc)){
            admRole.setRoledesc(roleDesc);
        }
    	int row = 0;
    	if(null != roleId) {
    		row = admRoleMapper.updateByPrimaryKey(admRole);
    	}else {

            AdmUsers admuser = (AdmUsers) session.getAttribute("admuser");
            if(null != admuser) {
                admRole.setCreator(admuser.getId());
            }
            admRole.setCreatetime(new Date());
            row = admRoleMapper.insertSelective(admRole);
    	}
    	return row;
    }

    /** Delete Role  **/
	@PostMapping("/delete")
	@ResponseBody
	public int delete(Integer roleId) {
		int row = 0;
        List<AdmPermission> allPermissions = null;
		try {
			if(null != roleId) {
                AdmPermissionExample example = new AdmPermissionExample();
                example.createCriteria().andRoleidEqualTo(roleId);
                allPermissions = admPermissionMapper.selectByExample(example);
                if (null != allPermissions && allPermissions.size() != 0) {
                    return -1;
                }
                row = admRoleMapper.deleteByPrimaryKey(roleId);
			}
			return row;
		} catch (Exception e) {
			return row;
		}
	}

    
}
