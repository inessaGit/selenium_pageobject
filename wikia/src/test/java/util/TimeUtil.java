package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.Date;


public class TimeUtil 
{
	private static final Calendar calendar;
	private static SimpleDateFormat dateFormatter;
	private static SimpleDateFormat timeFormatter;

	static
	{
		calendar = Calendar.getInstance();
		dateFormatter=	new SimpleDateFormat("yyyy-MM-dd");
		timeFormatter=new SimpleDateFormat("HH_mm_ss_SSS");

	}
	//2015-Jan-07 SimpleDateFormat("yyyy-MMM-dd");
	public static String CurrentDate() {

		Date date =new Date();//should not be static initialized!
		//String dateNow = dateFormatter.format(calendar.getTime());
		String dateNow = dateFormatter.format(date);

		return dateNow;
	}

	public static String CurrentTime() {

		Date date = new Date();
		String timeNow = timeFormatter.format(date);
		return timeNow;
	}


	public static String  getTimeStamp( )
	{
		Date date = new Date();
		Timestamp timestamp= new Timestamp(date.getTime());
		System.out.println("Getting timestamp @" +timestamp);

		//diff format 
		String timeStampCustom = TimeUtil.CurrentDate()+" "+TimeUtil.CurrentTime();
		return timestamp.toString();
	}
}
class TestTimeUtil
{

	public static void main (String []args)
	{
		System.out.println("TimeUtil.getTimeStamp()="+TimeUtil.getTimeStamp());
		System.out.println("TimeUtil.CurrentDate()"+TimeUtil.CurrentDate());
		System.out.println("TimeUtil.CurrentTime()"+TimeUtil.CurrentTime());
		System.out.println(TimeUtil.CurrentDate()+"_"+TimeUtil.CurrentTime());

	}
}