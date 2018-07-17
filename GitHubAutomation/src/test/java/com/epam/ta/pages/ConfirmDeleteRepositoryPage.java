package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmDeleteRepositoryPage extends AbstractPage {

    @FindBy(xpath = "//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")
    private WebElement nameOfRepositoryField;

    @FindBy(xpath = "//button[contains(text(),'I understand the consequences, delete this repository')]")
    private WebElement confirmDeletingButton;

    public void openPage() {

    }

    public ConfirmDeleteRepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private void setRepositoryName(String name) {
        nameOfRepositoryField.clear();
        nameOfRepositoryField.sendKeys(name);
    }

    public void confirmDeletingByRepositoryName(String name) {
        setRepositoryName(name);
        confirmDeletingButton.click();
    }

}
