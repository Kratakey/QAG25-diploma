package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static helpers.RegressionTestsHelpers.*;

public class BookingTests extends config.TestBase {
    @Test
    @Feature("Booking")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4857")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Booking: today's date")
    void t00000() {
        serviceReadyAPI();
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNameRandom);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNameRandom);
    }

    @Test
    @Feature("Booking")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4764")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Booking: your own service for another person")
    void t00001() {
        serviceReadyAPI();
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNameRandom);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.fillOrderForAPName(userFirstName);
        bkn.fillOrderForAPSurname(userFirstName);
        bkn.fillOrderForAPEmail(userEmailRandom);
        bkn.fillOrderForAPPhoneNumber(userCountry, userPhoneNumber);
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNameRandom);
    }

    @Test
    @Feature("Booking")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4764")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Booking: a service for another person")
    void t00002() {
        serviceReadyAPI();

        String service = serviceNameRandom;

        log.forceLogOut();
        closeWindow();
        setRandomData();
        log.forceMainPage();

        userRegisterUI();
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(service);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.clickOrderForAnotherPerson();
        bkn.fillOrderForAPName(userFirstName);
        bkn.fillOrderForAPSurname(userFirstName);
        bkn.fillOrderForAPEmail(userEmailRandom);
        bkn.fillOrderForAPPhoneNumber(userCountry, userPhoneNumber);
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(service);
    }

    @Test
    @Feature("Booking")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4929")
    @Severity(SeverityLevel.TRIVIAL)
    @DisplayName("Booking: time step, can't navigate back in time via arrows")
    void t00100() {
        serviceReadyAPI();
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNameRandom);
        bkn.chooseService();
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.verifyPreviousDayInactive();
        bkn.clickNextDay();
        bkn.verifyPreviousDayActive();
        bkn.clickPreviousDay();
        bkn.verifyPreviousDayInactive();
    }
}