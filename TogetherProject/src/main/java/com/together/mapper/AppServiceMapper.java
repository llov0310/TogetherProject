package com.together.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.EnterpriseVO;
import com.together.domain.HospitalOrdersVO;
import com.together.domain.MemberVO;
import com.together.domain.PetDiaryVO;
import com.together.domain.ReviewBoardVO;
import com.together.domain.UserOrderVO;

public interface AppServiceMapper {

	public ArrayList<EnterpriseVO> hotelList(@Param("in") String in, @Param("out") String out, @Param("address") String Address
			,@Param("localLat") String localLat,@Param("localLnt") String localLnt);

	public ArrayList<EnterpriseVO> hotelinfoList(@Param("name") String etpName, @Param("addr") String etpAddr);

	public ArrayList<EnterpriseVO> ProductList(@Param("code") String etpcode, @Param("in") String etpfirst, @Param("out") String etplast);

	public ArrayList<MemberVO> user_info(String user);

	public ArrayList<EnterpriseVO> product_info(@Param("proname") String productname, 
			@Param("addr") String address, @Param("ph") String etpph, 
			@Param("cont")String procont, @Param("price") String price);

	public int insertOrder(@Param("user") String user, @Param("code") String pd_cd, 
			@Param("in") String first, @Param("out") String last, 
			@Param("price") int sum);

	public int total_day(@Param("in") String first, @Param("out") String last);

	public int reviewadd(@Param("id") String user_id, 
			@Param("code") String code, 
			@Param("star") String starcount, 
			@Param("content") String content);

	public ArrayList<ReviewBoardVO> reviewList(String etpcode);

	public ArrayList<ReviewBoardVO> reviewcount(String etp_cd);

	public ArrayList<EnterpriseVO> firstproduct(String etp_cd);

	public ArrayList<EnterpriseVO> funeralList(@Param("day") String formetData, @Param("location") String location);

	public ArrayList<EnterpriseVO> firstfuneralproduct(String etp_cd);

	public ArrayList<ReviewBoardVO> funeral_review(String decodeResult);

	public ArrayList<EnterpriseVO> funeralgetList(String decodeResult);

	
	//격리공간 (장례상품조회)
	public ArrayList<EnterpriseVO> funeral1(String decodeResult);
	public ArrayList<EnterpriseVO> funeral2(String decodeResult); 
	public ArrayList<EnterpriseVO> funeral3(String decodeResult); 
	public ArrayList<EnterpriseVO> funeral4(String decodeResult);

	public int f_orders(@Param("user") String user_id, 
			@Param("pro") String pro_name, 
			@Param("price") String price, 
			@Param("day") String order_day,
			@Param("code") String code);

	public ArrayList<EnterpriseVO> resultOrder(@Param("day") String order_day,@Param("code") String code, @Param("user") String user_id);

	public ArrayList<EnterpriseVO> getHospitalList(@Param("day") String day, 
			@Param("item") String item, @Param("Address") String Address,
			@Param("localLat") String lat,@Param("localLnt") String lnt);

	public ArrayList<EnterpriseVO> detail_getHosList(String etp_nm);

	public ArrayList<HospitalOrdersVO> hospital_orders(
			@Param("code") String etp_cd, 
			@Param("day1") String s_time1, 
			@Param("day2") String s_time2);

	public int HosOrders(HospitalOrdersVO insVO);

	public ArrayList<HospitalOrdersVO> getHor_cd(HospitalOrdersVO insVO);

	public int HosDetail(@Param("hor_cd") String hor_cd, 
			@Param("canser") String canser);

	public ArrayList<EnterpriseVO> Recommend_list();
	
	public ArrayList<PetDiaryVO> getCalList(String petcode);

	public int insertDiary(PetDiaryVO setVO);

	public int signup(MemberVO ins);

	public ArrayList<UserOrderVO> UserOrder_list(String id);

	public int order_cancle(
			@Param("or_cd") String or_cd,
			@Param("code") String code, 
			@Param("day") String day, 
			@Param("day2") String day2);

	public int horder_cancle(
			@Param("or_cd") String or_cd,
			@Param("code") String code, 
			@Param("day") String day, 
			@Param("day2") String day2);

	public int forder_cancle(@Param("or_cd") String or_cd,
			@Param("code") String code, 
			@Param("day") String day, 
			@Param("day2") String day2); 

	

}
