package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class Messages {

    @Step("Open the chat from Order details menu")
    public void openChatFromOrderDetails() {
        $("app-sent-order-page").$("app-chat-button").$("ion-button").click();
    }

    @Step("Find the messenger")
    public void findUserChat(String userName) {
        $("app-chats-list-page").$("ion-searchbar").$("input").sendKeys(userName);
    }

    @Step("Select user")
    public void selectUser() {
        $("app-chats-list-page").$("ion-list").$("ion-item").click();
    }

    @Step("Send the message")
    public void sendMessage(String textMessage) {
        $("app-chat-page").$("form").$("input").sendKeys(textMessage);
        $("app-chat-page").$("form").$("ion-button").click();
    }

    @Step("Verify the message")
    public void checkMessage(String textMessage) {
        $("app-chat-page").$("ion-content").shouldHave(text(textMessage));
    }
}