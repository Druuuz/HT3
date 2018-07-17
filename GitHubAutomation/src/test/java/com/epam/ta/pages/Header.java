package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends AbstractPage {

    @FindBy(xpath = "//summary[@class='HeaderNavlink name mt-1']")
    private WebElement userFunctionsListLink;

    @FindBy(linkText = "Your profile")
    private WebElement profileLink;

    By dropDownListLocator = By.xpath("//details[@open]/summary[@class='HeaderNavlink name mt-1']");

    public void openPage() {

    }

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickUserFunctions() {
        userFunctionsListLink.click();
    }

    public void clickYourProfile() {
        wait.until(ExpectedConditions.numberOfElementsToBe(dropDownListLocator, 1));
        profileLink.click();
    }


}
