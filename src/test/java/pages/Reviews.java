package pages;

import helpers.Attach;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class Reviews {
    @Step("Select current orders")
    public void tabCurrentOrdersOutbox() {
        $("app-outbox-page").$("ion-segment-button", 0).click();
    }

    @Step("Select archived orders")
    public void tabArchivedOrdersOutbox() {
        $("app-outbox-page").$("ion-segment-button", 1).click();
    }

    @Step("Chose master {order}")
    public void choseMaster(int order) {
        if (order < 1) {
            fail();
        }
        order--;
        Attach.screenshotAs("Screenshot");
        $("app-outbox").$("app-professional-card", order).$("a").click();
    }

    @Step("Open reviews tab")
    public void clickReviewTab() {
        $("app-professional-page a.review-count").click();
    }

    @Step("Click send review link")
    public void clickSendReviewLink() {
        $("app-reviews-list ion-item.edit-review a").click();
    }

    @Step("Click review/rating button")
    public void clickReviews() {
        $("app-outbox-page").$("app-rating").$("a").click();
    }

    @Step("Click send review button")
    public void clickSendReviewButton() {
        $("app-edit-review ion-row ion-col ion-button",1).click();
    }

    @Step("Choose rating {value} star(s)")
    public void choseRating(int value) {
        if (value < 1 || value > 5) {
            fail();
        }
        value--;
        sleep(200);
        $("app-edit-review app-rating-picker ion-item", value).click();
        sleep(200);
    }

    @Step("Type review text")
    public void sendReviewText(String reviewText) {
        $("app-edit-review textarea").sendKeys(reviewText);
        sleep(200);
    }

    @Step("Add to favorite")
    public void addToFavorite() {
        sleep(200);
        $("app-edit-review").$("app-saved-professional-toggle").$("ion-button").click();
        sleep(200);
    }

    @Step("Send review")
    public void pressSend() {
        $("app-edit-review ion-row ion-col ion-button",1).shouldNotHave(cssClass("button-disabled"), Duration.ofSeconds(10));
        $("app-edit-review ion-row ion-col ion-button",1).scrollIntoView(true).click();
        $("app-reviews-list app-professional-card").shouldBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify review")
    public void verifyReview(String masterName, String userName, String reviewText) {
        $("app-reviews-list").$("app-professional-card").shouldHave(text(masterName));
        $("app-reviews-list").$("app-review-card").shouldHave(text(userName));
        $("app-reviews-list").$("app-review-card").shouldHave(text(reviewText));
    }

    @Step("Open side menu")
    public void clickMenuMain() {
        sleep(300);
        $("app-reviews-list").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Open bookmarks from the menu")
    public void openBookmarksMenu() {
        $("app-main-menu").$(byText("Bookmarks")).click();
    }

    @Step("Verify bookmarks")
    public void verifyBookmark(String masterName, String rating) {
        $("app-bookmarks-list-page").$("app-professional-card").shouldHave(text(masterName));
        $("app-bookmarks-list-page").$("app-rating").shouldHave(text(rating));
    }

    @Step("Click menu button")
    public void clickMenuProfile() {
        sleep(300);
        $("app-profile").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click menu button")
    public void clickMenu() {
        sleep(300);
        $("app-main").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Open master's profile")
    public void openMasterProfile() {
        $("app-main-menu").$(byText("Professional profile")).click();
    }

    @Step("Click master's reviews")
    public void clickMasterReviews() {
        $("app-professional-page").$(".review-count").click();
    }

    @Step("Post master's comment")
    public void postMasterComment(String masterComment) {
        $("app-reviews-list").$("app-review-card").$("ion-button").scrollIntoView(true);
        $("app-reviews-list").$("app-review-card").$("ion-button").click();
        sleep(500);
        $("app-edit-review-comment").$("textarea").scrollIntoView(true);
        $("app-edit-review-comment").$("textarea").sendKeys(masterComment);
        sleep(500);
        $("#send-btn").scrollIntoView(true);
        $("#send-btn").click();
        sleep(2000);
    }

    @Step("Verify master's comment")
    public void verifyMasterComment(String masterComment) {
        $("app-reviews-list").$("app-review-card").shouldHave(text(masterComment));
    }
}