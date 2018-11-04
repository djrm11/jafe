package com.jafe.comm.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AnywayTest {

	public static int getDateSpace(String date1, String date2)
			throws ParseException {

		int result = 0;

		Calendar calst = Calendar.getInstance();;
		Calendar caled = Calendar.getInstance();

		calst.setTime(new SimpleDateFormat("yyyyMMdd").parse(date1));
		caled.setTime(new SimpleDateFormat("yyyyMMdd").parse(date2));

		//设置时间为0时
		calst.set(Calendar.HOUR_OF_DAY, 0);
		calst.set(Calendar.MINUTE, 0);
		calst.set(Calendar.SECOND, 0);
		caled.set(Calendar.HOUR_OF_DAY, 0);
		caled.set(Calendar.MINUTE, 0);
		caled.set(Calendar.SECOND, 0);
		//得到两个日期相差的天数
		int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;

		return days;
	}
	public static void main(String[] args) throws ParseException {
//		System.out.println(getDateSpace("20170926", "20170615"));
//		System.out.println(Calendar.getInstance());
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		System.out.println(new SimpleDateFormat("yyyyMMdd").parse("20180702"));
//		System.out.println("^\\d{8}".matches("20160201"));
		System.out.println("20160201".matches("^\\d{8}"));
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("22");
		list.add("33");
		list.add("44");
		for(String str : list){
			if("33".equals(str) ){
				continue;
			}
			System.out.println(str);
		}
	}

	//test Map
	@Test
	public void soutMap(){

		Map<Long,String> map = new HashMap<>();
		map.put(1000L,"1000L");
		map.put(2000L,"2000L");
		map.put(3000L,"3000L");

		String mapString = map.toString();
//		Map<Long,String> mapAfter = (Map)mapString;

		System.out.println(map);

		String str = "8:00-22:00(所有上海迪士尼乐园时间仅供参考，并有可能在您游览乐园当天或之前发生变更，以景区当天营业时间为准。请于游览当天至游客服务处索取乐园时间表，以获取当天最新的活动时间详情及变更通知);";
		System.out.println(str.substring(0,str.length()-1)) ;
		System.out.println(str);
//		System.out.println(str.substring(0,-1)) ;	java.lang.StringIndexOutOfBoundsException: String index out of range: -1

	}

	@Test
	public void testStringLength(){
		System.out.println("所有".length());
		System.out.println("所you".length());
		System.out.println("所you!".length());

		System.out.println("所you!".substring(0,2));

		String str = "abccdadfafaf";
		System.out.println("afdafafda"+" '%' "+str);
	}

	@Test
	public void testRegex(){
		System.out.println("lv12345678910".matches("^lv\\d{11}$"));
		getDesensitizationUserName("");

	}
	@Test
	public void testLong(){
		long l =500;
		for(int i=0;i<5;i++){
			System.out.println(l+i);
		}
		Long ll = 10L;
		System.out.println(5*ll);

	}

	private String getDesensitizationUserName(String userName){
		if(isEmptyString(userName)){
			return userName;
		}

		if (userName.length() < 6){
			return userName.substring(0,1)+"****";
		}

		if(userName.matches("^lv\\d{11}$")){
			return userName.substring(0,5)+"****"+userName.substring(9,13);
		}

		if(userName.matches("^lv[\\s\\S]*$")){
			return userName.substring(0,3)+"****"+userName.substring((userName.length()-3),userName.length());
		}

		return userName.substring(0,1)+"****"+userName.substring((userName.length()-1),userName.length());
	}

	public boolean isEmptyString(String str) {
		return str == null ? true : str.trim().equals("") ? true : false;
	}
}