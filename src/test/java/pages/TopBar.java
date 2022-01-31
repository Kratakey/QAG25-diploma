package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.fail;

public class TopBar {
    @Step("Top Bar: click chat")
    public void clickChat() {
        sleep(300);
        $$("[routerlink='/message']").filter(visible).get(0).click();
        sleep(300);
    }

    @Step("Top Bar: click My Orders")
    public void clickMyOrders() {
        sleep(300);
        $$("ion-item.my-orders").filter(visible).get(0).click();
        sleep(200);
    }
}