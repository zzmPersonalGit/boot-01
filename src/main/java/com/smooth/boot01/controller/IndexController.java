package com.smooth.boot01.controller;

import com.smooth.boot01.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * 进登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        if (StringUtils.hasLength(user.getUsername()) && "123456".equals(user.getPassword())){
            //把登录成功得用户保存起来
            session.setAttribute("loginuser",user);
            //登录成功重定向到main，html；  重定向方式解决重复提交问题
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","账号密码错误");
            return "login";
        }


    }

    /**
     * 去main页面
     * @return
     */
    @GetMapping("/main.html")
    public String mainpage(HttpSession session,Model model){
        //是否登录  拦截器  过滤器
        Object loginuser = session.getAttribute("loginuser");
        if (loginuser != null){
            return "main";
        }else {
            model.addAttribute("msg","未登录，请登录");
            return "login";
        }

    }
}
