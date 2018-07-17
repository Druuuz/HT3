package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends AbstractPage {

    private String BASE_URL = "https://github.com";
    @FindBy(xpath = "//h1[@class='vcard-names']/span[2]")
    private WebElement profileUsername;

    @FindBy(xpath = "//div[@class='border-top py-3 clearfix']/a[1]")
    private WebElement organizationLink;


    public void openPage() {

    }

    public ProfilePage(WebDriver driver, String username) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username;

    }

    public String getProfileUsername() {
        return profileUsername.getText();
    }

    public void clickOnOrganization() {
        organizationLink.click();
    }
}
