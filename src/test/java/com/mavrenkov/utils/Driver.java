package com.mavrenkov.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Singleton design pattern for the driver
 */
public class Driver {
    /**
     * private constructor - so it is impossible to create instance of this class.
     */
    private Driver(){}

    private static WebDriver driver;


    public static WebDriver getDriver() {
        if(driver==null){
            String browser = ConfigurationReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                default:
                    /**
                     * Throw a custom exception to terminate our program
                     */
                    System.out.println("Wrong browser name!");
                    throw new IllegalArgumentException();
            }
        }
        return driver;
    }
    /**
     * Method to terminate driver & close window
     */
    public static void closeDriver(){
        if(driver!=null){
            /**
             * Will close all of the windows that was opened by this driver's instance
             */
            driver.quit();
            /**
             * Assigning driver's value to null -> so garbage collector will take care of it
             */
            driver=null;
        }
    }
}
