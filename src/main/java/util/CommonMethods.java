package util;

import org.testng.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CommonMethods {

    public static void waitForDocumentReady(RemoteWebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);

        wait.pollingEvery(Duration.ofMillis(250L)).withTimeout(Duration.ofSeconds(seconds)).until(input -> {
            RemoteWebDriver driver1 = (RemoteWebDriver) input;
            return (boolean) driver1.executeScript("return document.readyState === 'complete'");
        });
    }

    public static void assertWaitForElementToDisplay(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        Assert.assertTrue(element.isDisplayed());
    }

    public static void assertWaitPresenceOfElementLocated(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        Assert.assertTrue(element.isDisplayed());
    }

    public static void assertWaitForElementToBeClickable(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findBy));
        Assert.assertTrue(element.isEnabled());
    }

    public static void assertWebElementText(WebDriver driver, By findBy, String elementText) {
        CommonMethods.assertWaitForElementToDisplay(driver, findBy, 20);
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");
        String name = elements.get(0).getText();
        Assert.assertEquals(name, elementText);
    }

    public static void assertWebElementContainsText(WebDriver driver, By findBy, String elementText) {
        assertWaitForElementToDisplay(driver, findBy, 20);
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");
        String name = elements.get(0).getText();
        Assert.assertTrue(name.contains(elementText));
    }

    public static void clickWebElement(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        wait.until(ExpectedConditions.elementToBeClickable(findBy));
        element.click();
        CommonMethods.waitForDocumentReady((RemoteWebDriver) driver, 20);
    }

    public static void clickALink(WebDriver driver, By findBy, String text) {
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");
        WebElement element = elements.get(0);
        if (element.getText().contains(text)) {
            element.click();
            CommonMethods.waitForDocumentReady((RemoteWebDriver) driver, 30);
        }
    }

    public static void assertButtonTextAndVisibility(WebDriver driver, By findBy, String expectedButtonText) {
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");

        WebElement element = elements.get(0);
        String actualButtonText = element.getAttribute("value");
        Assert.assertEquals(actualButtonText, expectedButtonText);

        boolean isVisible = element.isDisplayed();
        Assert.assertTrue(isVisible);
    }

    public static void assertElementVisibility(WebDriver driver, By findBy) {
        assertWaitForElementToDisplay(driver, findBy, 30);
        WebElement element = driver.findElement(findBy);
        Assert.assertTrue(element.isDisplayed());
    }

    public static void assertPageTitle(WebDriver driver, String text) {
        Assert.assertTrue(driver.getTitle().contains(text), "Page title is not displayed correctly");
    }

    public static void clearAndEnterText(WebDriver driver, By findBy, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");
        WebElement element = elements.get(0);
        element.clear();
        element.sendKeys(text);
    }

    public static void clearAndEnterTextJSE(WebDriver driver, By findBy, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        List<WebElement> elements = driver.findElements(findBy);
        Assert.assertTrue(elements.size() == 1, "Find /selection criterion (By) must find exactly one instance!");
        WebElement element = elements.get(0);
        element.click();
        element.clear();
        WebElement elementValue = driver.findElement(findBy);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "';", elementValue);

    }

    public static String getattrubitevalue(WebDriver driver, By findBy, String attributeName) {

        CommonMethods.assertElementVisibility(driver, findBy);
        String attributevalue = driver.findElement(findBy).getAttribute(attributeName);
        return attributevalue;
    }

    public static void mouseHoverElement(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        Assert.assertTrue(element.isDisplayed());
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(findBy)).build().perform();
    }

    public static void clickAndHoldAndRelease(WebDriver driver, By findBy, int waitTimeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        Assert.assertTrue(element.isDisplayed());
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(findBy)).build().perform();
        actions.release().build().perform();
    }

    public static void verifyTableDataExists(WebDriver driver, By findBy) {
        CommonMethods.assertWaitPresenceOfElementLocated(driver, findBy, 10);
        List<WebElement> listOfRows = driver.findElements(findBy);
        Assert.assertTrue(listOfRows.size() > 1);
    }

    public static void verifyTableDataIsEmpty(WebDriver driver, By findBy) {
        List<WebElement> elementList = driver.findElements(findBy);
        Assert.assertEquals(elementList.size(), 0);
    }

    public static void acceptJSAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        System.out.println("The alert is : " + alert.getText());
        alert.accept();
    }

    //JavascriptExecutorScrollTillElement method
    public static void scrollTillElementJSE(WebDriver driver, By findBy) {
        scrollToTopJSE(driver);
        WebElement element = driver.findElement(findBy);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //JavascriptExecutorScrollToTop method
    public static void scrollToTopJSE(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

    //JavascriptExecutorScrollToBottom method
    public static void scrollToBottomJSE(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    //Method to handle StaleElementReferenceException
    public static boolean retryingClick(WebDriver driver, By findBy) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {

                driver.findElement(findBy).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    public static void multipleClick(WebDriver driver, By findBy, int waitTimeInSeconds) {
        int count = 0;
        WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
        Assert.assertTrue(element.isDisplayed());
        do {
            CommonMethods.clickWebElement(driver, findBy, 50);
            count++;
        } while (count < 5);
    }

    public static void clickJSE(WebDriver driver, By findBy) {
        WebElement element = driver.findElement(findBy);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    //JavascriptExecutor method to highlight an element
    public static void highlightElement(WebDriver driver, By findBy) {
        for (int i = 0; i < 1; i++) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement element = driver.findElement(findBy);
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border: 4px solid red;");
                Thread.sleep(200);
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
