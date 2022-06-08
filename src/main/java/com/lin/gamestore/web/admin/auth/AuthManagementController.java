package com.lin.gamestore.web.admin.auth;

import com.lin.gamestore.dto.AdminUserExecution;
import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.enums.AdminUserStateEnum;
import com.lin.gamestore.service.AdminUserService;
import com.lin.gamestore.util.CodeUtil;
import com.lin.gamestore.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AuthManagementController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 登录验证
     * 成功添加session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    private Map<String, Object> loginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();

        boolean needVerify = HttpServletRequestUtil.getBoolean(request,"needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        String username = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        if (username != null && password != null) {
            AdminUser adminUser = new AdminUser();
            adminUser = adminUserService.getAdminUserByUserNameAndPwd(username, password);
            if (adminUser != null) {
                modelMap.put("success", true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("adminuser", adminUser);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "账号密码错误！");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "账号密码为空！");
        }
        return modelMap;
    }

    /**
     * 创建账户
     * 成功添加session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/addadmin", method = RequestMethod.POST)
    private Map<String, Object> addAdminUser(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String username = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");

        if (username != null && password != null) {
            AdminUser adminUser = new AdminUser();
            adminUser.setUsername(username);
            adminUser.setPassword(password);
            adminUser.setCreateTime(new Date());
            adminUser.setUpdateTime(new Date());
            AdminUserExecution ae = adminUserService.bindAdminUser(adminUser);
            if (ae.getState() == AdminUserStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", ae.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "账户与密码不能为空！");
        }
        return modelMap;
    }

    /**
     * 修改密码
     * 成功覆盖原session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeadminpsw", method = RequestMethod.POST)
    private Map<String, Object> changeAdminUserPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long adminUserId = HttpServletRequestUtil.getLong(request, "adminUserId");
        String username = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        if (adminUserId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)) {
            AdminUserExecution ae = adminUserService.modifyAdminUser(adminUserId, username, password, newPassword);
            if (ae.getState() == AdminUserStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", ae.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "缺少必要信息！");
        }
        return modelMap;
    }

    /**
     * 通过session获取当前用户数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    private Map<String, Object> adminInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        AdminUser adminUser = null;
        adminUser =(AdminUser) request.getSession().getAttribute("adminuser");
        if (adminUser==null){
            modelMap.put("success", false);
            modelMap.put("errMsg","session为空");
        }else{
            modelMap.put("success", true);
            modelMap.put("adminUserId",adminUser.getAdminUserId());
            modelMap.put("username",adminUser.getUsername());
        }
        return modelMap;
    }

    /**
     * 登出操作，清除session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //清空session
        request.getSession().setAttribute("adminuser", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
