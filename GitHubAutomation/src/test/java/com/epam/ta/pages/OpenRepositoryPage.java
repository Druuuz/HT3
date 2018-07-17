package com.epam.ta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OpenRepositoryPage extends AbstractPage {


    private String BASE_URL = "https://github.com";
    @FindBy(xpath = "//form[@class='BtnGroup-form']/button")
    private WebElement createNewFileButton;

    @FindBy(xpath = "//a[@title='TestFile']")
    private WebElement testFile;

    @FindBy(xpath = "//a[@title='TestFile']")
    private List<WebElement> testFiles;

    @FindBy(xpath = "//*[@id=\"js-repo-pjax-container\"]/div[1]/nav/a[4]")
    private WebElement settings;

    @FindBy(xpath = "//strong[@itemprop='name']/a")
    private WebElement repositoryName;

    public void openPage() {

    }

    public OpenRepositoryPage(WebDriver driver, String repositoryName, String username) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        this.BASE_URL = BASE_URL + "/" + username + "/" + repositoryName;
    }

    public String getRepositoryName() {
        return repositoryName.getText();
    }

    public void openSettings() {
        settings.click();
    }

    public void clickCreateNewFile() {
        createNewFileButton.click();
    }

    public boolean isFileExist(String fileName) {
        return isExist(driver.findElements(By.linkText(fileName)));
        //return isExist(testFiles);
    }

    public void clickOnCreatedFile(String fileName) {
        driver.findElement(By.linkText(fileName)).click();
        //testFile.click();
    }


}
