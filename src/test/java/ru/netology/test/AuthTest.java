package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.netology.mode.DBUtils;
import ru.netology.mode.User;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthTest {
    User validUser = new User("vasya", "qwerty123");
    User invalidUser = new User("petya", "qwerty123");

    @Test
    void loginWithValidData() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(validUser);
        DashboardPage dashboardPage = verificationPage.validVerify(DBUtils.getVerificationCode());
        assertEquals("Личный кабинет", dashboardPage.getHeading());
    }
    
    @Test
    void loginWithInvalidData() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        $("[data-test-id=action-login]").click();
        $("[data-test-id=action-login]").click();
        $("[data-test-id=action-login]").click();
        $(By.className("notification__content")).shouldHave(text("Пользователь заблокирован"));
    }
}