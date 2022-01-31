package helpers;

import java.time.Duration;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class SelectableModal {
    public static void selectModal (String value) {
        $("ionic-selectable-modal input").shouldBe(empty, Duration.ofSeconds(10));
        sleep(500);
        $("ionic-selectable-modal input").sendKeys(value);
        $("ionic-selectable-modal ion-item", 1).shouldNotBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable-modal ion-item", 0).shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable-modal ion-item", 0).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }
}
