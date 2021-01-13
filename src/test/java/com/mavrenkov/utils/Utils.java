package com.mavrenkov.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);


    public static void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * method with explicit wait
     * for better synchronization
     */
    public static void waitForElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    //use it just for visibility (Selenium way to fast)
    public static void waitSleep (int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    //method that will send text without synchronization issues
    public static void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }



}
