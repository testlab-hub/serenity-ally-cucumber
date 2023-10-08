package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import starter.navigation.NavigateTo;
import starter.pages.LoginPage;

public class LoginStepDefinitions {

    LoginPage loginPage;

    @Given("{actor} navigates to the OrangeHRM website")
    public void researchingThings(Actor actor) {
        actor.wasAbleTo(NavigateTo.theLoginPage());
    }

    @When("{actor} enters Username as {string} and Password as {string}")
    public void setLoginDetails(Actor actor, String uname, String pwd) {
        actor.attemptsTo();
        loginPage.setUserLoginDetails(uname, pwd);
    }

    @When("Click on Login")
    public void click_on_login() throws InterruptedException {
        loginPage.clickLogin();
        Thread.sleep(2000);
    }

    @Then("{actor} should see OrangeHRM banner on Dashboard page")
    public void should_see_OrangeHRM_banner(Actor actor) {
        actor.attemptsTo();
        loginPage.bannerIsDisplayed();
    }

    @Then("the (.*) page should meet accessible criteria$")
    public void the_page_should_be_accessible(String page) {
        //loginPage.checkAccessibility();
        loginPage.scanForAccessibility();


    }

}
