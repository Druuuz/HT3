package com.epam.ta.steps;

import java.util.concurrent.TimeUnit;

import com.epam.ta.driver.DriverSingleton;
import com.epam.ta.pages.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Steps {
    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void loginGithub(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn(String username) {
        LoginPage loginPage = new LoginPage(driver);
        String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
        logger.info("Actual username: " + actualUsername);
        return actualUsername.equals(username);
    }

    //Create repository and put README.txt file there
    public void createNewRepositoryWithReadme(String repositoryName, String repositoryDescription, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
        QuickSetupRepositoryPage quickSetupRepositoryPage = new QuickSetupRepositoryPage(driver,repositoryName, username);
        quickSetupRepositoryPage.createREADME();
        CreateNewFilePage createNewFilePage = new CreateNewFilePage(driver, repositoryName, username);
        createNewFilePage.addCurrentFile();
    }

    public String getCurrentRepositoryName(String repositoryName, String username) {
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver,repositoryName, username);
        return openRepositoryPage.getRepositoryName();
    }

    //Create empty repository
    public boolean createNewRepository(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
        return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
    }

    //Create repository by name + random string
    public boolean createNewRepositoryWithRandomName(String repositoryName, String repositoryDescription){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        String expectedRepoName = createNewRepositoryPage.createNewRepositoryRandom(repositoryName, repositoryDescription);
        return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
    }

    public void deleteRepository(String repositoryName, String username) {
        findRepository(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver,repositoryName, username);
        openRepositoryPage.openSettings();
        RepositorySettingsPage repositorySettingsPage = new RepositorySettingsPage(driver, repositoryName, username);
        repositorySettingsPage.deleteRepository();
        ConfirmDeleteRepositoryPage confirmDeleteRepositoryPage = new ConfirmDeleteRepositoryPage(driver, repositoryName, username);
        confirmDeleteRepositoryPage.confirmDeletingByRepositoryName(repositoryName);
    }

    public boolean isRepositoryExist(String repositoryName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return mainPage.isExistForReal(repositoryName, username);
    }

    public void renameRepository(String repositoryName, String repositoryNewName, String username) {
        findRepository(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver, repositoryName, username);
        openRepositoryPage.openSettings();
        RepositorySettingsPage repositorySettingsPage = new RepositorySettingsPage(driver, repositoryName, username);
        repositorySettingsPage.renameRepository(repositoryNewName);

    }

    //Open organization profile and check existing this account
    public boolean checkOrganization(String username) {
        Header header = new Header(driver);
        header.clickUserFunctions();
        header.clickYourProfile();
        ProfilePage profilePage = new ProfilePage(driver, username);
        profilePage.clickOnOrganization();
        OrganizationPage organizationPage = new OrganizationPage(driver, driver.getCurrentUrl().split("/")[3]);
        organizationPage.clickOnPeopleTab();
        return organizationPage.isUserExist(username);

    }

    public boolean currentRepositoryIsEmpty() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
    }

    //Add simple file to repository
    public void addFileToRepository(String repositoryName, String fileName, String text, String username) {
        findRepository(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver,repositoryName, username);
        openRepositoryPage.clickCreateNewFile();
        CreateNewFilePage createNewFilePage = new CreateNewFilePage(driver, repositoryName, username);
        createNewFilePage.addFileName(fileName);
        createNewFilePage.addTextLine(text);
        createNewFilePage.addCurrentFile();
    }

    public boolean isTestFileExist(String repositoryName, String fileName, String username) {
        findRepository(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver, repositoryName, username);
        return openRepositoryPage.isFileExist(fileName);
    }

    //Delete file from repository
    public void deleteTestFile(String repositoryName, String fileName, String username) {
        findRepository(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver, repositoryName, username);
        openRepositoryPage.clickOnCreatedFile(fileName);
        OpenFilePage openFilePage = new OpenFilePage(driver, repositoryName, username, fileName);
        openFilePage.deleteFile();
        ConfirmDeleteFilePage confirmDeleteFilePage = new ConfirmDeleteFilePage(driver, repositoryName, username, fileName);
        confirmDeleteFilePage.confirmDeleting();
    }

    private void findRepository(String repositoryName, String username){
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
    }


}
