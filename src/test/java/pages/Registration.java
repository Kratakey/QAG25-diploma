package pages;

import com.codeborne.selenide.Condition;
import config.TestBase;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.SelectableModal.selectModal;
import static org.junit.jupiter.api.Assertions.fail;

public class Registration extends TestBase {

    @Step("Open the page")
    public void openPageUrl(String urlUserRegistration) {
        open(urlUserRegistration);
    }

    @Step("Fill the first name")
    public void fillUserFirstName(String userFirstName) {
        $("input[name='name']").setValue(userFirstName);
    }

    @Step("Fill the last name")
    public void fillUserLastName(String userLastName) {
        $("input[name='name']", 1).setValue(userLastName);
    }

    @Step("Fill an email")
    public void fillEmail(String userEmail) {
        $$("input[name='email']").filter(visible).get(0).scrollIntoView(true).setValue(userEmail);
    }

    @Step("Choose a password")
    public void choosePassword(String userPassword) {
        $$("input[name='password']").filter(visible).get(0).scrollIntoView(true).setValue(userPassword);
        $$("input[name='confirm']").filter(visible).get(0).scrollIntoView(true).setValue(userPassword);
    }

    @Step("Fill a phone number : {userCountry} - {userPhoneNumber}")
    public void fillPhoneNumber(String userPhoneNumber, String userCountry) {
        $("app-phone-editor").$("input").setValue(userPhoneNumber);
        $("app-registration-form").$("button[type='button']").click();
        selectModal(userCountry);
    }

    @Step("Select a country: {userCountry}")
    public void selectCountry(String userCountry) {
        $("app-registration-form").$("app-country-selector").$("ion-item").click();
        selectModal(userCountry);
    }

    @Step("Select a city: {userCity}")
    public void selectCity(String userCity) {
        $("app-registration-form").$("app-city-selector").$("ion-item").click();
        selectModal(userCity);
    }

    @Step("Confirm")
    public void confirm() {
        $("app-registration").$("ion-button[type='submit']").shouldBe(visible, Duration.ofSeconds(10));
        $("app-registration").$("ion-button[type='submit']").click();
        sleep(500);
    }

    @Step("Confirm")
    public void confirmAndWait() {
        $("app-registration ion-button[type='submit']").shouldBe(visible, Duration.ofSeconds(10));
        $("app-registration ion-button[type='submit']").click();
        $("app-registration ion-button[type='submit']").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify basic registration data")

    public void verifyRegistrationDataBasic(
            String userFirstName,
            String userEmail,
            String userCountry,
            String userCity
    ) {
        $("app-profile").$(withText(userFirstName)).should(visible);
        $("app-profile").$(withText(userEmail)).should(visible);
        $("app-profile").$(withText(userCountry)).should(visible);
        $("app-profile").$(withText(userCity)).should(visible);

    }

    @Step("Verify full registration data")
    public void verifyRegistrationDataFull(
            String userFirstName,
            String userLastName,
            String userEmail,
            String userPhoneNumber,
            String userCountry,
            String userCity
    ) {
        $("app-profile").$(withText(userFirstName)).should(visible);
        $("app-profile").$(withText(userLastName)).should(visible);
        $("app-profile").$(withText(userEmail)).should(visible);
        $("app-profile").$(withText(userPhoneNumber)).should(visible);
        $("app-profile").$(withText(userCountry)).should(visible);
        $("app-profile").$(withText(userCity)).should(visible);
    }

    @Step("Verify that there is no phone country code autofilled")
    public void verifyNoPhoneCountryCode() {
        String value = $("app-registration app-phone-editor ionic-selectable span").getText();
        if (!value.equals(emptySpace)) {
            System.out.println("Expected empty value, actual value : " + value);
            fail();
        }
    }

    @Step("Verify phone country code - {userCountry}")
    public void verifyPhoneCountryCode(String userCountry) {
        String value = $("app-registration app-phone-editor ionic-selectable").getText();
        if (value.equals(emptySpace)) {
            fail();
        }
        $("app-registration-form").$("button[type='button']").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(userCountry);
        sleep(500);
        $("ion-virtual-scroll").$("ion-item", 0).shouldHave(Condition.cssClass("ionic-selectable-item-is-selected"));
        sleep(500);
        $("ion-virtual-scroll").$("ion-item", 0).pressEscape();
    }

    @Step("Verify phone number filled")
    public void verifyPhoneNumber(String userPhoneNumber) {
        $("app-phone-editor input").parent().shouldHave(Condition.cssClass("has-value"));
    }

    @Step("Complete turor slides to search")
    public void completeTutorSlidesToSearch() {
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
    }

    @Step("Complete turor slides to service publish")
    public void completeTutorSlidesToPublish() {
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(0).scrollIntoView(true).click();
        $("app-registration-tutor").$$("ion-button.registration-tutor-button").filter(visible).get(1).scrollIntoView(true).click();
    }
}