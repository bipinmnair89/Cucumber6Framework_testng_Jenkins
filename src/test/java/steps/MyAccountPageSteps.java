package steps;

import driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.MyAccountPage;

import java.util.List;
import java.util.Map;

public class MyAccountPageSteps {

    private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
    private MyAccountPage myAccountPage;

    @Given("User login with email {string} password {string} and navigate to My Accounts page")
    public void test_login_navigateToMyAccountsPage(String email, String pwd) {
        myAccountPage=loginPage.app_login(email,pwd);
    }

    @Given("User lands in My Accounts page having title {string}")
    public void test_MyAccountPage_title(String expectedTitle) {
        String actualTitle=myAccountPage.myAccountPage_title();
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @When("User verifies the label section header {string}")
    public void test_verifySection_label(String expectedLabel) {
        String actualLabel=myAccountPage.verifySection_label(expectedLabel);
        Assert.assertEquals(expectedLabel,actualLabel);
    }

    @Then("User verifies the links in the section")
    public void test_verifySection_links(DataTable dataTable) {
        List<Map<String,String>> listMap= dataTable.asMaps(String.class,String.class);
        for(Map<String,String> mapItem : listMap) {
            String expectedLinkName=mapItem.get("Links");
            String actualLinkName=myAccountPage.verifySection_links(expectedLinkName);
            Assert.assertEquals(expectedLinkName,actualLinkName);
        }
    }

    @Then("User verfies the dropdown menu {string}")
    public void test_verifyDrpdown_menu(String expectedMenuLabel) {
        String actualMenuLabel=myAccountPage.verifyDrpdown_menu(expectedMenuLabel);
        Assert.assertEquals(expectedMenuLabel,actualMenuLabel);
    }

    @Then("Dummy failure step")
    public void test_dummyFailure() {
        boolean flag=true;
        Assert.assertFalse(flag);
    }


}
