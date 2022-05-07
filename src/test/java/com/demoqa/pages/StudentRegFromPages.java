package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegFromPages {

    CalendarComponent calendar = new CalendarComponent();
    //locators
    SelenideElement stateAndCitySelector = $("#stateCity-wrapper");

    // actions
    public StudentRegFromPages openPage(){
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public StudentRegFromPages setFirstName(String value){
        $("#firstName").setValue(value);

        return this;
    }

    public StudentRegFromPages setLastName(String value){
        $("#lastName").setValue(value);

        return this;
    }

    public StudentRegFromPages setEmail(String value){
        $("#userEmail").setValue(value);

        return this;
    }

    public StudentRegFromPages setGender(String value){
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public StudentRegFromPages setNumber(String value){
        $("#userNumber").setValue(value);

        return this;
    }

    public StudentRegFromPages setDateOfBirth(String day, String month, String year){
        $("#dateOfBirth-wrapper").$(byId("dateOfBirthInput")).click();
        calendar.setDate(day,month,year);

        return this;
    }

    public StudentRegFromPages setHobby(String value){
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }

    public StudentRegFromPages setUserPic(String filename){
        $("#uploadPicture").uploadFromClasspath(filename);

        return this;
    }

    public StudentRegFromPages setAddress (String address){
        $("#currentAddress").setValue(address);

        return this;
    }

    public StudentRegFromPages setSubject(String value) {
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }

    public StudentRegFromPages setStateAndCity (String state, String city){

        $(byText("State and City")).scrollTo();
        stateAndCitySelector.$(byText("Select State")).click();
        stateAndCitySelector.$(byText(state)).click();
        stateAndCitySelector.$(byText("Select City")).click();
        stateAndCitySelector.$(byText(city)).click();

        return this;
    }

    public StudentRegFromPages pressSubmit(){
        $("#submit").pressEnter();

        return this;
    }

    public StudentRegFromPages checkHeader(String value) {
        $("#example-modal-sizes-title-lg").shouldHave(text(value));

        return this;
    }

    public StudentRegFromPages checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

    public StudentRegFromPages closeModal() {
        $("#closeLargeModal").click();

        return this;
    }

}
