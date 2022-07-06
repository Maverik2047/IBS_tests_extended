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
    @DisplayName("IBS content test")
    void test01() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));
        step("click on ENG button", () -> {
            $(".header__lang.lang").$(byText("ENG")).click();
        });
        step("click on Accept cookies button", () -> {
            $(".cookie.cookie__show").$(byText("Принимаю условия")).click();
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
    @Severity(SeverityLevel.CRITICAL)
    @Description("IBS test")
    @DisplayName("Main page title should have header text")
    void test02() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));

        step("Page title should have text 'IBS — ведущая российская IT-сервисная компания'", () -> {
            String expectedTitle = "IBS — ведущая российская IT-сервисная компания";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Contacts check")
    @DisplayName("Open Contacts page")
    void test03() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));
        step("Click Contacts button", () -> {
            $(".header__btn").click();
        });
        step("Open main Office address", () -> {
            $(".view-address-block__title").click();
        });
        step("The main office should have full address", () -> {
            $(".view-address-block__content").shouldHave(Condition.text("Россия, " +
                    "127434, Москва, Дмитровское шоссе, 9Б, этаж 5, пом. XIII, ком. 6"));
        });
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Description("IBS jobs check")
    @DisplayName("Search for qa automation job")
    void test04() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));
        step("Type desired position into search", () -> {
            $("[name=q]").setValue("Инженер по автоматизации тестирования Java").pressEnter();
        });
        step("Open qa automation position", () -> {
            $(".search-result__title").click();
        });
        step("Check that only 1 position is found", () -> {
            $(".section-header-box__text").shouldHave(Condition.text("найден: 1 результат"));
        });
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    @Description("IBS check article about the internet of things")
    @DisplayName("Search for the article")
    void test05() {
        step("Open url 'https://ibs.ru/'", () ->
                open("https://ibs.ru/"));
        step("click menu button", () -> {
            $(".btn-menu__ico").click();
        });
        step("click on it-infrastructure block", () -> {
            $(".menu-list").$(byText("IT-инфраструктура")).click();
        });
        step("check that the page contains the needed article", () -> {
            $(".products-list__col").shouldHave(Condition.text("Промышленный интернет вещей"));
        });
        step("click on the internet of things article", () -> {
            $(".services").$(byText("Промышленный интернет вещей")).click();
        });
        step("check that the page contains ask for details form", () -> {
            $(".form-bottom__title").shouldHave(Condition.text("Запросить контакты или подробности"));
        });
        $(byText("Запросить контакты или подробности")).scrollTo();
    }

}