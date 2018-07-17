package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//a[contains(@aria-label, 'Create new')]")
    private WebElement buttonCreateNew;

    @FindBy(id = "dashboard-repos-filter")
    private WebElement findRepositoryField;

    @FindBy(xpath = "//div[@class='Box-header']/h3/a")
    private WebElement newRepoLink;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnCreateNewRepositoryButton() {
        newRepoLink.click();
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean isExistForReal(String repositoryName, String username) {
        findRepositoryField.clear();
        findRepositoryField.sendKeys(repositoryName);
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[@href='/" + username + "/" + repositoryName + "']"), 1));
        } catch (TimeoutException e) {
            return false;
        }
        WebElement rep = driver.findElement(By.xpath("//a[@href='/" + username + "/" + repositoryName + "']"));
        if (rep.getAttribute("href").contains("/" + username + "/" + repositoryName)) {
            return true;
        }
        return false;
    }

    public void findRepositoryForReal(String repositoryName, String username) {
        findRepositoryField.clear();
        findRepositoryField.sendKeys(repositoryName);
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//a[@href='/" + username + "/" + repositoryName + "']"), 1));
        WebElement rep = driver.findElement(By.xpath("//a[@href='/" + username + "/" + repositoryName + "']"));
        if (rep.getAttribute("href").contains("/" + username + "/" + repositoryName)) {
            rep.click();
        }
    }


}
