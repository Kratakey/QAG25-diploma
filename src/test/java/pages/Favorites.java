package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.fail;

public class Favorites {

    @Step("Click side menu button")
    public void clickMenuServiceInfo() {
        sleep(300);
        $("app-service-viewer-page").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Verify bookmarks")
    public void verifyBookmark(String masterName, String masterCity) {
        $("app-bookmarks-list-page").$("main").shouldHave(
                text(masterName),
                text(masterCity)
        );
    }

    @Step("Verify online bookmarks")
    public void verifyBookmarkOnline(String masterName) {
        $("app-bookmarks-list-page").$("main").shouldHave(
                text(masterName)
        );
    }

    @Step("Verify bookmark deletion")
    public void verifyDelBookmark(String masterName) {
        $("app-bookmarks-list-page").$("main").shouldNotHave(text(masterName));
    }

    @Step("Remove bookmark")
    public void removeBookmark() {
        $("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Remove first bookmark")
    public void removeBookmarkIndex(int value) {
        int index = value - 1;
        $("app-professional-card", index).$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Verify that the search result is correct")
    public void verifyServiceSearch(
            String firstName,
            String lastName,
            String serviceName,
            String servicePrice) {
        $("app-search-result").$("ion-card-content").$("app-professional-card")
                .shouldHave(text(firstName), text(lastName));
        $("app-search-result").$("ion-card-content").$("app-service-link").shouldHave(text(serviceName));
        String servicePriceActual = $("app-search-result").$("ion-card-content").$("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            fail();
        }
    }

    @Step("Click booking button from the search results")
    public void clickFavSearch() {
        $("app-search").$("app-search-result").$("app-professional-card").$("ion-button").click();
    }

    @Step("Click on master's name in the search results")
    public void selectMasterSearch() {
        $("app-search").$("app-search-result").$("app-professional-card").$("a").click();
    }

    @Step("Click on service name in the search results")
    public void selectServiceSearch() {
        $("app-search").$("app-search-result").$("app-service-link").$("a").click();
    }

    @Step("Add master to favorites from master's profile page")
    public void clickFavMasterProfile() {
        $("app-professional-page").$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Add master to favorites from service info page")
    public void clickFavServiceInfo() {
        $("app-service-viewer-page").$("app-saved-professional-toggle").$("ion-button").click();
    }
}