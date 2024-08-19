package lessons;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static org.openqa.selenium.By.linkText;
import static io.qameta.allure.Allure.step;
import org.junit.jupiter.api.DisplayName;

public class AllureTest {


    @Test
    @Disabled
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(withText("#80")).should(Condition.exist);
        //}

    }


    //StepsTest

    //public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    @Disabled
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> { //() -> - лямбда-функция с одним аргументом,
            //open("https://github.com") - аргумент
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });


    }

    // @Test
    // public void testAnnotatedStep() {
    //     SelenideLogger.addListener("allure", new AllureSelenide());
    //     WebSteps steps = new WebSteps();

    //     steps.openMainPage();
    //    steps.searchForRepository(REPOSITORY);
    //    steps.clickOnRepositoryLink(REPOSITORY);
    //    steps.openIssuesTab();
    //    steps.shouldSeeIssueWithNumber(ISSUE);

    //  }

//}


    //WebSteps

    //public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repo);
        $("#query-builder-test").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

//}

    //AttachmentsTest

    //public class AttachmentsTest {

        @Test
        @Disabled
        public void testLambdaAttachments() {
            SelenideLogger.addListener("allure", new AllureSelenide());

            step("Открываем главную страницу", () -> {
                open("https://github.com");
                attachment("Source", webdriver().driver().source());
            });
        }

        @Test
        @Disabled
        public void testAnnotatedAttachments() {
            SelenideLogger.addListener("allure", new AllureSelenide());
           // WebSteps steps = new WebSteps(); //из класса WebSteps

           // steps.openMainPage();
           // steps.takeScreenshot();
        }

    //}

    //LabelsTest

    //public class LabelsTest {

        @Test
        @Disabled
        @Feature("Issue в репозитории")
        @Story("Создание Issue")
        @Owner("eroshenkoam")
        @Severity(SeverityLevel.BLOCKER)
        @Link(value = "Testing", url = "https://testing.github.com")
        @DisplayName("Создание Issue для авторизованного пользователя")
        public void testStaticLabels() {
        }

        @Test
        @Disabled
        public void testDynamicLabels() {
            Allure.getLifecycle().updateTestCase(
                    t -> t.setName("Создание Issue для авторизованного пользователя")
            );
            Allure.feature("Issue в репозитории");
            Allure.story("Создание Issue");
            Allure.label("owner", "eroshenkoam");
            Allure.label("severity", SeverityLevel.CRITICAL.value());
            Allure.link("Testing", "https://testing.github.com");
        }

    //}
}
