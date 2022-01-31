package pages;

import com.github.javafaker.Faker;
import config.TestBase;
import helpers.Attach;
import io.qameta.allure.Step;

import java.io.File;
import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.DateTimeFormatter.monthConvertToNumber;
import static org.junit.jupiter.api.Assertions.fail;

public class ProfessionalProfile extends TestBase {

    @Step("Click social share button")
    public void clickSocialShare() {
        $$("ion-icon[name='share-social-outline']").filter(visible).get(0).scrollIntoView(false).parent().click();
    }

// other person's professional profile methods

    @Step("Open the chat")
    public void clickChat() {
        $("app-professional-page").$("#chatbtn").click();
    }

// your own professional profile methods

    @Step("Open my professional profile via link")
    public void openMyProfessionalProfileLink() {
        open(urlMyProfessionalProfile);
    }

    @Step("Upload 'Best works' picture: {image}")
    public void uploadBestWorks(String image) {
        $("app-professional-page").$("app-image-carousel").$("input[type='file']").uploadFile(new File(image));
        sleep(5000);
    }

    @Step("Expand all collapse items")
    public void expandItems() {
        sleep(500);
        while ($("app-professional-page").$("app-collapse-item ion-item.item-lines-full").exists()) {
            $("app-professional-page").$("app-collapse-item ion-item.item-lines-full").scrollIntoView(true).click();
            sleep(100);
        }
    }

    @Step("Collapse all collapse items")
    public void collapseItems() {
        sleep(500);
        while ($("app-professional-page").$("app-collapse-item ion-item.item-lines-none").exists()) {
            $("app-professional-page").$("app-collapse-item ion-item.item-lines-none").scrollIntoView(true).click();
            sleep(100);
        }
    }

    @Step("Open reviews")
    public void clickReviews() {
        $("app-professional-page").$("a.review-count").scrollIntoView(true).click();
    }

    @Step("Click avatar edit button")
    public void clickAvatarEdit() {
        $("app-professional-page").$("a[routerlink='/profile'] img").scrollIntoView(true).click();
    }

    @Step("Click create reservation button")
    public void clickCreateReservation() {
        $("app-professional-page").$("ion-button.book-btn").scrollIntoView(true).click();
    }

    @Step("Professional Profile: verify profile data - basic")
    public void verifyProfessionalProfileBasic(
            String firstName,
            String lastName,
            String masterCountry,
            String masterCity,
            String masterAddress,
            String masterAbout,
            String masterLevel
    ) {
        $("app-professional-page").$("app-professional-card-large").scrollIntoView(true).shouldHave(text(firstName + " " + lastName));
        $("app-professional-page").$("app-location-viewer").scrollIntoView(true).shouldHave(text(masterCountry + ", " + masterCity + ", " + masterAddress));
        if ($("app-professional-page").$("app-shorten").$("span.ext").exists()) {
            $("app-professional-page").$("app-shorten").$("span.ext").click();
        }
        $("app-professional-page").$("app-shorten").scrollIntoView(true).shouldHave(text(masterAbout));
        $("app-professional-page").$("main").scrollIntoView(true).shouldHave(text(masterLevel));
    }

    @Step("Professional Profile: verify professional experience {value}")
    public void verifyProfessionalExp(
            String value
    ) {
        Attach.screenshotAs("Screenshot");
        $("app-professional-page").$("main").scrollIntoView(true).shouldHave(text(value));
    }

    @Step("Professional Profile: verify professional name ({firstName} {lastName})")
    public void verifyProfessionalProfileName(String firstName, String lastName) {
        $("app-professional-page").$("app-professional-card-large").scrollIntoView(true).shouldHave(text(firstName + " " + lastName));

    }

    @Step("Professional Profile: verify main")
    public void verifyProfessionalProfileMain(
            String masterCountry,
            String masterCity,
            String masterAddress,
            String masterAbout,
            String masterExperience,
            String masterLevel
    ) {
        $("app-professional-page").$("app-location-viewer").scrollIntoView(true).shouldHave(text(masterCountry + ", " + masterCity + ", " + masterAddress));
        if ($("app-professional-page").$("app-shorten").$("span.ext").exists()) {
            $("app-professional-page").$("app-shorten").$("span.ext").scrollIntoView(true).click();
        }
        $("app-professional-page").$("app-shorten").scrollIntoView(true).shouldHave(text(masterAbout));

        String masterExperienceX = String.valueOf(Long.parseLong(masterExperience) + 1);
        if ($("app-professional-page").$("main").scrollIntoView(true).has(text(masterExperience))) {
            $("app-professional-page").$("main").scrollIntoView(true).shouldHave(text(masterExperience));
        } else if ($("app-professional-page").$("main").scrollIntoView(true).has(text(masterExperienceX))) {
            $("app-professional-page").$("main").scrollIntoView(true).shouldHave(text(masterExperienceX));
        } else {
            fail();
        }

        $("app-professional-page").$("main").scrollIntoView(true).shouldHave(text(masterLevel));
    }

    //address

    @Step("Address: click edit professional address")
    public void editProfessionalAddress() {
        $("app-professional-page").$("app-location-viewer").scrollIntoView(true).click();
    }

    @Step("Address: click add new address")
    public void clickAddNewAddress() {
        $("app-professional-page").$("app-add-button").$("ion-item").scrollIntoView(true).click();
    }

    @Step("Address: click back")
    public void addressClickBack() {
        $("app-master-location-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //main

    @Step("Main: click edit data")
    public void clickEditMain() {
        $("app-professional-page").$("ion-item[routerlink='edit']").scrollIntoView(true).click();
    }

    @Step("Main: click back")
    public void mainClickBack() {
        $("app-master-edit-page").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Main: click save")
    public void mainClickSave() {
        $("app-master-edit-page").$("app-master-edit").$("ion-button").click();
        sleep(500);
    }

    @Step("Main: edit specialization - value: {value}")
    public void editSpecialization(String value) {
        $("app-master-edit-page").$("app-master-edit").$("input", 0).setValue(value);
    }

    @Step("Main: edit description - value: {value}")
    public void editDescription(String value) {
        $("app-master-edit-page").$("app-master-edit").$("textarea").sendKeys(value);
    }

    @Step("Main: edit company - value: {value}")
    public void editCompany(String value) {
        $("app-master-edit-page").$("app-master-edit").$("input", 1).setValue(value);
    }

    @Step("Main: edit experience - value: {value}")
    public void editExperience(String value) {
        Attach.screenshotAs("Screenshot");
        $("app-master-edit-page").$("app-master-edit").$("input", 2).setValue(value);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Main: edit expertise level - value: {value}")
    public void editExpertiseLevel(String value) {
        sleep(200);
        $("app-master-edit-page").$("app-master-edit").$("ion-select").click();
        sleep(200);
        switch (value) {
            case "random":
                Faker generate = new Faker(new Locale("en-US"));
                Integer level = generate.number().numberBetween(0, 2);
                $("ion-popover").$("ion-select-popover").$("ion-item", level).click();
                break;
            case "junior":
                $("ion-popover").$("ion-select-popover").$("ion-item", 0).click();
                break;
            case "middle":
                $("ion-popover").$("ion-select-popover").$("ion-item", 1).click();
                break;
            case "senior":
                $("ion-popover").$("ion-select-popover").$("ion-item", 2).click();
                break;
            default:
                fail();
                break;
        }
        sleep(500);
    }

    @Step("Main: edit category - value: {value}")
    public void editCategoryByText(String value) {
        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='category']").click();
        sleep(200);
        $("ion-modal").$("ion-content").$(withText(value)).click();
        sleep(500);
    }

    @Step("Main: edit subcategory - value: {value}")
    public void editSubcategoryByText(String value) {
        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='subcategory']").click();
        sleep(200);
        $("ion-modal").$("ion-content").$(withText(value)).click();
        sleep(500);
    }

    @Step("Main: edit category - value: {value}")
    //value 0-8
    public void editCategory(Integer value) {
        sleep(500);
        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='category']").scrollIntoView(true).click();
        sleep(500);
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", value).scrollIntoView(true).click();
        sleep(500);
        Attach.screenshotAs("Screenshot");

    }

    @Step("Main: edit subcategory - value: {value}")
    //value 0-8
    public void editSubcategory(Integer value) {
        sleep(500);
        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='subcategory']").scrollIntoView(true).click();
        sleep(500);
        $("ionic-selectable-modal").$("ion-content").$("ion-item", value).click();
        sleep(500);
        Attach.screenshotAs("Screenshot");

    }

    @Step("Main: verify")
    public void mainVerify(String description, String level, Integer categoryNumber, Integer subcategoryNumber) {
        //can't add yet specialization check
        $("app-master-edit-page").$("app-master-edit").$("textarea").shouldHave(text(description));
        //can't add yet company name check
        //can't add yet experience check
        sleep(200);
        $("app-master-edit-page").$("app-master-edit").$("ion-select").click();
        sleep(200);
        switch (level) {
            case "junior":
                $("ion-popover").$("ion-select-popover").$("ion-item", 0).shouldHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 1).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 2).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").pressEscape();
                break;
            case "middle":
                $("ion-popover").$("ion-select-popover").$("ion-item", 0).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 1).shouldHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 2).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").pressEscape();
                break;
            case "senior":
                $("ion-popover").$("ion-select-popover").$("ion-item", 0).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 1).shouldNotHave(cssClass("item-radio-checked"));
                $("ion-popover").$("ion-select-popover").$("ion-item", 2).shouldHave(cssClass("item-radio-checked"));
                $("ion-popover").pressEscape();
                break;
            default:
                fail();
                break;
        }

        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='category']").click();
        sleep(200);
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", categoryNumber).shouldHave(cssClass("ionic-selectable-item-is-selected"));
        $("ion-modal").pressEscape();
        sleep(500);

        $("app-master-edit-page").$("app-master-edit").$("ionic-selectable[title='subcategory']").click();
        sleep(200);
        $("ionic-selectable-modal").$("ion-content").$("ion-item", subcategoryNumber).shouldHave(cssClass("ionic-selectable-item-is-selected"));
        $("ion-modal").pressEscape();
        sleep(500);
        Attach.screenshotAs("Screenshot");
    }

    //about

    @Step("About: click edit about")
    public void clickEditAbout() {
        $("app-professional-page").$("ion-button[routerlink='/profile/about']").scrollIntoView(true).click();
    }

    @Step("About: click back")
    public void aboutClickBack() {
        $("app-about-edit main ion-button").click();
        $("app-about-edit main ion-button").shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-professional-page").shouldBe(visible, Duration.ofSeconds(10));
        sleep(500);
    }

    //qualification

    @Step("Qualification: click add new qualification")
    public void clickAddNewQualification() {
        $("app-professional-page").$("app-add-button[routerlink='experience-add/']").$("ion-item").scrollIntoView(true).click();
    }

    @Step("Qualification: click edit existing qualification")
    // values 1-99
    public void clickEditQualification(Integer value) {
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).parent().scrollIntoView(true).click();
    }

    @Step("Qualification: click back")
    public void qualificationClickBack() {
        $("app-master-experience-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Qualification: click save")
    public void qualificationClickSave() {
        Attach.screenshotAs("Screenshot");
        if ($("app-master-experience-edit").$("form").$("ion-button", 1).exists()) {
            $("app-master-experience-edit").$("form").$("ion-button", 1).click();
        } else {
            $("app-master-experience-edit").$("form").$("ion-button").click();
        }
        sleep(500);
    }

    @Step("Qualification: click remove")
    public void qualificationClickRemove() {
        sleep(500);
        if ($("app-master-experience-edit").$("form").$("ion-button", 1).exists()) {
            $("app-master-experience-edit").$("form").$("ion-button", 0).click();
        } else {
            fail();
        }
        sleep(500);
    }

    @Step("Qualification: edit job title - value: {value}")
    public void qualificationEditJobTitle(String value) {
        $("app-master-experience-edit").$("app-experience-edit").$("input[name='title']").setValue(value);
    }

    @Step("Qualification: edit company - value: {value}")
    public void qualificationEditCompany(String value) {
        $("app-master-experience-edit").$("app-experience-edit").$("input[name='company']").setValue(value);
    }

    @Step("Qualification: edit 'from' - {month} {year}")
    public void qualificationEditFrom(String month, String year) {
        sleep(200);
        $("app-master-experience-edit").$("app-experience-edit").$("input[type='month']", 0).setValue(monthConvertToNumber(month));
        sleep(200);
        $("app-master-experience-edit").$("app-experience-edit").$("input[type='month']", 0).pressTab().setValue(year);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Qualification: edit 'to' - {month} {year}")
    public void qualificationEditTo(String month, String year) {
        sleep(200);
        $("app-master-experience-edit").$("app-experience-edit").$("input[type='month']", 1).setValue(monthConvertToNumber(month));
        sleep(200);
        $("app-master-experience-edit").$("app-experience-edit").$("input[type='month']", 1).pressTab().setValue(year);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Qualification: ongoing checkbox - checked")
    public void qualificationOngoingYes() {
        if (!$("app-master-experience-edit").$("app-experience-edit").$("ion-item.item-checkbox-checked").exists()) {
            $("app-master-experience-edit").$("app-experience-edit").$("ion-checkbox").parent().click();
        }
    }

    @Step("Qualification: ongoing checkbox - unchecked")
    public void qualificationOngoingNo() {
        if ($("app-master-experience-edit").$("app-experience-edit").$("ion-item.item-checkbox-checked").exists()) {
            $("app-master-experience-edit").$("app-experience-edit").$("ion-checkbox").parent().click();
        }
    }

    @Step("Qualification: edit description - value: {value}")
    public void qualificationEditDescription(String value) {
        $("app-master-experience-edit").$("app-experience-edit").$("input[name='title']", 1).setValue(value);
    }

    @Step("Qualification: verification - empty")
    public void qualificationVerificationEmpty() {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience").shouldNot(exist);
    }

    @Step("Qualification: verification - basic")
    public void qualificationVerificationBasic(Integer value, String jobTitle, String companyName) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).$("div.experience-title").shouldHave(text(jobTitle));
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).shouldHave(text(companyName));
    }

    @Step("Qualification: verification - full")
    public void qualificationVerificationFull(Integer value, String date, String jobTitle, String companyName, String description) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 1).scrollIntoView(true);
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).$("div.experience-date").shouldHave(text(date));
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).$("div.experience-title").shouldHave(text(jobTitle));
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).shouldHave(text(companyName));
        $("app-professional-page").$("app-collapse-item", 1).$("ion-item app-experience", value).shouldHave(text(description));
    }

    //education

    @Step("Education: click add new education")
    public void clickAddNewEducation() {
        $("app-professional-page").$("app-add-button[routerlink='education-add/'] ion-item").scrollIntoView(true).click();
    }

    @Step("Education: click edit existing education")
    // values 1-99
    public void clickEditEducation(Integer value) {
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).parent().scrollIntoView(true).click();
    }

    @Step("Education: click back")
    public void educationClickBack() {
        $("app-master-education-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Education: click save")
    public void educationClickSave() {
        Attach.screenshotAs("Screenshot");
        if ($("app-master-education-edit").$("form").$("ion-button", 1).exists()) {
            $("app-master-education-edit").$("form").$("ion-button", 1).click();
        } else {
            $("app-master-education-edit").$("form").$("ion-button").click();
        }
        sleep(500);
    }

    @Step("Education: click remove")
    public void educationClickRemove() {
        sleep(500);
        if ($("app-master-education-edit").$("form").$("ion-button", 1).exists()) {
            $("app-master-education-edit").$("form").$("ion-button", 0).click();
        } else {
            fail();
        }
        sleep(500);
    }

    @Step("Education: edit university - value: {value}")
    public void educationUniversity(String value) {
        $("app-master-education-edit").$("app-education-edit").$("input", 0).setValue(value);
    }

    @Step("Education: edit degree - value: {value}")
    public void educationDegree(String value) {
        $("app-master-education-edit").$("app-education-edit").$("input", 1).setValue(value);
    }

    @Step("Education: edit academic field - value: {value}")
    public void educationAcademicField(String value) {
        $("app-master-education-edit").$("app-education-edit").$("input", 2).setValue(value);
    }

    @Step("Education: edit 'from' - {month} {year}")
    public void educationEditFrom(String month, String year) {
        sleep(200);
        $("app-master-education-edit").$("app-education-edit").$("input[type='month']", 0).setValue(monthConvertToNumber(month));
        sleep(200);
        $("app-master-education-edit").$("app-education-edit").$("input[type='month']", 0).pressTab().setValue(year);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Education: edit 'to' - {month} {year}")
    public void educationEditTo(String month, String year) {
        sleep(200);
        $("app-master-education-edit").$("app-education-edit").$("input[type='month']", 1).setValue(monthConvertToNumber(month));
        sleep(200);
        $("app-master-education-edit").$("app-education-edit").$("input[type='month']", 1).pressTab().setValue(year);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Education: ongoing checkbox - checked")
    public void educationOngoingYes() {
        if (!$("app-master-education-edit").$("app-education-edit").$("ion-item.item-checkbox-checked").exists()) {
            $("app-master-education-edit").$("app-education-edit").$("ion-checkbox").parent().click();
        }
    }

    @Step("Education: ongoing checkbox - unchecked")
    public void educationOngoingNo() {
        if ($("app-master-education-edit").$("app-education-edit").$("ion-item.item-checkbox-checked").exists()) {
            $("app-master-education-edit").$("app-education-edit").$("ion-checkbox").parent().click();
        }
    }

    @Step("Education: edit description - value: {value}")
    public void educationEditDescription(String value) {
        $("app-master-education-edit").$("app-education-edit").$("input", 6).setValue(value);
    }

    @Step("Education: verification - empty")
    public void educationVerificationEmpty() {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education").shouldNot(exist);
    }

    @Step("Education: verification - basic")
    public void educationVerificationBasic(Integer value, String university) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).$("div.university").shouldHave(text(university));
    }

    @Step("Education: verification - full")
    public void educationVerificationFull(Integer value, String date, String university, String degree, String description) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 2).scrollIntoView(true);
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).$("div.education-date").shouldHave(text(date));
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).$("div.university").shouldHave(text(university));
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).shouldHave(text(degree));
        $("app-professional-page").$("app-collapse-item", 2).$("ion-item app-education", value).shouldHave(text(description));
    }

    //certificate

    @Step("Certificates: click add new certificate")
    public void clickAddNewCertificate() {
        $("app-professional-page").$("app-add-button[routerlink='certificate-add/'] ion-item").scrollIntoView(true).click();
    }

    @Step("Certificates: click edit existing certificate")
    // values 1-99
    public void clickEditCertificate(Integer value) {
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).parent().scrollIntoView(true).click();
    }

    @Step("Certificates: click back")
    public void certificatesClickBack() {
        $("app-master-certificate-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    @Step("Certificates: click save")
    public void certificatesClickSave() {
        Attach.screenshotAs("Screenshot");
        sleep(500);
        Attach.screenshotAs("Screenshot");
        if ($("app-master-certificate-edit").$("app-certificate-edit").$("ion-button", 3).exists()) {
            $("app-master-certificate-edit").$("app-certificate-edit").$("ion-button", 3).click();
        } else {
            $("app-master-certificate-edit").$("app-certificate-edit").$("ion-button", 2).click();
        }
        Attach.screenshotAs("Screenshot");
        sleep(500);
        Attach.screenshotAs("Screenshot");
    }

    @Step("Certificates: click remove")
    public void certificatesClickRemove() {
        sleep(500);
        if ($("app-master-certificate-edit").$("app-certificate-edit").$("ion-button", 3).exists()) {
            $("app-master-certificate-edit").$("app-certificate-edit").$("ion-button", 2).click();
        } else {
            fail();
        }
        sleep(500);
    }

    @Step("Certificates: edit name - value: {value}")
    public void certificatesEditName(String value) {
        $("app-master-certificate-edit").$("app-certificate-edit").$("input[name='name']").setValue(value);
    }

    @Step("Certificates: edit organization - value: {value}")
    public void certificatesEditOrganization(String value) {
        $("app-master-certificate-edit").$("app-certificate-edit").$("input[name='organization']").setValue(value);
    }

    @Step("Certificates: edit date - value: {day} {month} {year}")
    public void certificatesEditDate(String day, String month, String year) {
        $("app-certificate-edit input[type='date']").sendKeys("22113333");
        String value = $("app-certificate-edit input[type='date']").getValue();
        value = value.substring(0, 1);
        if (value.equals("1")) {
            $("app-master-certificate-edit").$("app-certificate-edit").$("input[type='date']").setValue(monthConvertToNumber(month) + day + year);
        } else if (value.equals("3")) {
            $("app-master-certificate-edit").$("app-certificate-edit").$("input[type='date']").setValue(day + monthConvertToNumber(month) + year);
        } else {
            fail();
        }
    }

    @Step("Certificates: edit ID - value: {value}")
    public void certificatesEditID(String value) {
        $("app-master-certificate-edit").$("app-certificate-edit").$("input[name='certificate_id']").setValue(value);
    }

    @Step("Certificates: edit link - value: {value}")
    public void certificatesEditLink(String value) {
        $("app-master-certificate-edit").$("app-certificate-edit").$("input[name='url']").setValue(value);
    }

    @Step("Certificates: upload picture")
    public void certificatesUploadPic(String value) {
        sleep(500);
        $("app-master-certificate-edit").$("app-certificate-edit").$("input[type='file']").uploadFile(new File(value));
        sleep(1000);
    }

    @Step("Certificates: picture - click select")
    public void certificatesPictureSelect() {
        $("app-master-certificate-edit").$("app-picture-selector").$("#file-button").click();
    }

    @Step("Certificates: picture - click delete")
    public void certificatesPictureDelete() {
        $("app-master-certificate-edit").$("app-picture-selector").$("ion-button", 1).click();
    }

    @Step("Certificates: verification - empty")
    public void certificatesVerificationEmpty() {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate").shouldNot(exist);
    }

    @Step("Certificates: verification - basic")
    public void certificatesVerificationBasic(Integer value, String name) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).$("ion-label.title").shouldHave(text(name));
    }

    @Step("Certificates: verification - full")
    public void certificatesVerificationFull(Integer value, String name, String date, String id, String link) {
        sleep(500);
        $("app-professional-page").$("app-collapse-item", 3).scrollIntoView(true);
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).$("ion-label.title").shouldHave(text(name));
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).shouldHave(text(date));
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).shouldHave(text(id));
        $("app-professional-page").$("app-collapse-item", 3).$("ion-item app-certificate", value).shouldHave(text(link));
    }

    //reviews

    @Step("Reviews: click 'all reviews'")
    public void clickAllReviews() {
        $("app-professional-page").$("app-review-cards").parent().$("ion-item a").scrollIntoView(true).click();
    }
}