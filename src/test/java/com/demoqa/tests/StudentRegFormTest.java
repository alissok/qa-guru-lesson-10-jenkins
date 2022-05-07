package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.apache.commons.lang3.RandomStringUtils;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Tag("demoqa")
public class StudentRegFormTest extends TestSetup{

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

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com");
    }

    @BeforeEach
    void createScrollablePage() {
        Selenide.executeJavaScript(
                "document.querySelector(\"footer\").hidden = 'true';" +
                        "document.querySelector(\"#fixedban\").hidden = 'true'");
    }

    @Test
    void fillRegForm() {

        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(genterSelector).$(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $(dateOfBirthSelector).$(byId("dateOfBirthInput")).click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--"+day).click();
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

        //Asserts
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
    }
}
