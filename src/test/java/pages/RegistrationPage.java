package pages;

import components.CalendarComponents;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    //переменные - указываются в тесте вместо локаторов

    //public static String firstNameLocator = "#firstName";
    //public static SelenideElement firstNameInput = $("#firstName");
    private SelenideElement firstNameInput = $("#firstName"),
                             lastNameInput = $("#lastName"),
                             userEmailInput = $("#userEmail"),
                             genderWrapper = $("#genterWrapper"),
                             userNumberInput = $("#userNumber"),
                             dateBirthInput = $("#userNumber"),
                             calendarInput = $("#dateOfBirthInput");

    CalendarComponents calendarComponents = new CalendarComponents();

   // @Step("Open registration page /automation-practice-form") //получение отчетности из Аллюра
    //public void openForm(){
    public RegistrationPage openForm(){

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;

    }

    //@Step("Set {{value}} to first name input")
    //$(firstNameLocator).setValue("Alex");
    // public void setFirstName(){
    //public void setFirstName(String value){
    public RegistrationPage setFirstName(String value){

        // $(firstNameLocator).sendKeys(value);
        firstNameInput.setValue(value);

        return this; //если присутствует конструкция public void setFirstName(String value), return this не писать
    }

    //public void setLastName(String value){
    public RegistrationPage setLastName(String value){

        // $(firstNameLocator).sendKeys(value);
        lastNameInput.setValue(value);

        return this;
    }

    //public void setEmail(String value){
    public RegistrationPage setEmail(String value){

        // $(firstNameLocator).sendKeys(value);
        userEmailInput.setValue(value);

        return this;
    }

    //public void setGenderWapper(String value){
    public RegistrationPage setGenderWapper(String value){

    // $(firstNameLocator).sendKeys(value);
        genderWrapper.$(byText(value)).click();

        return this;
    }

    //public void setUserNumber(String value){
    public RegistrationPage setUserNumber(String value){

        // $(firstNameLocator).sendKeys(value);
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateBirth(String day, String month, String year){

        calendarInput.click();
        calendarComponents.setDate(day, month, year); //из класса CalendarComponents

        //перенесли в класс CalendarComponents для возможности использования в др.методах, где используется календарь
        //$(".react-datepicker__month-select").click();
        //$(".react-datepicker__month-select").selectOption("December");
        //$(".react-datepicker__month-select").click();
        //$(".react-datepicker__year-select").click();
        //$(".react-datepicker__year-select").selectOption("1979");
        //$(".react-datepicker__year-select").click();
        //$(".react-datepicker__day--030").click();

        return this;
    }

    public RegistrationPage checkResults(String key, String value){

        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));

        return this;
    }
}
