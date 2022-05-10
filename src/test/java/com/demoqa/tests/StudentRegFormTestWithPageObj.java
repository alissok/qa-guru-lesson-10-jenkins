package com.demoqa.tests;

import com.demoqa.pages.StudentRegFromPages;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import java.util.Locale;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

public class StudentRegFormTestWithPageObj extends TestSetupAndTeardown {
    Faker faker = new Faker(new Locale("en"));
    RandomStringUtils gen =  new RandomStringUtils();
    //Personal Info
    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            currAddress = faker.address().fullAddress(),
            mobile = gen.randomNumeric(10);
    //States and Cities
    String state = "Haryana";
    String city = "Karnal";
    //Gender
    String gender = "Male";
    //Date
    String month = "June";
    String year = "2006";
    String day = "13";
    //Hobbies, Subject, Picture
    String filename = "reallife.jpg";
    String hobby = "Sports";
    String subject  = "Art";
    //Text after registration
    String thanks = "Thanks for submitting the form";

    //Checking results
    String expFullName = format("%s %s", firstName, lastName),
           expStateCity = format("%s %s", state, city),
           expDateOfBirth = format("%s %s,%s", day, month, year);

    StudentRegFromPages regForm = new StudentRegFromPages();

    @Test
    @Tag("demoqa_reg")
    @DisplayName("Fill Registration Form With Random Data Using Page Objects")
    void fillRegForm() {
        step("Open Registration Form", () -> {
            regForm.openPage();
        });
        step("Fill Registration Form", () -> {
            regForm.setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender(gender)
                    .setNumber(mobile)
                    .setDateOfBirth(day, month, year)
                    .setSubject(subject)
                    .setHobby(hobby)
                    .setUserPic(filename)
                    .setAddress(currAddress)
                    .setStateAndCity(state, city)
                    .pressSubmit();
        });
        step("Check Filled Registration Form", () -> {
            regForm.checkHeader(thanks)
                    .checkResult("Student Name", expFullName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", mobile)
                    .checkResult("Date of Birth", expDateOfBirth)
                    .checkResult("Subjects", subject)
                    .checkResult("Hobbies", hobby)
                    .checkResult("Address", currAddress)
                    .checkResult("State and City", expStateCity)
                    .closeModal();
        });
    }
}
