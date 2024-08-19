package lessons;

import pages.RegistrationPage;
import org.junit.jupiter.api.Test;

//import static Pages.RegistrationPage.firstNameLocator;
import static com.codeborne.selenide.Selenide.$;


public class PO1 extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void fillStudentRegistrationForm() {

        //open("/automation-practice-form");
        //executeJavaScript("$('#fixedban').remove()");
        //executeJavaScript("$('footer').remove()");
        registrationPage.openForm()

        //$("#firstName").setValue("Alex");
        //$(firstNameLocator).setValue("Alex");
        //new RegistrationPage().setFirstNameAlex(); //итог замены трех вышенаписанных строк
        //registrationPage.setFirstName("Alex");
                        .setFirstName("Alex")

        //$("#lastName").setValue("Ivanov");
                        .setLastName("Ivanov")

        //$("#userEmail").setValue("Alex@ivanov.com");
                        .setEmail("Alex@ivanov.com")

        //$("#gender-radio-1+label").click();
                        .setGenderWapper("male")

        //$("#userNumber").setValue("8888888888");
                        .setUserNumber("8888888888")

        //$("#dateOfBirthInput").click();
        //$(".react-datepicker__month-select").click();
        //$(".react-datepicker__month-select").selectOption("December");
        //$(".react-datepicker__month-select").click();
        //$(".react-datepicker__year-select").click();
        //$(".react-datepicker__year-select").selectOption("1979");
        //$(".react-datepicker__year-select").click();
        //$(".react-datepicker__day--030").click();
                        .setDateBirth("30", "November", "1979");

        $("#subjectsInput").setValue("Biology").pressEnter();
        $("#hobbies-checkbox-1+label").click();
        $("#uploadPicture").uploadFromClasspath("picture.jpg");
        $("#currentAddress").setValue("Russia");
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        //$(".table-responsive").shouldHave(text("Alex Ivanov"));
        //$(".table-responsive").shouldHave(text("Alex@ivanov.com"));
        //$(".table-responsive").shouldHave(text("Male"));
        //$(".table-responsive").shouldHave(text("8888888888"));
        //$(".table-responsive").shouldHave(text("30 November,1979"));
        //$(".table-responsive").shouldHave(text("Biology"));
        //$(".table-responsive").shouldHave(text("Sports"));
        //$(".table-responsive").shouldHave(text("picture.jpg"));
        //$(".table-responsive").shouldHave(text("Russia"));
        //$(".table-responsive").shouldHave(text("Haryana Karnal"));

        registrationPage.checkResults("Student Name", "Alex Ivanov");
        registrationPage.checkResults("Student Email", "Alex@ivanov.com");
    }

}

