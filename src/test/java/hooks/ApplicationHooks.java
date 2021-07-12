package hooks;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.CommonMethods;
import util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private ConfigReader configReader;
    private WebDriver driver;
    private Properties prop;

    //In @Before annotation the order executed is in ascending(1 - 2 - 3)
    @Before(order = 1)
    public void readProp() {
        configReader = new ConfigReader();
        prop = configReader.initProperties();
    }

    @Before(order = 2)
    public void printScenarioName(Scenario scenario) {
        System.out.println("Scenario name is - "+scenario.getName());
    }

    @Before(order = 3)
    public void loadBrowserDriver() {
        String browserValue = prop.getProperty("browser");  //use the prop object from readProp() method to extract browser value
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(browserValue);
    }

    //In @After annotation the order executed is in descending(3 - 2 - 1)
    @After(order = 0)
    public void closeBrowser() {
        driver.close();
    }

    @After(order = 1)
    public void logout() {
        try {
            CommonMethods.clickWebElement(driver, By.xpath("//span[text()='My Account']"), 5);
            CommonMethods.clickWebElement(driver, By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/descendant::a[text()='Logout']"), 2);
            CommonMethods.assertWaitForElementToDisplay(driver, By.xpath("//h1[text()='Account Logout']"), 60);
            CommonMethods.clickWebElement(driver, By.xpath("//a[text()='Continue']"), 60);
        }catch (Exception e) {
            System.out.println("This scenario doesn't need user to login");
        }
    }

    @After(order = 2)
    public void checkScenarioFail(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }


    @After(order = 3)
    public void printScenarioStatus(Scenario scenario) {
        System.out.println("Status of the scenario - "+scenario.getName()+" is :"+scenario.getStatus());
    }
}
