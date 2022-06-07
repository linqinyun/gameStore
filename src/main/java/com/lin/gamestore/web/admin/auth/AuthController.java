package com.lin.gamestore.web.admin.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/auth")
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
