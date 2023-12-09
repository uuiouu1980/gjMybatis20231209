package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.impl.memberServiceImpl;
import com.example.demo.vo.member;

import jakarta.servlet.http.HttpSession;

@RestController
public class memberController {
	@Autowired
	memberServiceImpl msi;
	
	@Autowired
	HttpSession session;
	
	@PostMapping("/Login")
	public ModelAndView gotoLogin(String username,String password)
	{
		member m=msi.Login(username, password);
		ModelAndView mav=null;
		if(m!=null)
		{
			session.setAttribute("M", m);
			mav=new ModelAndView("/loginSuccess");
		}
		else
		{
			mav=new ModelAndView("/loginError");
		}
		
		return mav;
	}
	
	@GetMapping("/addMember")
	public ModelAndView gotoAddMember()
	{
		return new ModelAndView("/addMember");
	}
	
	@PostMapping("/add")
	public ModelAndView add(String name,String username,String password)
	{
		/*
		 * 1.檢查 username是否重複
		 * 2.沒有就新增
		 */
		boolean u=msi.UsernameRepeat(username);
		ModelAndView mav=null;
		if(u)
		{
			mav=new ModelAndView("/addMemberError");
		}
		else
		{
			member m=new member(name,username,password);
			msi.addMember(m);
			
			mav=new ModelAndView("/addMemberSuccess");
		}
		
		return mav;
		
		
	}

}
