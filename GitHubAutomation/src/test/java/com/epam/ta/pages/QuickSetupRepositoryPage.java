package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuickSetupRepositoryPage extends AbstractPage {

    private String BASE_URL = "https://github.com";
    @FindBy(linkText = "README")
    private WebElement readmeLink;

    public void openPage() {

    }

    public QuickSetupRepositoryPage(WebDriver driver, String repositoryName, String username) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username + "/" + repositoryName;
    }

    public void createREADME() {
        readmeLink.click();
    }
}
