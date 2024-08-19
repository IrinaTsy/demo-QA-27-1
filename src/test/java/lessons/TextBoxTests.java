package lessons;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxTests {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080"; //изменение размера браузера
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager"; //вычитывание элементов из DOM-дерева до полного открытия страницы
        //Configuration.holdBrowserOpen = true; открытие браузера, если тесты проходят очень быстро
        //Configuration.timeout = 5000; пауза на 5 сек
    }

    @Test
    void fillFormTest() {

        open("/text-box");
        $("#userName").setValue("Alex1"); //вместо id можно указать #
        $("#userEmail").setValue("Alex@ivanov.com");
        $("#currentAddress").setValue("Some address");
        $("#permanentAddress").setValue("Some address");
        $("#submit").click();

        $("#output").$("#name").shouldHave(text("Alex1"));
        $("#output #email").shouldHave(text("Alex@ivanov.com")); //сокращенная запись строки №27
        $("#output #currentAddress").shouldHave(text("Some address"));
        $("#output #permanentAddress").shouldHave(text("Some address"));

    }

    //-------------------------HomeWork-------------------------------------

    @Test
    void fillStudentRegistrationForm() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");//отключение баннера
        executeJavaScript("$('footer').remove()");//отключение футера
        $("#firstName").setValue("Alex"); // #firstName=[id=firstName]
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("Alex@ivanov.com");
        $("#gender-radio-1+label").click();
        $("#userNumber").setValue("8888888888");
        $("#dateOfBirth #dateOfBirthInput").click();
        $(".react-datepicker__month-select").click(); //календарь начало
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1979");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--030").click(); //календарь конец  .react-datepicker__day--030 -> [class=react-datepicker__day--030]
        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#hobbies-checkbox-1+label").click();
        $("#uploadPicture").scrollIntoView(true);
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("Russia");
        $("#react-select-3-input").setValue("Haryana").pressEnter(); //выпадающий список
        $("#react-select-4-input").setValue("Karnal").pressEnter(); //выпадающий список
        $("#submit").click();

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
    }


    }
