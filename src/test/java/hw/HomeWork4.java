package hw;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork4 {

    @Test

    void searchJUnit5Test(){

        open("https://github.com/selenide/selenide");
        $("#wiki-tab").shouldHave(text("wiki")).click();
        $("#wiki-tab").click();
        $$("[href$='SoftAssertions']").first().shouldHave(text("Soft assertions"));
        $$("[href$='SoftAssertions']").first().click();
        $$("h4.heading-element").findBy(text("JUnit5")).should(exist);
        $("#wiki-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class}) " + "class Tests " +
                "{ " +
                    "  @Test " +
                    "  void test() { " +
                    "    Configuration.assertionMode = SOFT; " +
                    "    open(\"page.html\"); " +
                    "    $(\"#first\").should(visible).click(); " +
                    "    $(\"#second\").should(visible).click(); " +
                    "  } " +
                    "}"));
        }
    }

