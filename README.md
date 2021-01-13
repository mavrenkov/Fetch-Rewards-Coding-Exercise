# Fetch-Rewards-Coding-Exercise
I tried to make the solution production ready and demonstrate my skills. I used Java as my main language.                                                     
This project built with Maven and has the following dependencies:                                                 
1. Selenium Webdriver for Java 3.141.59                                                  
2. Junit 4.13 (for assertions, annotations etc)                            
3. WebDriver Manager (to avoid driver files and just call it from web)                                  
I used Singleton design pattern for Driver and also I made ConfigurationReader to read sensitive information from configuration.properties                             
I also Used POM (Page Object Model) - each web page is a java class. I have a Base Page, where I store all common methods.                               
Also Page Factory design Pattern has been used for @FindBy annotations - because it is a more readable and more convenient way to look for elements on the page.                  
To handle synchronization issues I created Utils.java that store all common methods with Explicit waits.                                          

Instructions to execute scripts:                                               
    Run ReactPage.java that is located in the pages package.                                     
    It has 2 Tests firstWayToDo & secondWayToDo (lines 108 & 118)                                                
    
Solution details:                                                        
       2 weighing:                                                                                    
            - Divide all golden bars into 3 parts: (0,1,2),(3,4,5),(6,7,8)                                                        
            - Put 0-2 bars on left bowl and 3-5 bars on right bars (first weighing)                                               
            - there is 3 possible scenarios:                
                  - 1. ">" - means that fake gold bar is on the left bowl: so it's 0,1 or 2. I compare 0 and 1 in second weighing; also 3 possible scenarios               
                       depends on the second weighing I built ternary return. If "<", fake bar is number 0, If ">", fake bar is number 1, if "=", fake bar is 2(no other options left).            
                  - 2. ">" - means that fake gold bar is on the left bowl: so it's 3,4 or 5. I compare 3 and 4 in second weighing; also 3 possible scenarios                     
                       depends on the second weighing I built ternary return. If "<", fake bar is number 3, If ">", fake bar is number 4, if "=", fake bar is 5(no other options left).                         
                  - 3. ">" - means that fake gold bar is on the left bowl: so it's 6,7 or 8. I compare 6 and 7 in second weighing; also 3 possible scenarios                    
                       depends on the second weighing I built ternary return. If "<", fake bar is number 6, If ">", fake bar is number 7, if "=", fake bar is 8(no other options left).                                   
                       
P.S. If script execution is to fast - apply waitSleep(10) (commented by default).     
P.S.S. Thank you for an interesting task.
      
