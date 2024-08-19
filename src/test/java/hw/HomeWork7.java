package hw;

import lessons.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage7;

import testdata.Data7;

@Disabled
public class HomeWork7 extends TestBase {

    RegistrationPage7 registrationPage = new RegistrationPage7();
    Data7 data13 = new Data7();

    @Test

    void fillStudentRegistrationFormTest() {



        registrationPage.openForm()
                .removeBanners()
                .setFirstName(data13.firstName)
                .setLastName(data13.lastName)
                .setEmail(data13.userEmail)
                .setGenderWrapper(data13.userGender)
                .setUserNumber(data13.userNumber)
                .setDateBirth(data13.day, data13.month, data13.year)
                .setSubjects(data13.subject)
                .setHobbies(data13.hobbie)
                .uploadPicture(data13.picture)
                .setAddress(data13.userAddress)
                .setState(data13.userState)
                .setCity(data13.userCity)
                .submitButton()

                .checkResults("Student Name", data13.firstName + " " + data13.lastName)
                .checkResults("Student Email", data13.userEmail)
                .checkResults("Gender", data13.userGender)
                .checkResults("Mobile", data13.userNumber)
                .checkResults("Date of Birth", data13.day + " "
                        + data13.month + ','
                        + data13.year)
                .checkResults("Subjects", data13.subject)
                .checkResults("Hobbies", data13.hobbie)
                .checkResults("Picture", data13.picture)
                .checkResults("Address", data13.userState)
                .checkResults("State and City", data13.userState + " " + data13.userCity);
    }



}
