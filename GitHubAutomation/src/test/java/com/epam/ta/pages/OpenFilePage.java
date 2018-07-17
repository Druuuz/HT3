package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenFilePage extends AbstractPage {

    private String BASE_URL = "https://github.com";
    @FindBy(xpath = "//button[@aria-label='Delete this file']")
    private WebElement deleteFileButton;

    public void openPage() {

    }

    public OpenFilePage(WebDriver driver, String repositoryName, String username, String filename) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username + "/" + repositoryName + "/blob/master/" + filename;
    }

    public void deleteFile() {
        deleteFileButton.click();
    }

}
