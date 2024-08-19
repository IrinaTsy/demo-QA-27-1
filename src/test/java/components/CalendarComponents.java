package components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponents {

    public void setDate(String day, String month, String year){


        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1979");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__day--030").click();


    }
}
