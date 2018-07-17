package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage extends AbstractPage {

    @FindBy(xpath = "//nav[@class='orgnav']/a[2]")
    private WebElement peopleTab;

    public void openPage() {

    }

    public OrganizationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnPeopleTab() {
        peopleTab.click();
    }

    public boolean isUserExist(String username) {
        return isExist(driver.findElements(By.xpath(
                " //ul[@class='member-listing-next table-list table-list-bordered adminable']" +
                        "/li/div[3]/span[contains(text(),'" + username + "')]")));

    }

}
