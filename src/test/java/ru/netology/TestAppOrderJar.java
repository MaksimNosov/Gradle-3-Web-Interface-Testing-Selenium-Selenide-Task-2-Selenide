package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.apache.hc.core5.annotation.Contract;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestAppOrderJar {

    @Test
    void testNameNotTrue() {
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id='name'] input").setValue("English");
        form.$("[data-test-id='phone'] input").setValue("+79998887766");
        form.$("[data-test-id='agreement']").click();
        form.$("[id=root] button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void testNoName() {
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id='phone'] input").setValue("+79998887766");
        form.$("[data-test-id='agreement']").click();
        form.$("[id=root] button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void testPhoneNotTrue() {
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id='name'] input").setValue("Комбинированная-Фамилия Имя");
        form.$("[data-test-id='phone'] input").setValue("+1234567");
        form.$("[data-test-id='agreement']").click();
        form.$("[id=root] button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void testNoPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id='name'] input").setValue("Комбинированная-Фамилия Имя");
        form.$("[data-test-id='agreement']").click();
        form.$("[id=root] button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void testNoAgreement() {
        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id='name'] input").setValue("Комбинированная-Фамилия Имя");
        form.$("[data-test-id='phone'] input").setValue("+79998887766");
        form.$("[id=root] button").click();
        $("[data-test-id='agreement'].input_invalid").shouldBe(visible);
    }
}
