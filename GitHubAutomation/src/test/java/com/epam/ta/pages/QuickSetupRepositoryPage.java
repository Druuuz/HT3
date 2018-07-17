package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QuickSetupRepositoryPage extends AbstractPage {

    @FindBy(linkText = "README")
    private WebElement readmeLink;

    public void openPage() {

    }

    public QuickSetupRepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void createREADME() {
        readmeLink.click();
    }
}
