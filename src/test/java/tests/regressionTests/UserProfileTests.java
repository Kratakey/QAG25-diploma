package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;

public class UserProfileTests extends config.TestBase {
    @Test
    @Feature("Profile")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4844")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Profile: delete address that was default")
    void t00000() {
        String address1 = "Street 1";
        String address2 = "Street 2";
        userReadyAPI();
        sideMenu.clickProfile();

        up.clickAddNewAddress();
        up.addressSelectCountry(userCountry);
        up.addressSelectCity(userCity);
        up.addressSelectAddress(address1);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.clickAddNewAddress();
        up.addressSelectCountry(userCountry);
        up.addressSelectCity(userCity);
        up.addressSelectAddress(address2);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.selectAddress(1);
        up.addressClickRemove();
        up.verifyAddressRemoved(userCountry, userCity, address1);

        up.selectAddress(1);
        up.addressClickMakeDefault();
        up.addressClickSave();

        up.selectAddress(1);
        up.addressClickRemove();
        up.verifyAddressRemoved(userCountry, userCity, address1);
        up.verifyAddressRemoved(userCountry, userCity, address2);
    }
}