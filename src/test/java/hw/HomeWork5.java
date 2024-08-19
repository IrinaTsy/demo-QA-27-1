package hw;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork5 {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test

    void hoverSolutionsTest(){

        open("https://github.com");
        $(withTagAndText("button", "Solutions")).hover();
        $(withTagAndText("a", "Enterprise")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));


    }
}
