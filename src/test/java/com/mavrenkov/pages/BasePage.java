package com.mavrenkov.pages;

import com.mavrenkov.utils.ConfigurationReader;
import com.mavrenkov.utils.Driver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;
//in this class I store all the common stuff
public abstract class BasePage {

    //Instantiate an instance of the given class, and set a lazy proxy for each of the WebElement
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    //This method will switch driver to alert and get its text
    public String getAlertText(){
        return Driver.getDriver().switchTo().alert().getText();
    }
    //driver setup and test setup
    @Before
    public void setUp(){
        System.out.println("Starting automation");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }
    //this method will close the driver
    @After
    public void tearDown(){
        Driver.closeDriver();
        System.out.println("End od the automation!");
    }

}
