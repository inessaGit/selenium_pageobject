# selenium_pageobject
selenium_pageobject

****************** Info Regarding setup*************************************
##Pre-requisite: 
1) Maven configured and installed: http://maven.apache.org/download.cgi
2) JDK v 1.7 installed 
3) Firefox version 33 installed 
4) For Firefox: set up FIREFOX PROFILE and name it WebDriver. 
on Windows: http://kb.mozillazine.org/Creating_a_new_Firefox_profile_on_Windows
A.exit Firefox - Start - search - firefox.exe -p (to switch profiles)
B. set Firefox 'Update' to 'Ask before updating' 
C. see compatability notes for FF and Selenium: https://raw.githubusercontent.com/SeleniumHQ/selenium/master/java/CHANGELOG
on Mac: http://spf13.com/post/managing-multiple-firefox-profiles-in-os-x
5) Eclipse - GIT plugin (EGIT) installed 
6) Eclipse - Maven plugin (M2Eclipse)  installed 
7) Git configured and installed
8) Eclipse ->right click project name -> Build Path -> Configure Build Path -> Add External Library ->TestNG

General notes:
1). WebDriver v2.44 support Firefox 24, 31, 32 and 33
2). see info on Page Object pattern: https://code.google.com/p/selenium/wiki/PageObjects
3). all PageObjects extend LoadableComponent class ;
4). all PageObjectTests extend BaseSuite (to control tests with @AfterSuite, @BeforeSuite etc) 
5). On Windows reading from properties file is different: 
silently drops backslash'\' -> put double backslash '\\' or replace backslash with forward slash
http://stackoverflow.com/questions/5784895/java-properties-backslash

Installation: http://stackoverflow.com/questions/4869815/importing-a-maven-project-into-eclipse-from-git
1) git clone https://github.com/inessaGit/selenium_pageobject.git
2) Eclipse -File -> Import... -> Existing Maven Projects
OR
1) Eclipse- Window -> Show View ->"Git Repository" View -> click on "Clone from a Git Repository
 and add the clone to this view"
2) Eclipse -File ->Import -> "Import Maven Projects" from such clone

Running: 
1)Command Prompt -> Change to the directory where pom.xml is located after you cloned project
2) mvn compile 
3) mvn test 
OR
1) Eclipse -right click suite.xml file -> Run as TestNG suite. 
