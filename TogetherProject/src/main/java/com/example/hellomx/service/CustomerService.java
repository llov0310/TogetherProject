package com.example.hellomx.service;

import java.util.ArrayList;
import com.example.hellomx.domain.MemberVO;

public interface CustomerService {
	public MemberVO login(String userid, String password);

	public int signup(MemberVO ins);
}