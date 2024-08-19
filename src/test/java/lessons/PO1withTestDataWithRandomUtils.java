package lessons;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import pages.RegistrationPage;
import org.junit.jupiter.api.Test;

//import static Pages.RegistrationPage.firstNameLocator;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static testdata.RandomUtils.*;
import static testdata.dataRegistrationPage.*;

public class PO1withTestDataWithRandomUtils {

    @Test
    void fillStudentRegistrationFormTest() {

        //генерация тестовых данных с помощью javafaker - подход 1
        //Faker faker = new Faker();

        //String firstName = faker.name().firstName();
        //String lastName = faker.name().lastName();
        //String userEmail = faker.internet().emailAddress();
        //String streetAddress = faker.address().streetAddress();

        //генерация тестовых данных с помощью javafaker - подход 2

        String firstName = getRandomString(10);// (10) - количество символов в имени
        String lastName = getRandomString(10);
        String userEmail = getRandomEmail();
        String streetAddress = getRandomStreetAddress();

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //$("#firstName").setValue("Alex");
        $("#firstName").setValue(firstName);

        //$("#lastName").setValue("Ivanov");
        $("#lastName").setValue(lastName);

        //$("#userEmail").setValue("Alex@ivanov.com");
        $("#userEmail").setValue(userEmail);

        $("#gender-radio-1+label").click();
        $("#userNumber").setValue("8888888888");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1979");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--030").click();
        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#hobbies-checkbox-1+label").click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");

        //$("#currentAddress").setValue("Russia");
        $("#currentAddress").setValue(streetAddress);

        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("Alex Ivanov"));
        //$(".table-responsive").shouldHave(text("Alex@ivanov.com"));

        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("8888888888"));
        $(".table-responsive").shouldHave(text("30 November,1979"));
        $(".table-responsive").shouldHave(text("Biology"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("picture.jpg"));

        //$(".table-responsive").shouldHave(text("Russia"));
        $(".table-responsive").shouldHave(text(streetAddress)); //из javafaker

        $(".table-responsive").shouldHave(text("Haryana Karnal"));

    }

}
