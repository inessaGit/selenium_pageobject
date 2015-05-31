package util;

/**
 * @author Inessa
 * 2/12
 * Singleton class with getters for constant values 
 * Values are read from properties file. 
 */
public final class Constants {
	
	private static Constants CONSTANTS = null;
	private final  ReadingProperties rp;

	private final  String failPath; //screenshot path 
	private final  String passPath;
	private final  String usergenPath;	
	
	private final String iePath;
	private final String chromePathWin;
	private final String chromePathMac;

	private final String base_url;
	private final String homepage_url; 

	
	//private constructor +init constants 
	private Constants()
	{
		rp =new ReadingProperties();
	
		failPath=rp.readConfigProperties("fail.screenshot.path");
		passPath=rp.readConfigProperties("pass.screenshot.path");
		usergenPath=rp.readConfigProperties("usergen.screenshot.path");
		
		iePath = rp.readConfigProperties("ie.path");
		chromePathWin = rp.readConfigProperties("chrome.path.win");
		chromePathMac = rp.readConfigProperties("chrome.path.mac");

		base_url=rp.readConfigProperties("base.url");
		homepage_url=rp.readConfigProperties("homepage.url");
	}
	
	
	public String getIePath() {
		return iePath;
	}


	public String getChromePathWin() {
		return chromePathWin;
	}
	public String getChromePathMac() {
		return chromePathMac;
	}
	
	public static Constants getInstance() {
	      if(CONSTANTS == null) {
	         CONSTANTS = new Constants();
	      }
	      return CONSTANTS;
	   }

	public ReadingProperties getRp() {
		return rp;
	}

	public String getFailPath() {
		return failPath;
	}

	public String getPassPath() {
		return passPath;
	}

	public String getUsergenPath() {
		return usergenPath;
	}



	public String getBase_url() {
		return base_url;
	}
	
	public String getHomepage_url() {
		return homepage_url;
	}
}

class TestConstants
{
	public static void main (String args[])
	{
		System.out.println(Constants.getInstance().getFailPath());
	}
}