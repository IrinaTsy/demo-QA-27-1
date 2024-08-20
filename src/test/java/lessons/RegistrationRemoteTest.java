package lessons;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class RegistrationRemoteTest {


    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @Tag("demoqa")
    void fillStudentRegistrationFormTest() {

        step("Открываем главную страницу", () -> {
            open("/automation-practice-form");
             executeJavaScript("$('#fixedban').remove()");
                executeJavaScript("$('footer').remove()");
        });

        step("Заполняем форму", () -> {
            $("#firstName").setValue("Alex");
            $("#lastName").setValue("Ivanov");
            $("#userEmail").setValue("Alex@ivanov.com");
            $("#gender-radio-1+label").click();$("#userNumber").setValue("8888888888");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption("December");
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__year-select").click();
            $(".react-datepicker__year-select").selectOption("1979");
            $(".react-datepicker__year-select").click();$(".react-datepicker__day--030").click();
            $("#subjectsInput").setValue("Biology").pressEnter();
            $("#hobbies-checkbox-1+label").click();
            $("#uploadPicture").uploadFromClasspath("picture.jpg");
            $("#currentAddress").setValue("Russia");
            $("#react-select-3-input").setValue("Haryana").pressEnter();
            $("#react-select-4-input").setValue("Karnal").pressEnter();
            $("#submit").click();
        });

        step("Подтверждение результатов", () -> {
            $(".table-responsive").shouldHave(text("Alex Ivanov"));
            $(".table-responsive").shouldHave(text("Alex@ivanov.com"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("8888888888"));
            $(".table-responsive").shouldHave(text("30 November,1979"));
            $(".table-responsive").shouldHave(text("Biology"));
            $(".table-responsive").shouldHave(text("Sports"));
            $(".table-responsive").shouldHave(text("picture.jpg"));
            $(".table-responsive").shouldHave(text("Russia"));
            $(".table-responsive").shouldHave(text("Haryana Karnal"));
        });
    }

}
