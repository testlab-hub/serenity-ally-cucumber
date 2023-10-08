package starter.pages;

import com.deque.html.axecore.results.ResultType;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.deque.html.axecore.selenium.AxeReporter.getReadableAxeResults;
import static org.junit.Assert.fail;

public class LoginPage extends PageObject {

    WebDriver driver = getDriver();
    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtn;

    @FindBy(xpath = "//img[@alt='client brand banner']")
    WebElement banner;

    @Step
    public void setUserLoginDetails(String uname, String pwd) {
        waitFor(username).shouldBePresent();

        username.clear();
        username.sendKeys(uname);
        password.clear();
        password.sendKeys(pwd);
    }

    @Step
    public void clickLogin() {
        loginBtn.click();
    }

    @Step
    public void bannerIsDisplayed() {
        waitFor(banner).shouldBePresent();
        Assert.assertTrue(banner.isDisplayed());
    }

    /**
     * method to check Accessibility Violations, then write report to JSON & Text File
     **/
    @SneakyThrows
    @Step
    public void checkAccessibility() {

        Results results = new AxeBuilder().analyze(driver);
        List<Rule> violations = results.getViolations();

        String AxeReportPath = System.getProperty("user.dir") + File.separator + "AxeReports" + File.separator;
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss").format(new java.util.Date());
        String AxeViolationReportPath = AxeReportPath + "AccessibilityViolations_" + timeStamp;

        if (violations.size() == 0) {
            Assert.assertTrue("No violations found", true);
            Serenity.recordReportData().withTitle("Accessibility Violations").andContents("No Violations Detected");

        } else if (getReadableAxeResults(ResultType.Violations.getKey(), driver, violations)) {

            // write report to Text File
            AxeReporter.writeResultsToTextFile(AxeViolationReportPath, AxeReporter.getAxeResultString());
            // write report to JSON File
            AxeReporter.writeResultsToJsonFile(AxeViolationReportPath, results);

            // fail the test
            throw new AssertionError("Accessibility violations found, check AxeReports folder for more details.");
        }

    }

    /**
     * method to check Accessibility Violations, then write report to directly to Serenity Report
     **/
    @SneakyThrows
    @Step
    public void scanForAccessibility() {
        Results results = new AxeBuilder().analyze(driver);
        List<Rule> violations = results.getViolations();

        if (getReadableAxeResults(ResultType.Violations.getKey(), driver, violations)) {

            // write result directly to Serenity Report & fail the test
            Serenity.recordReportData().withTitle("Accessibility Violations").andContents(AxeReporter.getAxeResultString());

            //throw new AssertionError("Accessibility violations found.");
            fail("Accessibility Violations detected, review Serenity report for more details");

        } else {
            Serenity.recordReportData().withTitle("Accessibility Violations").andContents("No Violations Detected");

        }

    }
}
