package com.epam.ta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewFilePage extends AbstractPage {

    private String BASE_URL = "https://github.com";

    @FindBy(name = "filename")
    private WebElement fileName;

    @FindBy(xpath = "//div[@class='CodeMirror-code']/div[1]/pre/span")
    private WebElement textLine;

    @FindBy(id = "submit-file")
    private WebElement submitFileButton;

    public void openPage() {

    }

    public CreateNewFilePage(WebDriver driver, String repositoryName, String username){
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username + "/" + repositoryName + "/new/master";
    }

    public void addTextLine(String text){
        textLine.clear();
        textLine.sendKeys(text);
    }

    public void addFileName(String filename){
        this.fileName.clear();
        this.fileName.sendKeys(filename);
    }

    public void addCurrentFile(){
        submitFileButton.click();
    }
}
