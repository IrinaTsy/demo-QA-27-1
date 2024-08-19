package testdata;

import com.github.javafaker.Faker;

public class Data7 {

    Faker faker = new Faker();

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userGender = faker.options().option("Male"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%s", faker.number().numberBetween(1, 28)),
            month = faker.options().option("January", "February",
                    "March", "April", "May", "June",
                    "July", "August", "September",
                    "October", "November", "December"),
            year = String.format("%s", faker.number().numberBetween(1979, 2020)),
            subject = faker.options().option("Maths", "Chemistry", "English",
                    "Computer Science"),
            hobbie = faker.options().option("Sports", "Reading", "Music"),
            picture = faker.options().option("picture.JPG"),
            userAddress = faker.address().country(),
            userState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            userCity = getRandomCity(userState);

    public String getRandomCity(String value) {
        if (userState.equals("NCR")) userCity = faker.options().option("Delhi", "Gurgaon", "Noida");
        if (userState.equals("Uttar Pradesh")) userCity = faker.options().option("Agra", "Lucknow", "Merrut");
        if (userState.equals("Haryana")) userCity = faker.options().option("Karnal", "Panipat");
        if (userState.equals("Rajasthan")) userCity = faker.options().option("Jaipur", "Jaiselmer");
        return userCity;
    }
}
