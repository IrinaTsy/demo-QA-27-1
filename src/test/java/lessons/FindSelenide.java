package lessons;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FindSelenide {

    @Test

    void shouldFindSelenideTest(){

        Selenide.open("https://github.com/");                //открыть главную страницу
        //$("title").click();

        $("[placeholder='Search GitHub']").setValue("selenide").pressEnter().click();//ввести в поле поиска selenide и нажать enter
        //$("[placeholder='Search GitHub']").pressEnter();
        //[placeholder='Search GitHub'] - квадратные скобки ставятся, если ищем по атрибуту [placeholder='...'], если по тэгу $("a") - квадратные скобки не ставятся.
        //.class - по имени класса, #id1 - по номеру id, [] - по атрибуту


        $$("ul.repo-list li").first().$("a").click();//кликнуть на первый репозиторий из списка найденных
        //проверка: заголовок selenide/selenide
        //если необходимо нажать линк: линк - a class

        //Порядок написания теста:
        //ARRANGE - подготовить
        //ACT - действие
        //ASSERT - проверка

        $("#repository-container-header").shouldHave(Condition.text("selenide / selenide"));

    }
    }

