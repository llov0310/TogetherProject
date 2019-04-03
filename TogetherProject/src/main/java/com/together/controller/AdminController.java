package com.together.controller;

import org.springframework.stereotype.Controller;

import com.together.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;
	
}
