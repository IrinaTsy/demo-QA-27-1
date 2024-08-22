package pages;

import components.CalendarComponents;
import com.codeborne.selenide.SelenideElement;
import components.CityComponent;
import components.StateComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public class RegistrationPage11 {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
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

    @Step("Открываем главную страницу")
    public RegistrationPage11 openForm(){

        open("/automation-practice-form");

        return this;

        }

    @Step("Отключаем баннеры")
    public RegistrationPage11 removeBanners(){

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;

        }

    @Step("Вводим имя")
    public RegistrationPage11 setFirstName(String value){

        firstNameInput.setValue(value);

        return this;
    }

    @Step("Вводим фамилию")
    public RegistrationPage11 setLastName(String value){

        lastNameInput.setValue(value);

        return this;
    }

    @Step("Вводим Email")
    public RegistrationPage11 setEmail(String value){

        userEmailInput.setValue(value);

        return this;
    }

    @Step("Вводим пол студента")
    public RegistrationPage11 setGenderWapper(String value){

        genderWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Вводим номер мобильного телефона")
    public RegistrationPage11 setUserNumber(String value){

        userNumberInput.setValue(value);

        return this;
    }

    @Step("Вводим дату рождения")
    public RegistrationPage11 setDateBirth(String day, String month, String year){

        calendarInput.click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    @Step("Вводим предмет изучения")
    public RegistrationPage11 setSubjects(String value){

        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Вводим хобби")
    public RegistrationPage11 setHobbies(String value){

        hobbiesInput.click();

        return this;
    }

    @Step("Прикладываем картинку")
    public RegistrationPage11 uploadPicture(String value){

        uploadPicture.uploadFromClasspath(value);
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationPage11 setAddress(String value){

        addressInput.setValue(value);

        return this;
    }

    @Step("Вводим название штата")
    public RegistrationPage11 setState(String value){

        stateComponent.setState(value);

        return this;
    }

    @Step("Вводим название города")
    public RegistrationPage11 setCity(String value){

        cityComponent.setCity(value);

        return this;
    }

    @Step("Нажимаем кнопку Submit")
    public RegistrationPage11 submitButton(){

        submitButton.click();

        return this;
    }

    @Step("Подтверждение результатов")
    public RegistrationPage11 checkResults(String key, String value){

        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }

}
