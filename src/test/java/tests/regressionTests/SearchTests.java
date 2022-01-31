package tests.regressionTests;

import config.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.*;

public class SearchTests extends TestBase {
    @Test
    @Feature("Search")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4932")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("'Best works' will not show if professional doesn't have any")
    void t00000() {
        //setup
        serviceReadyAPI();

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNameRandom);
        search.verifyBestWorksNotExists();
    }

    @Test
    @Feature("Search")
    @Owner("Kratakey")
    @Story("https://redmine.maxi-booking.ru/issues/4932")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("'Best works' will show if filled")
    void t00001() {
        //setup
        serviceReadyAPI();
        pp.openMyProfessionalProfileLink();
        pp.uploadBestWorks(randomFile);

        //search and check
        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceNameRandom);
        search.verifyBestWorksExists();
    }
}