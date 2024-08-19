import static com.codeborne.selenide.Selenide.$;

public class LoginPO {

    //pageObject

    public void loginWithPassportAndCard(String card, String password){


    }

    public void loginWithUsernameAndPassword(String username, String password){

    //public HomePagePO loginWithUsernameAndPassword(String username, String password){

        $("#username").setValue(username);
        $("#password").setValue(password);
        $("#submit-button").click();
        //return new HomePagePO; //перейти на сл.страницу

    }

    public void loginWithUsernameAndPasswordNegative(String username, String password){
    //public LoginPO loginWithUsernameAndPasswordNegative(String username, String password){

        $("#username").setValue(username);
        $("#password").setValue(password);
        $("#submit-button").click();
        //return this; //остаться на странице регистрации, не переходя на следующую

}
}
