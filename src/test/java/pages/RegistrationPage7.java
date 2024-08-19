package pages;

import components.CalendarComponents;
import com.codeborne.selenide.SelenideElement;
import components.CityComponent;
import components.StateComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RegistrationPage7 {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapperInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbies-checkbox-1+label"),
            uploadPicture = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            submitButton = $("#submit"),
            emailWrapper = $("#userEmail");



    CalendarComponents calendarComponents = new CalendarComponents();
    StateComponent stateComponent = new StateComponent();
    CityComponent cityComponent = new CityComponent();


    public RegistrationPage7 openForm(){

        open("/automation-practice-form");

        return this;

    }

    public RegistrationPage7 removeBanners(){


        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;

    }


    public RegistrationPage7 setFirstName(String value){

        firstNameInput.setValue(value);

        return this;
    }


    public RegistrationPage7 setLastName(String value){

        lastNameInput.setValue(value);

        return this;
    }


    public RegistrationPage7 setEmail(String value){

        userEmailInput.setValue(value);

        return this;
    }


    public RegistrationPage7 setGenderWrapper(String value){

        genderWrapperInput.$(byText(value)).click();

        return this;
    }


    public RegistrationPage7 setUserNumber(String value){

        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage7 setDateBirth(String day, String month, String year){

        calendarInput.click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    public RegistrationPage7 setSubjects(String value){

        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage7 setHobbies(String value){

        hobbiesInput.click();

        return this;
    }

    public RegistrationPage7 uploadPicture(String value){

        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage7 setAddress(String value){

        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage7 setState(String value){

        stateComponent.setState(value);

        return this;
    }

    public RegistrationPage7 setCity(String value){

        cityComponent.setCity(value);

        return this;
    }

    public RegistrationPage7 submitButton(){

        submitButton.click();

        return this;
    }

    public RegistrationPage7 checkResults(String key, String value){

        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }
}
