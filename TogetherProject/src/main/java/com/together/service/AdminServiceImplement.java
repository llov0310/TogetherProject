package com.together.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.mapper.AdminMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplement implements AdminService{
   private AdminMapper mapper;

   //회원 리스트 가져오는 함수
   @Override
   public ArrayList<MemberVO> getMemberList(){
	   return mapper.getMemberList();
   }
   
   //업체 리스트 가져오는 함수
   @Override
   public ArrayList<EnterpriseVO> getEnterpriseList(){
	   return mapper.getEnterpriseList();
   }
}