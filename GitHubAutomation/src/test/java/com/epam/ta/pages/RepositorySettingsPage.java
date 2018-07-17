package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RepositorySettingsPage extends AbstractPage {


    @FindBy(xpath = "//div[@class='Box Box--danger']/ul/li[4]/details/summary")
    private WebElement deleteRepositoryLink;

    @FindBy(id = "rename_field")
    private WebElement renameField;

    @FindBy(xpath = "//*[@id=\"options_bucket\"]/form[1]/button")
    private WebElement renameButton;

    By renameButtonLocator = By.xpath("//*[@id=\"options_bucket\"]/form[1]/button[not(@disabled)]");

    public void openPage() {

    }

    public RepositorySettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void deleteRepository() {
        deleteRepositoryLink.click();
    }

    public void renameRepository(String newName) {
        renameField.clear();
        renameField.sendKeys(newName);
        wait.until(ExpectedConditions.numberOfElementsToBe(renameButtonLocator, 1));
        renameButton.click();
    }
}
