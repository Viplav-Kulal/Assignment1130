# Assignment

This project is built using Selenium Webdriver + TestNG and Java language. The folder structure is maven based.

Assignment A
  1. Clone or Import the repository as Maven project from Master branch https://github.com/Viplav-Kulal/Assignment1130.git
  2. Go to TestNG.xml file and run as TestNG Suite

Assignment B
  1. Checkout branch 'viplav'
  2. refresh the project
  3. Go to "TestNG2.xml" and run as TestNG Suite

Points to Note- 
  1. The user provided in Assignment(john/demo) stopped working. Someone else might also be testing with same user. While testing i found the same users cutomerID changes twice. This created inconsistency. So I have created a new user viplav/demo and used those credentials in the assignment.
  2. The user information is provided in environment.properties file located at /src/main/resources/property. You can add any test user of your choice to that properties file for assignment A. For Assignment B please use my test user viplav/demo
  3. Screenshots are saved on failure using the iTestListener of TestNG. These are copied in the Project directory.  
  4. The execution happens on Chromedriver. It is located in the Project path/Chromedriver directory. It supports Chrome version 96. Please make sure youo have the compatible chrome browser version on the execution environment.
  5. There is no external reporting mechanism. I have used the default TestNG reports and logging using Log4j for both assignments.
