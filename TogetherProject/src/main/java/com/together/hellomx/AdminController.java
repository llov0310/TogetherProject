package com.together.hellomx;

import org.springframework.stereotype.Controller;

import com.together.service.AdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;
	
}
