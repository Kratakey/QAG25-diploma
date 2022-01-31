package tests.smokeTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.String.valueOf;

public class PositiveTests extends config.TestBase {

    @Test
    @Feature("User Registration")
    @Owner("Kratakey")
    @Story("Full registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Positive User Registration - in order")
    void t00000() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(firstNames[0]);
        reg.fillUserLastName(lastNames[0]);
        reg.fillEmail(emails[0]);
        reg.choosePassword(passwords[0]);
        reg.fillPhoneNumber(phoneNumbers[0], countries[0]);
        reg.selectCountry(countries[0]);
        reg.selectCity(cities[0]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToSearch();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataFull(firstNames[0], lastNames[0], emails[0], phoneNumbers[0], countries[0], cities[0]);
    }

    @Test
    @Feature("User Registration")
    @Owner("Kratakey")
    @Story("Minimal registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic Positive User Registration")
    void t00001() {
        log.openMainPage();
        log.popupSelect(countries[1], cities[1]);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(firstNames[1]);
        reg.fillEmail(emails[1]);
        reg.choosePassword(passwords[1]);
        reg.selectCountry(countries[1]);
        reg.selectCity(cities[1]);
        reg.confirmAndWait();
        reg.completeTutorSlidesToPublish();
        log.forceEN();
        sideMenu.clickProfile();
        reg.verifyRegistrationDataBasic(firstNames[1], emails[1], countries[1], cities[1]);
    }

    @Test
    @Feature("Service Publish")
    @Owner("Kratakey")
    @Story("Minimal service publication")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic positive service publishing, service location: Online")
    void t00100() {
        log.openMainPage();
        log.popupSkip();
        log.account(0);
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNames[0]);
        pbl.enterServiceDescription(serviceDescriptions[0]);
        pbl.setDuration(serviceDurations[0]);
        pbl.setPriceFixed(servicePrices[0], randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(specializations[0]);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.PaymentByCash(on);
        pbl.OnlinePayment(on);
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(serviceNames[0], serviceDurations[0], serviceDescriptions[0]);
        pbl.checkPrice(servicePrices[0]);
        pbl.publishService();
    }
    @Test
    @Feature("Booking")
    @Owner("Kratakey")
    @Story("Service booking")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Basic booking online")
    void t00200() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        sideMenu.clickSearch();

        search.closeAllChips();
        bkn.findService(serviceNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
        bkn.verifyServiceLocation("Online");
        bkn.verifyServicePaymentCash();
        bkn.verifyServicePaymentOnline();
        bkn.verifyInstantBooking(on);
        bkn.clickOrder();
        bkn.pickTheDate(today);
        bkn.clickNextDay();
        bkn.bookTime(1100);
        bkn.acceptTimeSelection();
        bkn.acceptConfirmation();
        bkn.placeOrder();
        bkn.showOrderDetails();
        bkn.verifyOrderDetails(serviceNames[0]);
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
    }

    @Test
    @Feature("Orders")
    @Owner("Kratakey")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Check orders from user side")
    void t00300() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        topBar.clickMyOrders();
        ord.checkOrderOutbox(firstNames[0], serviceNames[0], servicePrices[0], serviceDurations[0]);
    }

    @Test
    @Feature("Orders")
    @Owner("Kratakey")
    @Story("Order check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Master completes order: online")
    void t00301() {
        log.openMainPage();
        log.popupSkip();
        log.account(0);
        log.forceEN();
        topBar.clickMyOrders();
        ord.tabCurrentOrdersInbox();
        ord.checkOrderInbox(firstNames[1], servicePrices[0], serviceDurations[0]);
        ord.completeOrder();
    }

    @Test
    @Feature("Reviews")
    @Owner("Kratakey")
    @Story("Review check")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Post and check review add to favorites in the process")
    void t00402() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        sideMenu.clickSentOrders();
        rev.tabArchivedOrdersOutbox();
        rev.choseMaster(1);
        rev.clickReviewTab();
        rev.clickSendReviewLink();
        rev.addToFavorite();
        rev.choseRating(randomRating);
        String rating = valueOf(randomRating);
        rev.sendReviewText(reviewText1 + " " + reviewText2);
        rev.pressSend();
        rev.verifyReview(firstNames[0] + " " + lastNames[0], firstNames[1], reviewText1 + " " + reviewText2);

        rev.clickMenuMain();
        rev.openBookmarksMenu();
        rev.verifyBookmark(firstNames[0] + " " + lastNames[0], rating);
    }

    @Test
    @Feature("Reviews")
    @Owner("Kratakey")
    @Story("Review master answers")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Review answer")
    void t00403() {
        log.openMainPage();
        log.popupSkip();
        log.account(0);
        log.forceEN();
        sideMenu.clickProfessionalProfile();
        rev.clickMasterReviews();
        rev.postMasterComment(masterComment);
        rev.verifyMasterComment(masterComment);
    }

    @Test
    @Feature("Messages")
    @Owner("Kratakey")
    @Story("Chatting tests")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Chat test")
    void t00500() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        sideMenu.clickSentOrders();
        ord.tabArchivedOrdersOutbox();
        ord.clickProfessionalsName();
        pp.clickChat();
        msg.sendMessage(testMessage1);
        msg.checkMessage(testMessage1);
    }

    @Test
    @Feature("Favorites")
    @Owner("Kratakey")
    @Story("Remove master from favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Remove master from favorites")
    void t00600() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();

        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(firstNames[0] + " " + lastNames[0]);

        fav.removeBookmark();
        fav.verifyDelBookmark(firstNames[0] + " " + lastNames[0]);
    }

    @Test
    @Feature("Favorites")
    @Owner("Kratakey")
    @Story("Add master to favorites")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Add master to favorite from search results")
    void t00601() {
        log.openMainPage();
        log.popupSkip();
        log.account(1);
        log.forceEN();
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNames[0]);
        fav.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        fav.clickFavSearch();
        sideMenu.clickBookmarks();
        fav.verifyBookmarkOnline(firstNames[0] + " " + lastNames[0]);
    }

    @Test
    @Feature("Search")
    @Owner("Kratakey")
    @Story("Search from the main page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Main page search: professional name")
    void t00700() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();

        search.searchMainNoQuotes(firstNames[0] + " " + lastNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
    }

    @Test
    @Feature("Search")
    @Owner("Kratakey")
    @Story("Search from the search page")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Search page search: professional name")
    void t00701() {
        log.openMainPage();
        log.popupSkip();
        log.forceEN();
        sideMenu.clickSearch();

        search.searchNoQuotes(firstNames[0] + " " + lastNames[0]);
        bkn.verifyServiceSearch(firstNames[0], lastNames[0], serviceNames[0], servicePrices[0]);
        bkn.chooseService();
        bkn.verifyServiceBase(serviceNames[0], servicePrices[0], serviceDurations[0], firstNames[0], lastNames[0], serviceDescriptions[0]);
    }
}