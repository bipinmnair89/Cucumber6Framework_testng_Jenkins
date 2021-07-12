package steps;

import driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class LoginPageSteps {

    private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());

    @Given("User is able to load the application")
    public void test_load_appUrl() {
        DriverFactory.getDriver().get("https://demo-opencart.com/index.php?route=common/home");
    }

    @Given("User lands in the application landing page having title {string}")
    public void test_landingPage_title(String expectedTitle) {
        String actualTitle=loginPage.landingPage_title();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @When("User navigates to the Login page having title {string}")
    public void test_navigateTo_loginPage_verifyTitle(String expectedTitle) {
        String actualTitle=loginPage.navigateTo_loginPage_verifyTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @When("User enters email as {string} and password as {string}")
    public void test_enter_emailandpwd(String email, String pwd) {
        String emailAttrValue=loginPage.enter_email(email);
        Assert.assertEquals(emailAttrValue,email);
        String pwdAttrValue  =loginPage.enter_pwd(pwd);
        Assert.assertEquals(pwdAttrValue,pwd);
    }

    @Then("User clicks Login button and navigates to My account page with title {string}")
    public void test_myAccountPage_title(String expectedAcntPageTitle) {
        String actualAcntPageTitle=loginPage.click_loginBtn();
        Assert.assertEquals(expectedAcntPageTitle,actualAcntPageTitle);
    }

    @Then("User clicks on {string} link and verify page elements")
    public void test_forgotpwdPage(String pageLinkName) {
        boolean value = loginPage.forgotpwdPage(pageLinkName);
        Assert.assertTrue(value);
    }

    @Then("User navigates to the Register page having title {string}")
    public void test_navigateTo_registerPage_verifyTitle(String expectedTitle) {
        String actualTitle=loginPage.navigateTo_RegisterPage_verifyTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Then("User verifies the fields in Register Account page")
    public void test_registerAccountPage_fields(DataTable dataTable) {
        List<String> list = dataTable.asList(String.class);
        for(String listItem : list) {
            String actualFieldLabel=loginPage.registerAccountPage_fields(listItem);
            Assert.assertEquals(actualFieldLabel,listItem);
        }
    }
}

