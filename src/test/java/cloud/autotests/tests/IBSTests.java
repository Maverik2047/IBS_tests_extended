package cloud.autotests.tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;


public class IBSTests extends TestBase {
    @Test
    @Tag("ibsTest")
    @Owner("Maverik2047")
    @Severity(SeverityLevel.NORMAL)
    @Description("Check specified content on IBS web-site")
    @DisplayName("IBS-tests")
    void ibsTest() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));

        step("click on Accept cookies button", () -> {
            $(".cookie.cookie__show").$(byText("Принимаю условия")).click();
        });

        step("click on ENG button", () -> {
            $(".header__lang.lang").$(byText("ENG")).click();
        });

        step("click on Technology Partners", () -> {
            $(".aside-navigation__list").$(byText("Technology Partners")).click();
        });

        step("click on SHOW", () -> {
            $("#ajax_load").click();
        });

        step("page should have modal ROBIN Partner", () -> {
            $(".vendors-project-list.ajax_result").shouldHave(Condition.text("ROBIN"));
        });
    }

    @Test
    @Description("IBS test")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));

        step("Page title should have text 'IBS — ведущая российская IT-сервисная компания'", () -> {
            String expectedTitle = "IBS — ведущая российская IT-сервисная компания";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }
    @Test
    @Description("Contacts check")
    @DisplayName("IBS Contacts page")
    void contactsCheck() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));
        step("Click Contacts button",()->{
            $(".header__btn").click();
        });
        step("Open main Office address",()->{
            $(".view-address-block__title").click();
        });
        step("The main office should have full address",()->{
            $(".view-address-block__content").shouldHave(Condition.text("Россия, " +
                    "127434, Москва, Дмитровское шоссе, 9Б, этаж 5, пом. XIII, ком. 6"));
        });
    }
}

