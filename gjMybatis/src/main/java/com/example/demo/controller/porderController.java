package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.porderService;
import com.example.demo.vo.member;
import com.example.demo.vo.porder;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/porder")
public class porderController {
	@Autowired
	porderService ps;
	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletResponse response;
	
	@PostMapping("/add")
	public ModelAndView add(int A,int B,int C)
	{
		/*
		 * 1.擷取　session-->member
		 * 2.new porder-->轉session
		 * 3.換頁-->confirm.html
		 */
		
		member m=(member) session.getAttribute("M");
		porder p=new porder(m.getName(),A,B,C);
		System.out.println(p.getA()+"\t"+p.getB()+"\t"+p.getC());
		session.setAttribute("P", p);
		
		return new ModelAndView("/porder/confirm");
		
		
		
		
	}
	
	
	@GetMapping("/porder")
	public ModelAndView gotoPorder()
	{
		return new ModelAndView("/porder/porder");
	}
	
	
	
	@RequestMapping("/finish")
	public ModelAndView gotoConfirm()
	{
		/*
		 * 1.擷取session-->P
		 * 2.ps-->add
		 * 3.切換-->finish頁
		 * 
		 */
		porder p=(porder) session.getAttribute("P");		
		ps.addPorder(p);	
		
		
		return new ModelAndView("/porder/finish");
	}
	
	@GetMapping("/LogOut")
	public void logOut()
	{
		session.removeAttribute("M");
		session.removeAttribute("P");
		try {
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
