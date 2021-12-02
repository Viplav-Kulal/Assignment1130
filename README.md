# Assignment

This project is built using Selenium Webdriver + TestNG and Java language. The folder structure is maven based.

Assignment A
  1. Clone or Import the repository as Maven project from Master branch https://github.com/Viplav-Kulal/Assignment1130.git
  2. Go to TestNG.xml file and run as TestNG Suite

Assignment B
  1. Checkout branch 'viplav'
  2. Pull the latest commit
  3. Go to "TestNG2.xml" and run as TestNG Suite

For Assingment B you will find a browser launching before the API testing starts. This is because i have used the same framework of Assignment A and extended the same BaseClass. Please switch to eclipse to see the console logs for API test and when Visual test starts to verify Account activity page, youo can switch to the browser.

Points to Note- 
  1. The user provided in Assignment(john/demo) stopped working. Someone else might also be testing with same user. While testing I found the same users cutomerID changed twice. This created inconsistency. So I have created a new user viplav/demo and used those credentials in the assignment.
  2. Also it has been obeserved the Users Customer ID is changing frequently. Not sure of the reason. So i have put the customer ID in properties file. If you get 500 error, it probably means the Customer ID is changed. So update the properties file with new customer ID and then execute.
  3. The user information is provided in environment.properties file located at /src/main/resources/property. You can add any test user of your choice to that properties file for assignment A. For Assignment B please use my test user viplav/demo
  4. Screenshots are saved on failure using the iTestListener of TestNG. These are copied in the Project directory. Need update to save screenshots to a specific directory.
  5. The execution happens on Chromedriver. It is located in the Project path/Chromedriver directory. It supports Chrome version 96. Please make sure you have the compatible chrome browser version on the execution environment. Alternatively, you can add the chromedriver, supported by your chrome version, to this directory
  6. There is no external reporting mechanism. I have used the default TestNG reports and logging using Log4j for both assignments.
