import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("[id = firstName]").setValue("Testname");
        $("[id = lastName]").setValue("TestLastName");
        $("[id = userEmail]").setValue("qaguru@test.ru");

        //Radiobutton
        $("[type='radio'][value='Male']").parent().click();

        $("[id = userNumber]").setValue("1111111111");
        //Calendar
        $("[id = dateOfBirthInput]").click();
//        $("[id = dateOfBirthInput]").setValue("01 Dec 2022").pressEnter();
        $("[class=\"react-datepicker__year-select\"]").$("[value=\"2000\"]").click();
        $("[class=\"react-datepicker__month-select\"]").$("[value=\"7\"]").click();
        $("[class=\"react-datepicker__day react-datepicker__day--011\"]").click();

        //Subject
        $("[id = subjectsInput]").setValue("Physics").pressEnter();

        $("[for=\"hobbies-checkbox-1\"]").click();
        $("[for=\"hobbies-checkbox-2\"]").click();

        //file
        $("[id = uploadPicture]").uploadFile(new File("src/test/resources/img.jpg"));
        //Address
        $("[id = currentAddress]").setValue("Test Address");

        $("[id = react-select-3-input]").setValue("NCR").pressEnter();
        $("[id = react-select-4-input]").setValue("Delhi").pressEnter();

        $("[id = submit]").click();

        //AfterClick Submit
        $("[id = example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));
        $("[class=\"table table-dark table-striped table-bordered table-hover\"]")
                .$("tbody").shouldHave(text("Testname TestLastName"),
                text("qaguru@test.ru"),
                text("Male"),
                text("1111111111"),
                text("11 August,2000"),
                text("Physics"),
                text("Sports, Reading"),
                text("img.jpg"),
                text("Test Address"),
                text("NCR Delhi"));

    }
}