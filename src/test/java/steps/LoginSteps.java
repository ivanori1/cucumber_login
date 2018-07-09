package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {

    public WebDriver driver;
    public WebDriverWait driverWait;
    String baseURL = "http://webstationtest3.ttweb.net/WebStation.aspx";

    @Given("^Go to webstation$")
    public void goToWebstation() {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @When("^Set (.*) and (.*)$")
    public void setUsernameAndPassword(String username, String password) {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("userName")).clear();
        driver.findElement(By.id("userName")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("^Click Login")
    public void clickLogin() {
        driver.findElement(By.id("loginUser")).click();
    }

    @And("^Check Accept EULA$")
    public void checkAcceptEULA()  {
        // Write code here that turns the phrase above into concrete actions
        boolean e = driver.findElement(By.xpath("//*[@id=\"eulaAccepted\"]")).isSelected();
        if (e == false)
            driver.findElement(By.cssSelector("[for='eulaAccepted'].checkBoxLabel")).click();
    }

    @Then("^Error Message Credentials incorrect$")
    public void errorMessageCredentialsIncorrect() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(driver.findElement(By.className("error_container_inner")).getText().contains(
                "The username or password is incorrect"));
    }

    @Then("^Error Message Accept EULA$")
    public void errorMessageAcceptEULA()  {
        Assert.assertTrue(driver.findElement(By.className("error_container_inner")).getText().contains(
                "You have to accept the End User License Agreement in order to log in."));
    }

    @Then("^Error Message This user account is disabled$")
    public void errorMessageThisUserAccountIsDisabled()  {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(driver.findElement(By.className("error_container_inner")).getText().contains(
                "This user account is disabled. Please contact the support team for further actions."
                ));
    }

    @And("^Close browser$")
    public void closeBrowser() {
        // Write code here that turns the phrase above into concrete actions
        driver.quit();
    }

    @And("^Change Language to German$")
    public void changeLanguageToGerman()  {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.id("langButton1")).click();
        driver.findElement(By.id("langNavide")).click();
    }

    @Then("^Error Message Credentials incorrect German$")
    public void errorMessageCredentialsIncorrectGerman()  {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertTrue(driver.findElement(By.className("error_container_inner")).getText().contains(
                "Benutzername oder Passwort ist falsch"
        ));
    }


    @Then("^User is successfully login$")
    public void userIsSuccessfullyLogin()  {
        // Write code here that turns the phrase above into concrete actions
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo-ws")));
    }


    @And("^Go to webstation expired session$")
    public void goToWebstationExpiredSession()  {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseURL + "?timeout=5000");
    }

    @Then("^Relog button appear$")
    public void relogButtonAppear()  {
        // Write code here that turns the phrase above into concrete actions
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id("relogButton")));
     }
}
