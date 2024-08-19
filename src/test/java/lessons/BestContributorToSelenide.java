package lessons;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorToSelenide {

    @Test

    void andreySolntsev(){

        //запустить тест в броузере firefox
        //Configuration.browser = "firefox";

        //открыть страницу репозитория селенида
        open("https://github.com/selenide/selenide");

        //подвести мышку к первому аватару из блока Contributors
        $("div.layout-sidebar").$(Selectors.byText("Contributors"))
                //.closest(".BorderGrid-sell").$$("ul li").first().hover()
                .closest("h2").sibling(0).$$("li").first().hover();
        //проверка: во всплывающем окне есть текст Andrey Solntsev
        $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));

    }

    @Test

    void closeCookie(){

        //закрыть куки, используя текст в DOM-дереве
        open("https://otpbank.ru/retail/bank-sevices/");
        $(withText("Продолжая просмотр сайта")).shouldBe(visible);
        $(byTagAndText("strong","Закрыть")).click();
        $(withText("Продолжая просмотр сайта")).shouldBe(hidden);
    }

   @Test

    void required(){

        //если в DOM-дереве отсутсвует элемент
       $("input#username").shouldHave(attribute("required"));
   }
}
