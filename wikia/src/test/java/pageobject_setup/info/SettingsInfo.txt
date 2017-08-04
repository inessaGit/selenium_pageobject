****************** ********************Framework setup*************************************
Pre-requisites and setup: 
1) Maven configured and installed: http://maven.apache.org/download.cgi
2) JDK v 1.8 installed and JAVA_HOME setup
3) Eclipse - GIT plugin (EGIT) installed 
4) Eclipse - Maven plugin (M2Eclipse)  installed 
5) Git configured and installed

----------------------------------------Pulling from git------------------------------------------------

6) pulling project from git: http://stackoverflow.com/questions/4869815/importing-a-maven-project-into-eclipse-from-git
- git clone https://github.com/inessaGit/selenium_pageobject.git
- Eclipse -File -> Import... -> Existing Maven Projects
OR
- Eclipse- Window -> Show View ->"Git Repository" View -> click on "Clone from a Git Repository
and add the clone to this view"
- Eclipse -File ->Import -> "Import Maven Projects" from such clone

7) Eclipse ->right click project name -> Build Path -> Configure Build Path -> Add External Library ->TestNG
8) Eclipse - Project-  Build path - put JDK 1.8 on your build path 

----------------------------------------WebDriver compatability------------------------------------------------

SEE API: http://seleniumhq.github.io/selenium/docs/api/java/

8) WebDriver v3.4.0 requires JDK 1.8 or higher  
- WebDriver v3.4.0 support Firefox via Geiko driver  https://ftp.mozilla.org/pub/firefox/releases/
- download  geckodriver_v0.18 from https://github.com/mozilla/geckodriver/releases  
- system path to geckodriver.exe, chromedriver.exe, IEDriverSErver.exe is specified via properties file in /config folder
- Firefox 53 and greater supported with WebDriver v3.4.0
- Chrome  Version 56.0.2924.87 supported with WebDriver v3.4.0
- OPTIONAL:   set up FIREFOX PROFILE and name it webdriver. 
see more for  Windows: http://kb.mozillazine.org/Creating_a_new_Firefox_profile_on_Windows
see more on Mac: http://spf13.com/post/managing-multiple-firefox-profiles-in-os-x

A.exit Firefox - Start - search - firefox.exe -p (to switch profiles)
B. set Firefox 'Update' to 'Ask before updating' 
C. see compatability notes for FF and Selenium: https://raw.githubusercontent.com/SeleniumHQ/selenium/master/java/CHANGELOG

9) Testing on mobile: https://seleniumhq.github.io/selenium/docs/api/java/
- The IOSDriver extends from AppiumDriver
- AppiumDriver extends from RemoteWebDriver
 This means if you want to use some specific function on IOS, it's better that select IOSDriver.

------------------------------------------Running:-------------------------------------------------- 
1)Separate test classes -> Eclipse right click class - Run as TestNG test
OR
2)Test suites via command Prompt -> Change to the directory where pom.xml is located after you cloned project
mvn compile 
mvn test 
OR
3) Test suites via testng.xml -> Eclipse -right click suite.xml file -> Run as TestNG suite. 

----------------------------------geckodriver v3.4.1------------------------------ 
BEFORE: 
	* webdriver.chrome.driver for Chrome browser
	* webdriver.ie.driver for IE browser
	* Now we have to use webdriver.gecko.driver for Firefox as well
/*------------------
Gecko Driver is the link between your tests in Selenium and the Firefox browser. 
GeckoDriver is a proxy for using W3C WebDriver-compatible clients to interact with Gecko-based browsers i.e. Mozilla Firefox . 
 As Selenium 3 will not have any native implementation of FF => need to direct all the driver commands through Gecko Driver. 
 Gecko Driver is an executable file that you need to have in one of the system path before starting your tests. Firefox browser implements the WebDriver protocol using an executable called GeckoDriver.exe. This executable starts a server on your system. All your tests communicate to this server to run your tests. It translates calls into the Marionette automation protocol by acting as a proxy between the local and remote ends.


Note:  On Windows reading from properties file is different: 
silently drops backslash'\' -> put double backslash '\\' or replace backslash with forward slash in config.properties
http://stackoverflow.com/questions/5784895/java-properties-backslash

--------------------------------------------Classes structure:----------------------------------
                            
1)Each page of AUT (Application Under Test) is mapped to a class file in your code 
and each method within the class file can be treated as a service offered by the PageObject. 
 see more info on Page Object pattern: https://code.google.com/p/selenium/wiki/PageObjects
2) All PageObjectTest classes should extend BaseTestSuite class (to control tests flow with @AfterSuite, @BeforeSuite etc) 
3) All  PageObject classes should extend BasePage class. 
4) Each PageObject class and PageObjectTest class should have Logger instance (using Log4j) 
5) WebDriverManager class acts as a utility class with methods to setup driver instances 

