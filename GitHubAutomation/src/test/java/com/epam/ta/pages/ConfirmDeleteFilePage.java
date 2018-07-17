package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmDeleteFilePage extends AbstractPage {

    private String BASE_URL = "https://github.com";
    @FindBy(id = "submit-file")
    private WebElement submitDeleteButton;

    public void openPage() {

    }

    public ConfirmDeleteFilePage(WebDriver driver, String repositoryName, String username, String filename){
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username + "/" + repositoryName + "/delete/master/" + filename;
    }

    public void confirmDeleting(){
        submitDeleteButton.click();
    }
}
