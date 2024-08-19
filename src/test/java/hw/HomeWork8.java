package hw;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;

public class HomeWork8  {

    @ValueSource(strings = {"qagurubotgmail.", "qagurubotgmail.com"})
    @DisplayName("Тест формы регистрации с заполненным полем Email")
    @ParameterizedTest(name = "В форме регистрации для {0} должно заполниться поле Email")
    @Tag("SMOKE")
    void unsuccessFillFieldEmailTest(String value) {

        open("https://school.qa.guru");
            $("[name=email]").setValue(value);
            $("[name=password]").setValue("somepasshere").pressEnter();
            $(".btn-error").shouldHave(text("Неверный формат e-mail"));

        }


    @CsvSource(value = {
            "Главная, https://kion.ru/",
            "Телеканалы, https://kion.ru/tv",
            "Фильмы, https://kion.ru/video",
            "Сериалы, https://kion.ru/video/series"
            })

    @DisplayName("Тест на переключение языков")
    @ParameterizedTest()
    @Tag("SMOKE")
    void searchResultsShouldContainExpectedURL(String page, String link) {
        open("https://kion.ru/");
        $(".d-flex h-100 align-items-center position-relative ng-tns-c1981479377-0").$(byText(page)).hover().click();

        //webdriver().shouldHave(currentFrameUrl(link));

    }

   // @DisplayName("Тестирование зависимости URL от выбранного языка")
   // @CsvSource(value = {
       //     "English, https://javarush.com/en/",
        //    "Українська, https://javarush.com/ua/",
        //    "Русский, https://javarush.com/"

   // })
   // @ParameterizedTest()
   // void languageTest(String language, String link) {
    //    open("https://javarush.com/");
    //    $(".language-switcher-item").scrollIntoView(true).click();
    //    $(".language-switcher").$(byText(language)).click();
     //   webdriver().shouldHave(currentFrameUrl(link));
   // }

    }

