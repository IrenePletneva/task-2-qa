import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Tests {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Testname");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("qaguru@test.ru");

        //Radiobutton
        $("[type='radio'][value='Male']").parent().click();

        $("#userNumber").setValue("1111111111");
        //Calendar
        $("#dateOfBirthInput").click();
//        $("[id = dateOfBirthInput]").setValue("01 Dec 2022").pressEnter();
        $(".react-datepicker__year-select").$("[value=\"2000\"]").click();
        $(".react-datepicker__month-select").$("[value=\"7\"]").click();
//        $("[class=\"react-datepicker__day react-datepicker__day--011\"]").click();
        $(".react-datepicker__day--011").click();

        //Subject
        $("#subjectsInput").setValue("Physics").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();
//        $("[for=\"hobbies-checkbox-1\"]").click();
//        $("[for=\"hobbies-checkbox-2\"]").click();

        //file
        $("#uploadPicture").uploadFromClasspath("img.jpg");
        //Address
        $("#currentAddress").setValue("Test Address");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();


        $("#submit").click();

        //AfterClick Submit
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive")
                .$("tbody").shouldHave(text("Testname TestLastName"),
                text("qaguru@test.ru"),
                text("Male"),
                text("1111111111"),
                text("11 August,2000"),
                text("Physics"),
                text("Sports"),
                text("img.jpg"),
                text("Test Address"),
                text("NCR Delhi"));

    }
}