package lessons;

import org.junit.jupiter.api.Test;

//import static Pages.RegistrationPage.firstNameLocator;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static testdata.dataRegistrationPage.*;


public class PO1withTestData extends TestBase {

    //подход 1
    //public static final String firstName = "Alex", // setValue("Alex")
           // lastName = "Ivanov",
            //userEmail= "Alex@ivanov.com";

    //подход 2
    //String firstName = "Alex", // setValue("Alex")
          //  lastName = "Ivanov",
          //  userEmail= "Alex@ivanov.com";

    //подход 3
    //static String firstName, // setValue("Alex")
            //lastName,
           // userEmail;

    //подход 4
    //@BeforeEach
    //void PrepareTestData(){

      //  firstName = "Alex";
       // lastName = "Ivanov";
       // userEmail= "Alex@ivanov.com";

    //}

    //подход 5
    //объявить в TestBase

    @Test
    void fillStudentRegistrationFormTest() {

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //$("#firstName").setValue("Alex");
        $("#firstName").setValue(firstName);// firstName взят из класса dataRegistrationPage
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
        $("#currentAddress").setValue("Russia");
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
        $(".table-responsive").shouldHave(text("Russia"));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));

    }

}
