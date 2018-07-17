package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmDeleteFilePage extends AbstractPage {

    @FindBy(id = "submit-file")
    private WebElement submitDeleteButton;

    public void openPage() {

    }

    public ConfirmDeleteFilePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void confirmDeleting(){
        submitDeleteButton.click();
    }
}
