package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public abstract void openPage();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public boolean isExist(List<WebElement> elements) {
        if (!elements.isEmpty()) {
            return true;
        }
        return false;
    }

}
