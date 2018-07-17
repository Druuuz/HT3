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

    public void createNewRepositoryWithReadme(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
        QuickSetupRepositoryPage quickSetupRepositoryPage = new QuickSetupRepositoryPage(driver);
        quickSetupRepositoryPage.createREADME();
        CreateNewFilePage createNewFilePage = new CreateNewFilePage(driver);
        createNewFilePage.addCurrentFile();
    }

    public String getCurrentRepositoryName() {
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        return openRepositoryPage.getRepositoryName();
    }

    public boolean createNewRepository(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
        return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
    }

    public boolean createNewRepositoryWithRandomName(String repositoryName, String repositoryDescription){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        String expectedRepoName = createNewRepositoryPage.createNewRepositoryRandom(repositoryName, repositoryDescription);
        return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
    }

    public void deleteRepository(String repositoryName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        openRepositoryPage.openSettings();
        RepositorySettingsPage repositorySettingsPage = new RepositorySettingsPage(driver);
        repositorySettingsPage.deleteRepository();
        ConfirmDeleteRepositoryPage confirmDeleteRepositoryPage = new ConfirmDeleteRepositoryPage(driver);
        confirmDeleteRepositoryPage.confirmDeletingByRepositoryName(repositoryName);
    }

    public boolean isRepositoryExist(String repositoryName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return mainPage.isExistForReal(repositoryName, username);
    }

    public void renameRepository(String repositoryName, String repositoryNewName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        openRepositoryPage.openSettings();
        RepositorySettingsPage repositorySettingsPage = new RepositorySettingsPage(driver);
        repositorySettingsPage.renameRepository(repositoryNewName);

    }

    public boolean checkOrganization(String username) {
        Header header = new Header(driver);
        header.clickUserFunctions();
        header.clickYourProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickOnOrganization();
        OrganizationPage organizationPage = new OrganizationPage(driver);
        organizationPage.clickOnPeopleTab();
        return organizationPage.isUserExist(username);

    }

    public boolean currentRepositoryIsEmpty() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
    }

    public void addFileToRepository(String repositoryName, String fileName, String text, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        openRepositoryPage.clickCreateNewFile();
        CreateNewFilePage createNewFilePage = new CreateNewFilePage(driver);
        createNewFilePage.addFileName(fileName);
        createNewFilePage.addTextLine(text);
        createNewFilePage.addCurrentFile();
    }

    public boolean isTestFileExist(String repositoryName, String fileName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        return openRepositoryPage.isFileExist(fileName);
    }

    public void deleteTestFile(String repositoryName, String fileName, String username) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.findRepositoryForReal(repositoryName, username);
        OpenRepositoryPage openRepositoryPage = new OpenRepositoryPage(driver);
        openRepositoryPage.clickOnCreatedFile(fileName);
        OpenFilePage openFilePage = new OpenFilePage(driver);
        openFilePage.deleteFile();
        ConfirmDeleteFilePage confirmDeleteFilePage = new ConfirmDeleteFilePage(driver);
        confirmDeleteFilePage.confirmDeleting();
    }


}
