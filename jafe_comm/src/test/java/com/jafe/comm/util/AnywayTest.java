import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		System.out.println(new SimpleDateFormat("yyyyMMdd").parse("@0000000"));
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

}