package pages;

import config.Lang;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static helpers.SelectableModal.selectModal;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class UserProfile {
    @Step("Verify profile data")
    public void verifyProfile(
            String firstName,
            String lastName,
            String email,
            String country,
            String city) {
        sleep(300);
        $("app-profile").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(country),
                text(city));
    }

    @Step("Verify profile country/city data")
    public void verifyProfileAddressExists(
            String country,
            String city) {
        sleep(300);
        $("app-profile").shouldHave(text(country + ", " + city));
    }

    @Step("Check that verification E-mail sent")
    public void checkVerificationEmailSent(String email) {
        $("app-profile").$("app-info-row").shouldHave(text(email));
    }


    @Step("Verify language")
    public void verifyRussianLang() {
        sleep(500);
        $("app-profile ion-toolbar ion-menu-toggle ion-label").shouldHave(text(Lang.RUSSIAN.getLangText()));
    }


    @Step("Open Profile: Main")
    public void openUserProfileMain() {
        $("app-profile").$("ion-button[routerlink='edit/']").click();
        sleep(500);
    }

    @Step("Click Back: Main")
    public void clickBackMain() {
        $("app-user-edit").$("app-column-header").$("ion-button").click();
        sleep(500);
    }

    @Step("Click Save: Main")
    public void clickSaveMain() {
        $("app-user-edit").$("ion-button[type='submit']").click();
        sleep(3000);
    }

    @Step("Change First name")
    public void inputFirstName(String firstName) {
        $("app-user-edit").$("input[name='first_name']").setValue(firstName);
    }

    @Step("Change Last name")
    public void inputLastName(String lastName) {
        $("app-user-edit").$("input[name='last_name']").setValue(lastName);
    }

    @Step("Change Patronymic")
    public void inputPatronymic(String patronymic) {
        $("app-user-edit").$("input[name='patronymic']").setValue(patronymic);
    }

    @Step("Select gender: male")
    public void selectGenderMale() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 0).click();
    }

    @Step("Select gender: female")
    public void selectGenderFemale() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 1).click();
    }

    @Step("Select gender: no gender")
    public void selectGenderNot() {
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 2).click();
    }

    @Step("Fill a phone number")
    public void fillPhoneNumber(String userPhoneNumber, String userCountry) {
        $("app-phone-editor").$("input").setValue(userPhoneNumber);
        $("app-phone-editor").$("button[type='button']").click();
        selectModal(userCountry);
    }

    @Step("Change E-mail")
    public void inputEmail(String email) {
        $("app-user-edit").$("input[name='email']").setValue(email);
    }

    @Step("Verify Profile: Main - minimal")
    public void verifyProfileMainMin(
            String firstName,
            String lastName,
            String email) {
        sleep(300);
        $("app-user-edit").$("input[name='first_name']").shouldHave(value(firstName));
        $("app-user-edit").$("input[name='last_name']").shouldHave(value(lastName));
        $("app-user-edit").$("input[name='email']").shouldHave(value(email));
    }

    @Step("Verify Profile: Main - full")
    public void verifyProfileMainMax(
            String firstName,
            String lastName,
            String patronymic,
            String phoneNumber,
            String email) {
        sleep(300);
        $("app-user-edit").$("input[name='first_name']").shouldHave(value(firstName));
        $("app-user-edit").$("input[name='last_name']").shouldHave(value(lastName));
        $("app-user-edit").$("input[name='patronymic']").shouldHave(value(patronymic));
        $("app-user-edit").$("app-phone-editor").$("input").shouldHave(value(phoneNumber));
        $("app-user-edit").$("input[name='email']").shouldHave(value(email));
    }

    @Step("Verify Profile: Main - Gender: Male")
    public void verifyGenderMale() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 0).should(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 1).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 2).shouldNot(cssClass("segment-button-checked"));
    }

    @Step("Verify Profile: Main - Gender: Female")
    public void verifyGenderFemale() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 0).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 1).should(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 2).shouldNot(cssClass("segment-button-checked"));
    }

    @Step("Verify Profile: Main - Gender: Not Specified")
    public void verifyGenderNot() {
        sleep(500);
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 0).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 1).shouldNot(cssClass("segment-button-checked"));
        $("app-user-edit").$("app-gender-selector").$("ion-segment-button", 2).should(cssClass("segment-button-checked"));
    }

    @Step("Profile: Contacts - click edit")
    public void clickEditContacts(Integer value) {
        $("app-profile").$("app-contacts-edit").$("ion-icon", value).click();
        sleep(500);
    }

    @Step("Profile: Contacts - click back")
    public void clickBackContacts() {
        $("app-user-contact-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Profile: Contacts - contacts: select contact")
    public void selectContact(String value) {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-select").click();
        sleep(200);
        $("ion-popover").$("ion-select-popover").$(withText(value)).closest("ion-item").click();
    }

    @Step("Profile: Contacts - contacts: enter value")
    public void enterContact(String value) {
        $("app-user-contact-edit").$("app-contact-edit").$("input[name='contact-value']").setValue(value);
    }

    @Step("Profile: Contacts - click 'Add new contact'")
    public void clickAddNewContact() {
        $("app-profile").$("app-contacts-edit").$("app-add-button").$("ion-item").click();
        sleep(500);
    }

    @Step("Profile: Contacts - remove contact")
    public void removeContact() {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-row").$("ion-button", 0).click();
        sleep(500);
    }

    @Step("Profile: Contacts - save contact")
    public void saveContact() {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-button[type='submit']").click();
        sleep(500);
    }

    @Step("Verify contact default")
    public void verifyContactDefault() {
        $("app-user-contact-edit").$("app-contact-edit").$("ion-input[class='ion-pristine']").exists();
    }

    @Step("Verify contact exists")
    public void verifyContactExists(String value) {
        sleep(500);
        $("app-profile").$("app-contacts-edit").shouldHave(text(value));
    }

    @Step("Verify contact removed")
    public void verifyContactRemoved(String value) {
        $("app-profile").$("app-contacts-edit").shouldNotHave(text(value));
    }

    @Step("Verify 'Select Contact'")
    public void verifySelectContact(String value) {
        $("app-profile").$("app-contacts-edit").$("ion-select").$("div").shouldHave(text(value));
    }

    @Step("Verify 'Select Contact' is set default")
    public void verifySelectContactDefault() {
        sleep(500);
        $("app-profile").$("app-contacts-edit").$("ion-select[class='ion-untouched']").exists();
    }

    @Step("Verify 'Enter your contact'")
    public void verifyEnterYourContact(String value) {
        sleep(500);
        $("app-user-contact-edit").$("app-contact-edit").$("input").shouldHave(value(value));
    }

    @Step("Open Profile: Address")
    public void openUserProfileLocationEdit(Integer value) {
        $("app-profile").$("ion-item-group").$("ion-button", value).click();
        sleep(500);
    }

    @Step("Profile address: click Back")
    public void addressClickBack() {
        $("app-user-location-edit").$("app-column-header").$("ion-button").click();
    }

    @Step("Profile address: click Save")
    public void addressClickSave() {
            $("app-user-location-edit app-location-editor ion-button[id='save-btn']").click();
    }

    @Step("Profile address: click Remove")
    public void addressClickRemove() {
        if ($("app-user-location-edit app-location-editor ion-button[id='save-btn']").exists()) {
            $("app-user-location-edit app-location-editor ion-button").click();
        } else {
            System.out.println("Can not verify remove button.");
            fail();
        }
    }

    @Step("Profile: remove address")
    public void removeAddress() {
        $("app-user-contact-edit app-contact-edit ion-row ion-button", 0).click();
        sleep(500);
    }

    @Step("Verify Profile: Address")
    public void verifyProfileAddress(
            String country,
            String city) {
        sleep(300);
        $("app-user-location-edit").$("app-location-editor").$("app-country-selector").shouldHave(text(country));
        $("app-user-location-edit").$("app-location-editor").$("app-city-selector").shouldHave(text(city));
    }

    @Step("Profile: address - click 'Add new address'")
    public void clickAddNewAddress() {
        $("app-profile app-add-button[routerlink='location-add/'] ion-item").click();
        $("app-user-location-edit app-location-editor ion-button").shouldBe(visible, Duration.ofSeconds(10));
    }

    public void selectAddress(int value) {
        step("Profile: select address, index: " + value, () -> {
            $("app-profile main ion-item-group ion-button" , value).click();
            $("app-user-location-edit app-location-editor ion-button").shouldBe(visible, Duration.ofSeconds(10));
        });
    }

    @Step("Profile address: select country")
    public void addressSelectCountry(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-country-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile address: select region")
    public void addressSelectRegion(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-region-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile address: select subregion")
    public void addressSelectSubregion(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-subregion-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile address: select city")
    public void addressSelectCity(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-city-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$(byText(value)).click();
    }

    @Step("Profile address: select district")
    public void addressSelectDistrict(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-district-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile address: select zip code")
    public void addressSelectZipCode(String value) {
        $("app-user-location-edit").$("app-location-editor").$("app-postal-code-selector").$("ion-item").click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile address: select address")
    public void addressSelectAddress(String value) {
        $("app-user-location-edit").$("app-location-editor").$("input").sendKeys(value);
    }

    @Step("Profile address: make default click")
    public void addressClickMakeDefault() {
        $("app-user-location-edit").$("app-location-editor").$("ion-checkbox").click();
    }

    @Step("Verify profile address: Address full")
    public void verifyAddressDefault(
            String country,
            String region,
            String city) {
        sleep(300);
        $("app-user-location-edit").$("app-location-editor").$("app-country-selector").shouldHave(text(country));
        $("app-user-location-edit").$("app-location-editor").$("app-region-selector").shouldHave(text(region));
        $("app-user-location-edit").$("app-location-editor").$("app-city-selector").shouldHave(text(city));
    }

    @Step("Verify profile address: Address full")
    public void verifyAddressFull(
            String country,
            String region,
            String subregion,
            String city,
            String district,
            String zipCode,
            String address) {
        sleep(300);
        $("app-user-location-edit").$("app-location-editor").$("app-country-selector").shouldHave(text(country));
        $("app-user-location-edit").$("app-location-editor").$("app-region-selector").shouldHave(text(region));
        $("app-user-location-edit").$("app-location-editor").$("app-subregion-selector").shouldHave(text(subregion));
        $("app-user-location-edit").$("app-location-editor").$("app-city-selector").shouldHave(text(city));
        $("app-user-location-edit").$("app-location-editor").$("app-district-selector").shouldHave(text(district));
        $("app-user-location-edit").$("app-location-editor").$("app-postal-code-selector").shouldHave(text(zipCode));
        $("app-user-location-edit").$("app-location-editor").$("ion-input").shouldHave(text(address));
    }

    @Step("Verify address removed")
    public void verifyAddressRemoved(String country, String city) {
        $("app-profile").$("app-contacts-edit").shouldNotHave(text(country + ", " + city));
    }

    @Step("Verify address removed")
    public void verifyAddressRemoved(String country, String city, String address) {
        $("app-profile").$("app-contacts-edit").shouldNotHave(text(country + ", " + city + ", " + address));
    }

    @Step("Open Profile: About")
    public void openUserProfileAbout() {
        $("app-profile ion-button[routerlink='about/']").click();
        sleep(500);
    }

    @Step("Profile: About - click 'back'")
    public void clickBackAbout() {
        $("app-about-edit app-column-header ion-button").click();
        sleep(500);
    }

    @Step("Verify 'About'")
    public void verifyAbout(String nationality, String language, String dateDD, String dateMM, String dateYYYY) {
        sleep(500);
        $("app-profile ion-button[routerlink='about/']").scrollIntoView(true);
        $("app-profile main").shouldHave(text(nationality), text(language), text(dateYYYY + "-" + dateMM + "-" + dateDD));
    }

    @Step("Verify 'About' language")
    public void verifyAboutExtraLanguage(String language) {
        sleep(500);
        $("app-profile main").shouldHave(text(language));
    }


    @Step("Verify 'About' default")
    public void verifyAboutDefault() {
        sleep(500);
        $("app-about-edit form ion-input[class='ng-pristine']", 0).exists();
        $("app-about-edit form ionic-selectable[class='ion-untouched']", 0).exists();
        $("app-about-edit form ionic-selectable[class='ion-untouched']", 1).exists();
    }

    @Step("Profile: About - select birth date")
    public void selectDateBirth(String dateDD, String dateMM, String dateYYYY) {
        $("app-about-edit form input").setValue(dateDD + dateMM + dateYYYY);
    }

    @Step("Profile: About - select nationality")
    public void selectNationality(String value) {
        sleep(500);
        $("app-about-edit").$("form").$("button", 0).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
    }

    @Step("Profile: About - select languages")
    public void selectLanguage(String value) {
        sleep(500);
        $("app-about-edit").$("form").$("button", 1).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").sendKeys(value);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).click();
        $("ionic-selectable-modal").$("ion-footer").$("ion-button", 1).click();
        sleep(500);
    }

    @Step("Profile: About - click 'save'")
    public void clickSaveAbout() {
        $("app-about-edit").$("form").$("ion-button").click();
        sleep(2000);
    }

}