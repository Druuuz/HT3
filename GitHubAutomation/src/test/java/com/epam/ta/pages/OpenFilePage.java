package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenFilePage extends AbstractPage {

    @FindBy(xpath = "//button[@aria-label='Delete this file']")
    private WebElement deleteFileButton;

    public void openPage() {

    }

    public OpenFilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void deleteFile() {
        deleteFileButton.click();
    }

}
