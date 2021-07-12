package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.CommonMethods;

public class LoginPage {

    private WebDriver driver;

    //Constructor of LoginPage class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Object Repository
    By label_demoStore = By.xpath("//img[@title='GspTechnologies Opencart Demo Store']");
    By drpdown_myAccount = By.xpath("//span[text()='My Account']");
    By drpdownValue_login = By.xpath("//a[text()='Login']");
    By drpdownValue_register = By.xpath("//a[text()='Register']");
    By pageLabel_login = By.xpath("//ul[@class='breadcrumb']/descendant::a[text()='Login']");
    By pageLabel_register = By.xpath("//ul[@class='breadcrumb']/descendant::a[text()='Register']");
    By label_returningCust = By.xpath("//h2[text()='Returning Customer']");
    By label_email = By.xpath("//label[text()='E-Mail Address']");
    By label_pwd = By.xpath("//label[text()='Password']");
    By txtbox_email = By.xpath("//input[@id='input-email']");
    By txtbox_pwd = By.xpath("//input[@id='input-password']");
    By btn_login= By.xpath("//input[@value='Login']");
    By label_myAccount = By.xpath("//h2[text()='My Account']");
    By link_forgotPwd = By.xpath("//div[@class='form-group']/descendant::a[text()='Forgotten Password']");
    By label_forgotPwd = By.xpath("//h1[text()='Forgot Your Password?']");


    //Action Methods
    public String landingPage_title() {
        CommonMethods.assertWaitForElementToDisplay(driver, label_demoStore, 30);
        return driver.getTitle();
    }

    public String navigateTo_loginPage_verifyTitle() {
        CommonMethods.clickWebElement(driver, drpdown_myAccount,30);
        CommonMethods.clickWebElement(driver, drpdownValue_login, 30);
        CommonMethods.assertWaitForElementToDisplay(driver, pageLabel_login, 30);
        return driver.getTitle();
    }

    public String navigateTo_RegisterPage_verifyTitle() {
        CommonMethods.clickWebElement(driver, drpdown_myAccount,30);
        CommonMethods.clickWebElement(driver, drpdownValue_register, 30);
        CommonMethods.assertWaitForElementToDisplay(driver, pageLabel_register, 30);
        return driver.getTitle();
    }


    public String enter_email(String email) {
        CommonMethods.assertWaitForElementToDisplay(driver,label_returningCust,30);
        CommonMethods.clearAndEnterText(driver, txtbox_email, email);
        CommonMethods.assertWaitForElementToDisplay(driver, label_email, 30);
        return CommonMethods.getattrubitevalue(driver, txtbox_email, "value");
    }

    public String enter_pwd(String pwd) {
        CommonMethods.assertWaitForElementToDisplay(driver,label_returningCust,30);
        CommonMethods.clearAndEnterText(driver, txtbox_pwd, pwd);
        CommonMethods.assertWaitForElementToDisplay(driver, label_pwd, 30);
        return CommonMethods.getattrubitevalue(driver, txtbox_pwd, "value");
    }

    public String click_loginBtn() {
        CommonMethods.clickWebElement(driver, btn_login, 30);
        CommonMethods.assertWaitForElementToDisplay(driver, label_myAccount, 30);
        return driver.getTitle();
    }

    //Instead of giving the property of element in Object Repository, we can dynamically add the same as shown in below method
    public boolean forgotpwdPage(String linkName) {
        CommonMethods.clickWebElement(driver, By.xpath("//div[@class='form-group']/descendant::a[text()='"+linkName+"']"), 30);
        return driver.findElement(label_forgotPwd).isDisplayed();
    }

    public String registerAccountPage_fields(String fieldName) {
        String path="//div[@class='form-group required']/label[text()='"+fieldName+"']";
        CommonMethods.assertWaitForElementToDisplay(driver, By.xpath(path), 30);
        return driver.findElement(By.xpath(path)).getText();
    }

    public MyAccountPage app_login(String email, String pwd) {
        CommonMethods.clickWebElement(driver, drpdown_myAccount,30);
        CommonMethods.clickWebElement(driver, drpdownValue_login, 30);
        CommonMethods.assertWaitForElementToDisplay(driver,label_returningCust,30);
        CommonMethods.clearAndEnterText(driver, txtbox_email, email);
        CommonMethods.clearAndEnterText(driver, txtbox_pwd, pwd);
        CommonMethods.clickWebElement(driver, btn_login, 30);
        CommonMethods.assertWaitForElementToDisplay(driver, label_myAccount, 30);
        return new MyAccountPage(driver);
    }

}

























