package com.together.service;


import org.springframework.stereotype.Service;

import com.together.domain.MemberVO;
import com.together.mapper.AdminMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplement implements AdminService{
   private AdminMapper mapper;

   //회원 리스트 가져오는 함수
   @Override
   public MemberVO getMemberList() {
      // TODO Auto-generated method stub
      return mapper.getMemberList();
   }
}