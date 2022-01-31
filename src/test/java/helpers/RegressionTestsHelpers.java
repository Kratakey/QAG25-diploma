package helpers;

import api.Registration;

import static api.Registration.locations;
import static api.Registration.registration;
import static api.ServicePublish.*;
import static helpers.DateTimeFormatter.getDateTime;

public class RegressionTestsHelpers extends config.TestBase {

    public static void userRegisterUI() {
        log.forceMainPage();
        log.popupSelect(userCountry, userCity);
        log.forceEN();
        sideMenu.clickSignUp();
        reg.fillUserFirstName(userFirstName);
        reg.fillEmail(userEmailRandom);
        reg.choosePassword(userPasswordRandom);
        reg.selectCountry(userCountry);
        reg.selectCity(userCity);
        reg.confirmAndWait();
    }

    public static void serviceRegisterUI() {
        log.forceMainPage();
        log.forceEN();
        sideMenu.clickPublishNewService();

        pbl.chooseCategory(randomServiceCategory);
        pbl.chooseSubcategory(randomServiceSubcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(serviceNameRandom);
        pbl.enterServiceDescription(serviceDescriptionRandom);
        pbl.setDuration(serviceDurationRandom);
        pbl.setPriceFixed(servicePriceRandom, randomCurrency);
        pbl.selectServiceLocation(online);
        pbl.clickSecondStep();

        pbl.clickThirdStep();

        pbl.fillSpecialization(serviceSpecializationRandom);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.instantBooking(on);
        pbl.PaymentByCash(on);
        pbl.OnlinePayment(on);
        pbl.clickSeventhStep();

        pbl.publishService();
    }

    public static String userRegisterAPI() {
        String accessToken = registration(userFirstName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        return accessToken;
    }

    public static void serviceRegisterAPI(String accessToken) {
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, randomServiceCategory, randomServiceSubcategory, serviceSpecializationRandom);
        int serviceId = servicePublish(accessToken, professionalId, serviceNameRandom, serviceDescriptionRandom, serviceDurationRandom, online, instantBooking);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePriceRandom, serviceCurrencyRandom, paymentCashOnline);
    }

    public static void userReadyAPI() {
        String accessToken = registration(userFirstName, userEmailRandom, userPasswordRandom);
        int locationsId = locations(accessToken, userCountry, userCity);
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();
    }

    public static String clientRegisterAPI() {
        String accessToken = registration(clientFirstName, clientEmailRandom, clientPasswordRandom);
        int locationsId = locations(accessToken, clientCountry, clientCity);
        return accessToken;
    }

    public static void clientReadyAPI() {
        String accessToken = registration(clientFirstName, clientEmailRandom, clientPasswordRandom);
        int locationsId = locations(accessToken, clientCountry, clientCity);
        log.openMainPage();
        log.popupSkip();
        log.logIn(clientEmailRandom, clientPasswordRandom);
        log.forceEN();
    }

    public static void serviceReadyAPI() {
        serviceRegisterAPI(userRegisterAPI());
        log.openMainPage();
        log.popupSkip();
        log.logIn(userEmailRandom, userPasswordRandom);
        log.forceEN();
    }
}