package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.CommonMethods;

public class MyAccountPage {

    private WebDriver driver;

    //Constructor of MyAccountPage class
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //Object Repository
    By pageLabel_myAccount = By.xpath("//ul[@class='breadcrumb']/descendant::a[text()='Account']");

    //Action Methods
    public String myAccountPage_title() {
        CommonMethods.assertWaitForElementToDisplay(driver, pageLabel_myAccount, 60);
        return driver.getTitle();
    }

    public String verifySection_label(String labelName) {
        String path="//div[@id='content']/descendant::h2[text()='"+labelName+"']";
        CommonMethods.assertWaitForElementToDisplay(driver, By.xpath(path), 60);
        return driver.findElement(By.xpath(path)).getText();
    }

    public String verifySection_links(String linkName) {
        String path="//div[@id='content']/descendant::a[text()='"+linkName+"']";
        CommonMethods.assertWaitForElementToDisplay(driver, By.xpath(path), 60);
        return driver.findElement(By.xpath(path)).getText();
    }

    public String verifyDrpdown_menu(String menuName) {
        String path="//*[@class='nav navbar-nav']/descendant::a[text()='"+menuName+"']";
        CommonMethods.assertWaitForElementToDisplay(driver, By.xpath(path), 60);
        return driver.findElement(By.xpath(path)).getText();
    }
}
