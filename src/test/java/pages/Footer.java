package pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.fail;

public class Footer extends config.TestBase  {
    @Step("Open '/for-performers' page")
    public void clickForPerformersPage() {
        $$("div.footer__links__navbar span").filter(exist).get(1).scrollIntoView(true).click();
    }

    @Step("Verify that '/for-performers' page opened")
    public void verifyForPerformers() {
        String currentUrl = WebDriverRunner.url();
        if (!currentUrl.equals(urlForPerformers)) {
            System.out.println("Wrong URL, expected: " + urlForPerformers + "/n actual: " + currentUrl);
            fail();
        }
    }
}
