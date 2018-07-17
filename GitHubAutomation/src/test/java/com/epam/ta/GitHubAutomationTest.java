package com.epam.ta;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.ta.steps.Steps;

public class GitHubAutomationTest
{
	private Steps steps;
	private final String USERNAME = "testautomationuser";
	private final String PASSWORD = "Time4Death!";

	@BeforeMethod(description = "Init browser")
	public void setUp()
	{
		steps = new Steps();
		steps.initBrowser();
	}

	@Test
	public void oneCanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepositoryWithRandomName("testRepo", "auto-generated test repo"));
		//Assert.assertTrue(steps.currentRepositoryIsEmpty());
		// do not use lots of asserts
	}

	@Test(description = "Login to Github")
	public void oneCanLoginGithub()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.isLoggedIn(USERNAME));
	}

	//Добавление файла в репозиторий
	@Test
	public void canAddFileToRepository(){
		steps.loginGithub(USERNAME, PASSWORD);
		steps.createNewRepositoryWithReadme("testRepoForAddFile", "auto-generated test repo");
		steps.addFileToRepository("testRepoForAddFile","testFile", "Text",USERNAME);
		Assert.assertTrue(steps.isTestFileExist("testRepoForAddFile","testFile", USERNAME),"File does not exist");
		steps.deleteTestFile("testRepoForAddFile","testFile",USERNAME);
		Assert.assertFalse(steps.isTestFileExist("testRepoForAddFile","testFile",USERNAME),"File exists");
		steps.deleteRepository("testRepoForAddFile",USERNAME);
	}

	//Наличие у организации текущего аккаунта в списке
	@Test
	public void checkOrganization(){
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.checkOrganization(USERNAME),"Organisation does not contain this account");
	}

	//Добавление репозитория и удаление репозитория
	@Test
	public void сanCreateProject()
	{
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepository", "auto-generated test repo"));
		Assert.assertTrue(steps.isRepositoryExist("testRepository",USERNAME),"Project hasn't been created");
		steps.deleteRepository("testRepository",USERNAME);
		Assert.assertFalse(steps.isRepositoryExist("testRepository",USERNAME),"Project hasn't been deleted");
	}

	//Изменение названия каталога
	@Test
	public void renameRepository(){
		steps.loginGithub(USERNAME, PASSWORD);
		Assert.assertTrue(steps.createNewRepository("testRepository2018", "auto-generated test repo"));
		Assert.assertEquals("testRepository2018", steps.getCurrentRepositoryName());
		steps.renameRepository("testRepository2018","renamedRepository2018",USERNAME);
		Assert.assertEquals("renamedRepository2018", steps.getCurrentRepositoryName(),"Repository name hasn't been changed");
		steps.deleteRepository("renamedRepository2018", USERNAME);
	}

	@AfterMethod(description = "Stop Browser")
	public void stopBrowser()
	{
		steps.closeDriver();
	}

}
