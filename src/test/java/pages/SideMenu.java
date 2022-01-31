package pages;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class SideMenu {
    public void openMenu() {
        sleep(300);
        $$("[menu='main-menu']").filter(visible).get(0).click();
        sleep(300);
    }

    @Step("Click Menu > Log in")
    public void clickLogIn() {
        openMenu();
        $("[id='main-menu.log-in']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Log out")
    public void clickLogOut() {
        openMenu();
        $("[id='main-menu.log-out']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Profile")
    public void clickProfile() {
        openMenu();
        $("[id='main-menu.my-account']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Sing up")
    public void clickSignUp() {
        openMenu();
        $("[id='main-menu.sign-up']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Professional profile")
    public void clickProfessionalProfile() {
        openMenu();
        $("[id='main-menu.professional-profile']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Search")
    public void clickSearch() {
        openMenu();
        $("[id='main-menu.search']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Bookmarks")
    public void clickBookmarks() {
        openMenu();
        $("[id='main-menu.bookmarks']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Sent orders (Outbox)")
    public void clickSentOrders() {
        openMenu();
        $("[id='main-menu.sent-orders']").scrollIntoView(true).click();
        sleep(300);
    }

    @Step("Click Menu > Publish new service")
    public void clickPublishNewService() {
        openMenu();
        $("[id='main-menu.become-professional']").scrollIntoView(true).click();
        sleep(300);
    }
}