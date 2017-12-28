package cn.et.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.et.shiro.dao.UserMapper;

@Controller
public class SpringLoginController {
	
	@Autowired
	UserMapper userMapper;
	
	
	@RequestMapping("/shirologin")
	public String login(String userName,String password,Model model){
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			currentUser.login(token);
			//根据用户名查询出对应的菜单
			model.addAttribute("menuList",userMapper.queryMenuByUser(userName));
			return "/layout.jsp";
		} catch (UnknownAccountException uae) {
			System.out.println("账号错误");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("密码不匹配");
		} catch (LockedAccountException lae) {
			System.out.println("账号被锁定");
		} catch (AuthenticationException ae) {
			System.out.println("位置异常");
		}
		return "/fail.jsp";
	}
	
}
