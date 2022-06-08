package com.lin.gamestore.web.admin.auth;

import com.lin.gamestore.entity.AdminUser;
import com.lin.gamestore.service.AdminUserService;
import com.lin.gamestore.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AuthController {

    /**
     * 登录路由
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "auth/login";
    }

    /**
     * 修改密码页路由
     *
     * @return
     */
    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    private String changepsw() {
        return "auth/changepsw";
    }
}
