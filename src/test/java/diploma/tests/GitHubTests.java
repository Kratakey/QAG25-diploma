package diploma.tests;

import diploma.helpers.DriverUtils;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("General tests")
public class GitHubTests extends TestBase {
    @Test
    @AllureId("6018")
    @Description("Open github.com and verify the title")
    @DisplayName("Main page title should be correct")
    void titleMainPageCheckTest() {
        step("open " + baseUrl, () -> {
            open(baseUrl);
        });

        step("check the title is 'GitHub: Where the world builds software · GitHub'", () -> {
            String expectedTitle = "GitHub: Where the world builds software · GitHub";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @AllureId("6017")
    @Description("Checking the console logs for severe errors")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("open " + baseUrl, () -> {
            open(baseUrl);
        });

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @AllureId("6035")
    @Description("Click 'Sign in' button")
    @DisplayName("Sign up should be clickable")
    void signInTest() {
        step("open " + baseUrl, () -> {
            open(baseUrl);
        });

        step("click 'Sign in'", () -> {
            $("header").$(byText("Sign in")).click();
        });

        step("verify that the register page is displayed", () -> {
            $("div[id='login']").shouldBe(visible);
        });
    }

    @Test
    @AllureId("6037")
    @Description("Check that search is working")
    @DisplayName("Selenide is searchable")
    void searchSelenideTest() {
        String value = "selenide";

        step("open " + baseUrl, () -> {
            open(baseUrl);
        });

        step("search '" + value + "'", () -> {
            $("input[name='q']").setValue(value).pressEnter();
        });

        step("Click on services", () -> {
            $("ul.repo-list li").shouldHave(text(value + "/" + value));
        });
    }

    @Test
    @AllureId("6036")
    @Description("Check that you can read terms of service")
    @DisplayName("GitHub Terms of Service clickable")
    void termsOfServiceTest() {
        step("open " + baseUrl, () -> {
            open(baseUrl);
        });

        step("click 'Sign in'", () -> {
            $("header").$(byText("Sign in")).click();
        });

        step("click 'Terms'", () -> {
            $("div.footer").$("a").click();
        });

        step("verify that the terms page is displayed", () -> {
            $("main").shouldHave(text("GitHub Terms of Service"));
        });
    }
}