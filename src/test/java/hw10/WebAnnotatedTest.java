package hw10;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;




public class WebAnnotatedTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 85;

    @Test
    @Disabled
    public void testAnnotatedStep() {

        SelenideLogger.addListener("allure", new AllureSelenide());
      AllureWebSteps steps = new AllureWebSteps();

    steps.openMainPage();
    steps.searchForRepository(REPOSITORY);
    steps.clickOnRepositoryLink(REPOSITORY);
    steps.openIssuesTab();
    steps.shouldSeeIssueWithNumber(ISSUE);

      }
}
