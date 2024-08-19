package lessons;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

// @Tag("") если установить аннотацию тест перед классом, то в запустится все тесты из этого класса
public class ParametrizedTests {

    @BeforeEach //предусловие
    void setUp() {
        open("https://duckduckgo.com/");
    }

    //параметризованный тест для одного аргумента
    @ValueSource(strings = {
            "Selenide", "JUnit 5", "Allure"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    //параметризованный тест - это такой тест-кейс, с одним и тем же набором шагов, но с разными
    //входными данными

    //@ValueSource - может использоваться в случае, если в массиве есть только один аргумент (searchResultsShouldNotBeEmpty(String searchQuery - массив)),
    //называется ДатаПровайдер

    // {0} - номер ячейки (нулевой аргумент) из void searchResultsShouldNotBeEmpty(String searchQuery, ..., ...)
    // String searchQuery - 1-й элемент в нулевой ячейке массива searchResultsShouldNotBeEmpty

    @Tag("BLOCKER")
    // @Tag - разметка тестов для удобства их запуска

    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    //параметризованный тест для двух аргументов
    //есть возможность передачи других переменных, кроме string
    @CsvSource(value = {
            "Selenide , https://selenide.org", // "Selenide , https://selenide.org, 40.23 (double),
            // false (boolean)"
            //void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink, double i, boolean b)
            "JUnit 5 , https://junit.org"

            //"Selenide | Hello,Selenide", // | - символ разделителя
            //"JUnit 5 | https://junit.org"
            //},delimeter = '|')
    })

    //если много разных входных тестовых данных
    @CsvFileSource(resources = "/searchResultsShouldContainExpectedUrl.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("Для поискового запроса 'junit 5' должен отдавать не пустой список карточек")
        //описание теста
    void successfulSearchJUnitTest() {
        $("#searchbox_input").setValue("junit 5").pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("Для поискового запроса 'selenide' должен показываться не пустой список фото")
    void successfulSearchPhotoTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();
        $("[data-zci-link='images']").click();

        $$("img.tile--img__img")
                .shouldBe(sizeGreaterThan(0));
    }


    //тесты на email рассылку

    @Test
    @DisplayName("Email должен быть отправлен новому юзеру")
    void emailShouldBeSentForNewUser() {
        System.out.println("Hello^ World!");
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Email должен быть отправлен забаненому юзеру")
    void emailShouldBeSentForBannedUser() {
        System.out.println("Hello^ World!");
    }

    //@Disabled(номер дефекта) - реализация исключения упавшего теста, в случае низкого приоритета дефекта
    @Test
    @Tags({                 //если нужно указать, что данный тест запускается для группы тестов SMOKE и для
            //группы тестов WEB
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @DisplayName("Email должен быть отправлен в случае изменения PaymentMethod")
    void emailShouldBeSentAfterChangePaymentMethod() {
        throw new AssertionError("Падаем!");
    }
}

 //использвание списков Enum (не меняющиеся данные, например: название месяцев, дней недели, языки и т.д.) в параметризованных тестах
// см. класс Language
// ссылка на репозиторий https://github.com/qa-guru/qa_guru_23_junit/blob/master/src/test/java/guru/qa/SelenideWebTest.java
//@EnumSource - для массивов, включающих в себя один элемент (void selenideSiteShouldDisplayCorrectText(Language language))

    //@EnumSource(Language.class)
   // @ParameterizedTest
   // void selenideSiteShouldDisplayCorrectText(Language language) {
      //  open("https://ru.selenide.org/");
       // $$("#languages a").find(text(language.name())).click();
       // $("h3").shouldHave(text(language.description));
    //}


//@MethodSource - для массивов, включающих в себя любое количество любых типов данных, включая коллекции
   //static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons() {
     // return Stream.of(
           //   Arguments.of(
               //        Language.EN,
               //      List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"),

             // ),
             // Arguments.of(
              //          Language.RU,
              //        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"),

               //)
      // );
    //}

   // @MethodSource
   // @ParameterizedTest
  //  void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
   //   open("https://ru.selenide.org/");
   //    $$("#languages a").find(text(language.name())).click();
   //   $$(".main-menu-pages a").filter(visible)
    //           .shouldHave(texts(expectedButtons));
  //  }


