package hw;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage11;


import java.util.Map;

public class HomeWork11 {

    @BeforeAll
    static void BeforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        // Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";//запуск тестов через
        //сервер интернет-ферм Selenoid

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

    }

    RegistrationPage11 registrationPage = new RegistrationPage11();

    @Test
    @Tag("demoqaHW")
    void successfulFillStudentRegistrationFormTest() {


        registrationPage.openForm()
                .removeBanners()
                .setFirstName("Alex")
                .setLastName("Ivanov")
                .setEmail("Alex@ivanov.com")
                .setGenderWapper("Male")
                .setUserNumber("8888888888")
                .setDateBirth("30", "November", "1979")
                .setSubjects("Biology")
                .setHobbies("Sports")
                .uploadPicture("picture.jpg")
                .setAddress("Russia")
                .setState("Haryana")
                .setCity("Karnal")
                .submitButton()

                .checkResults("Student Name", "Alex Ivanov")
                .checkResults("Student Email", "Alex@ivanov.com")
                .checkResults("Gender", "Male")
                .checkResults("Mobile", "8888888888")
                .checkResults("Date of Birth", "30 November,1979")
                .checkResults("Subjects", "Biology")
                .checkResults("Hobbies", "Sports")
                .checkResults("Picture", "picture.jpg")
                .checkResults("Address", "Russia")
                .checkResults("State and City", "Haryana Karnal");
    }

}
