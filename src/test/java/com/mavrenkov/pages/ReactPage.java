package com.mavrenkov.pages;

import com.mavrenkov.utils.Driver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

import static com.mavrenkov.utils.Utils.*;
import static org.junit.Assert.assertEquals;


// this will run the tests
public class ReactPage extends BasePage {
    // driver instance
    private WebDriver driver = Driver.getDriver();
    // expected result
    private static final String expectedResult = "Yay! You find it!";

    // easy way to find desired web element by its attribute.
    @FindBy(xpath ="//button[@class='square'][@data-value='0']")
    private WebElement correctNumber;
    // List of golden bars(0-9)
    @FindBy(css = ".coins .square ")
    private List<WebElement> bars;
    // result of Weighing ( situated between "boards")
    @FindBy(css = ".result .button")
    private WebElement resultOfWeighing;
    //weigh button
    @FindBy(id = "weigh")
    private WebElement weighButton;
    // reset button
    @FindBy(xpath = "//button[.='Reset']")
    private WebElement resetButton;

    // dynamic xpath to handle boards by id attribute ( more convenient than to use static xpath that can be changed after update, for example )
    // I use String.format method to replace %s with desired value (0-9 in our case)
    private final String leftBowlBoard = "//input[@id='left_%s']";
    private final String rightBowlBoard = "//input[@id='right_%s']";

    // returns ">", "<" or "="
    public String resultCheck(){
        waitForElement(resultOfWeighing);
        return resultOfWeighing.getText().trim();
    }
    // first Weighing: I decided to split golden bars  into 3 parts
    // at this method I put 0-2 on left and 3-5 on right Bowl Boards.
    public void firstWeighing(){
        int third = (bars.size()-1)/3;
        for (int i = 0; i < third ; i++) {
            //this will re-assign element to 0-2
            WebElement elementOnLeft = driver.findElement(By.xpath(String.format(leftBowlBoard,i)));
            //will send text in the left bowl 0-2
            enterText(elementOnLeft,i+"");
            //this will re-assign element to 3-5
            WebElement elementOnRight = driver.findElement(By.xpath(String.format(rightBowlBoard,i+third)));
            //will send text in the left bowl 3-5
            enterText(elementOnRight,i+third+"");

        }
        //click on weigh button
        clickOnElement(weighButton);
    }

    // in second weighing we will use information from first weighting
    // there is 3 possible scenarios:

    public int secondWeighing(){
        // calling first weighing
        firstWeighing();
        // 1. ">" - means that fake gold bar is on the left bowl: so its 3,4 or 5. I compare 3 and 4 in second weighing; also 3 possible scenarios
        // depends on the second weighing I built ternary return. If "<", fake bar is number 3, If ">", fake bar is number 4, if "=", fake bar is 5(no other options left).
        if(resultCheck().equals(">")){
            clickOnElement(resetButton);
            driver.findElement(By.xpath(String.format(leftBowlBoard,3))).sendKeys(3+"");
            driver.findElement(By.xpath(String.format(rightBowlBoard,4))).sendKeys(4+"");
            clickOnElement(weighButton);

            return (resultCheck().equals("<"))?3:(resultCheck().equals(">"))?4:5;
        // 2. ">" - means that fake gold bar is on the left bowl: so its 0,1 or 2. I compare 0 and 1 in second weighing; also 3 possible scenarios
        // depends on the second weighing I built ternary return. If "<", fake bar is number 0, If ">", fake bar is number 1, if "=", fake bar is 2(no other options left).
        }else if (resultCheck().equals("<")){
            clickOnElement(resetButton);
            driver.findElement(By.xpath(String.format(leftBowlBoard,0))).sendKeys(0+"");
            driver.findElement(By.xpath(String.format(rightBowlBoard,1))).sendKeys(1+"");
            clickOnElement(weighButton);

            return (resultCheck().equals("<"))?0:(resultCheck().equals(">"))?1:2;
        // 3. ">" - means that fake gold bar is on the left bowl: so its 6,7 or 8. I compare 6 and 7 in second weighing; also 3 possible scenarios
        // depends on the second weighing I built ternary return. If "<", fake bar is number 6, If ">", fake bar is number 7, if "=", fake bar is 8(no other options left).
        }else{
            clickOnElement(resetButton);
            driver.findElement(By.xpath(String.format(leftBowlBoard,6))).sendKeys(6+"");
            driver.findElement(By.xpath(String.format(rightBowlBoard,7))).sendKeys(7+"");
            clickOnElement(weighButton);

            return (resultCheck().equals("<"))?6:(resultCheck().equals(">"))?7:8;
        }

    }

    //first way - find fake golden bar by its attribute value
    @Test
    public void firstWayToDo(){
        clickOnElement(correctNumber);
        //assertion between expected result and text value from alert
        assertEquals(expectedResult,getAlertText());

        // waitSleep(10);  //uncomment if execution to fast
    }

    //second way - fine fake golden bar by 2 weightings
    @Test
    public void secondWayToDo(){
        //secondWeighing() - will return int (number of fake gold bar) and I will use it to get element from List of gold bars
        clickOnElement(bars.get(secondWeighing()));
        //assertion between expected result and text value from alert
        assertEquals(expectedResult,getAlertText());


        // waitSleep(10);  //uncomment if execution to fast

    }





}
