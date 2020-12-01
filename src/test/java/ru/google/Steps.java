package ru.google;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Steps {

    private static String selectorSearchItem="//div[@class='g']";
    private static String selectorNamePage = "//div[@class='g']//h3";

    @Step("Шаг 1. Совершение поискового запроса")
    public static PageObjectGoogleSearch doGoogleSearch(WebDriver chromeDriver, String searchQuery){
        PageObjectGoogleSearch googlePO = new PageObjectGoogleSearch(chromeDriver);
        googlePO.doSearch(searchQuery);
        googlePO.refreshListElements();
        return googlePO;
    }


    @Step("Шаг 2. Проверка количества результатов больше 3")
    public static void checkResultAmount(WebDriver chromeDriver, PageObjectGoogleSearch googlePO){
        List<WebElement> listOfWebElement = chromeDriver.findElements(By.xpath(googlePO.getSelectorSearchItem()));
        System.out.println("yeeeeeeeeee");
        if (listOfWebElement.size() > 3){
            Assertions.assertTrue(true);
        }
        else {
            CustomUtils.getScreen(chromeDriver);
            Assertions.fail();
        }
    }

    @Step("Шаг 2. Проверка наличия имени: {name}")
    public static void checkContainsName(WebDriver chromeDriver, String name){
        List<WebElement> searchResultList = chromeDriver.findElements(By.xpath(selectorNamePage));
        System.out.println("proverka");
        if (searchResultList.stream().anyMatch(x->x.getText().contains(name))){
            Assertions.assertFalse(true);
        }
        else {
            Assertions.assertFalse(false);
            CustomUtils.getScreen(chromeDriver);
        }
    }
}
