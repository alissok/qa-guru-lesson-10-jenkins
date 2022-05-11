package com.demoqa.tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.apache.commons.lang3.RandomStringUtils;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegFormTest extends TestSetupAndTeardown {

    //Personal Info
    RandomStringUtils gen =  new RandomStringUtils();
    String firstName = gen.randomAlphabetic(6);
    String lastName = gen.randomAlphabetic(10);
    String userEmail = gen.randomAlphabetic(7) + "@gmail.com";
    String mobile = gen.randomNumeric(10);
    String currAddress = gen.randomAlphabetic(15) + " " + gen.randomNumeric(3);
    //States and Cities
    SelenideElement stateAndCitySelector = $("#stateCity-wrapper");
    String state = "Haryana";
    String city = "Karnal";
    //Gender
    SelenideElement genterSelector = $("#genterWrapper");
    String gender = "Male";
    //Date
    SelenideElement dateOfBirthSelector = $("#dateOfBirth-wrapper");
    String month = "June";
    String year = "2006";
    String day = "013";
    //Hobbies, Subject, Picture
    String filename = "reallife.jpg";
    String hobby = "Sports";
    String subject  = "Art";
    SelenideElement hobbiesSelector = $("#hobbiesWrapper");
    //Text after registration
    String thanks = "Thanks for submitting the form";

    @Test
    @Tag("demoqa_reg")
    @DisplayName("Fill Registration Form With Random Data")
    void fillRegForm() {
        step("Open Registration Form", () -> {
            open("/automation-practice-form");
        });
        step("Fill Registration Form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            $(genterSelector).$(byText(gender)).click();
            $("#userNumber").setValue(mobile);
            $(dateOfBirthSelector).$(byId("dateOfBirthInput")).click();
            $(".react-datepicker__month-select").selectOption(month);
            $(".react-datepicker__year-select").selectOption(year);
            $(".react-datepicker__day--" + day).click();
            $("#subjectsInput").setValue(subject).pressEnter();
            $(hobbiesSelector).$(byText(hobby)).click();
            $("#uploadPicture").uploadFromClasspath(filename);
            $("#currentAddress").setValue(currAddress);
            $(byText("State and City")).scrollTo();
            $(stateAndCitySelector).$(byText("Select State")).click();
            $(stateAndCitySelector).$(byText(state)).click();
            $(stateAndCitySelector).$(byText("Select City")).click();
            $(stateAndCitySelector).$(byText(city)).click();
            $("#submit").pressEnter();
        });
        step("Check Filled Registration Form", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text(thanks));
            $(".table-responsive").shouldHave(
                    text(firstName),
                    text(lastName),
                    text(userEmail),
                    text(gender),
                    text(mobile),
                    text(month + "," + year),
                    text(subject),
                    text(filename),
                    text(currAddress),
                    text(state + " " + city)
            );
            $("#closeLargeModal").click();
            $(".practice-form-wrapper").shouldNotHave(//Check if form cached personal data
                    text(firstName),
                    text(lastName),
                    text(userEmail),
                    text(currAddress)
            );

        });

    }
}
