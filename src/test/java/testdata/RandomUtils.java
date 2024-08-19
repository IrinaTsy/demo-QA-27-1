package testdata;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;


public class RandomUtils {

    public static void main(String[] args) {

        System.out.println(getRandomString(10));
    }

    public static String getRandomString(int len) {
//        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomEmail(){

        return getRandomString(10) + "@qa.guru";

    }

    public static String getRandomStreetAddress(){

        return getRandomString(10) + " " + getRandomString(10)  + " " + getRandomString(10);

    }

    //генерация случайного числа

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    //генерация номера телефона: +3 (263) 253 - 66 - 12
    public static String getRandomPhone() {

        String b = "45";
        //String text = "123" + b + "6789";

        String text = String.format("123%s6789", b);

        return String.format("+%s (%s) %s - %s - %s", getRandomInt(1, 9), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }

    //генерация случайного пола (массив)

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromArray(genders);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

}
