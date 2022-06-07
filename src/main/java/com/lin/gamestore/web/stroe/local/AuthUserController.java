package com.lin.gamestore.web.stroe.local;

import com.lin.gamestore.dto.AdminUserExecution;
import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.enums.AdminUserStateEnum;
import com.lin.gamestore.service.AdminUserService;
import com.lin.gamestore.util.HttpServletRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AuthUserController {
    Logger logger = LoggerFactory.getLogger(AuthUserController.class);
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 管理员列表
     *
     * @return
     */
    @RequestMapping(value = "/listauthuser", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listAuthUser() {
        logger.info("=========START========");
        Long startTime = System.currentTimeMillis();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<AdminUser> adminUserList = new ArrayList<AdminUser>();
        try {
            adminUserList = adminUserService.getAdminUserList();
            modelMap.put("rows", adminUserList);
            modelMap.put("total", adminUserList.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        logger.error("test error!");
        Long endTime = System.currentTimeMillis();
        logger.debug("costTime:[{}ms]", endTime - startTime);
        logger.info("========END==========");
        return modelMap;
    }

    /**
     * 创建账户
     * 成功添加session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindAuth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindAuthUser(HttpServletRequest request) {
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
                //填入session
                request.getSession().setAttribute("auth", ae.getAuthUser());
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
    @RequestMapping(value = "/changeauthuserpwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeAuthUserPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        Long authId = HttpServletRequestUtil.getLong(request, "authId");
        String username = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        if (authId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)) {
            AdminUserExecution ae = adminUserService.modifyAdminUser(authId, username, password, newPassword);
            if (ae.getState() == AdminUserStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
                //覆盖原信息
                request.getSession().setAttribute("auth", ae.getAuthUser());
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
     * 登录验证
     * 成功添加session
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> loginCheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        String username = HttpServletRequestUtil.getString(request, "username");
        String password = HttpServletRequestUtil.getString(request, "password");
        if (username != null && password != null) {
            AdminUser adminUser = new AdminUser();
            adminUser = adminUserService.getAdminUserByUserNameAndPwd(username, password);
            if (adminUser != null) {
                modelMap.put("success", true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("auth", adminUser);
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
     * 登出操作，清除session
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //清空session
        request.getSession().setAttribute("user", null);
        modelMap.put("success", true);
        return modelMap;
    }

}
