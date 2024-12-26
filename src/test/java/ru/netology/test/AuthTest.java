package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.mode.DBUtils;
import ru.netology.mode.DataHelper;
import ru.netology.mode.User;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {
    User validUser = DataHelper.getValidUser();
    User invalidUser = DataHelper.getInvalidUser();

    @AfterAll
    static void cleanDatabase() {
        DBUtils.clearDatabase();
    }

    @Test
    void loginWithValidData() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(validUser);
        DashboardPage dashboardPage = verificationPage.validVerify(DBUtils.getVerificationCode());
    }

    @Test
    void loginWithInvalidData() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithInvalidData(invalidUser);
        loginPage.shouldShowBlockedUserNotification();
    }
}
