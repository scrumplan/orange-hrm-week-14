package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.AddUserPage;
import com.orangehrmlive.demo.pages.HomePage;
import com.orangehrmlive.demo.pages.LoginPage;
import com.orangehrmlive.demo.pages.ViewSystemUsersPage;
import com.orangehrmlive.demo.testbase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class UsersTest extends TestBase {

    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUsersPage viewSystemUsersPage;
    AddUserPage addUserPage;

    @BeforeMethod(alwaysRun = true)
    public void initialize() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUsersPage = new ViewSystemUsersPage();
        addUserPage = new AddUserPage();
    }

    @Test(priority = 0, groups = {"sanity", "smoke", "regression"})
    public void adminShouldAddUserSuccessFully() throws InterruptedException {
        loginPage.loginToApplication("Admin", "admin123");
        viewSystemUsersPage.clickOnAdminTab();
        viewSystemUsersPage.verifySystemUsersText("System Users");
        viewSystemUsersPage.clickAddButton();
        addUserPage.verifyAddUserText("Add User");
        addUserPage.addUserDetails("Admin", "Ananya Dash", "Ananya7", "Disabled", "Ananya123", "Ananya123");
        addUserPage.clickOnSaveButton();
        viewSystemUsersPage.verifySuccessfullySavedMessage("Successfully Saved");
    }

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void searchTheUserCreatedAndVerifyIt() throws InterruptedException {
        loginPage.loginToApplication("Admin", "admin123");
        viewSystemUsersPage.clickOnAdminTab();
        viewSystemUsersPage.verifySystemUsersText("System Users");
        viewSystemUsersPage.inputUserName("Ananya7");
        viewSystemUsersPage.selectUserRole("Admin");
        viewSystemUsersPage.selectUserStatus("Disabled");
        viewSystemUsersPage.clickOnSearchButton();
        viewSystemUsersPage.verifyUserInSearchResult("Ananya7");

    }

    @Test(priority = 2, groups = {"regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully() throws InterruptedException {
        searchTheUserCreatedAndVerifyIt();
        viewSystemUsersPage.clickOnCheckBox();
        viewSystemUsersPage.clickOnDeleteButton();
        viewSystemUsersPage.acceptPopUpMessage();
        viewSystemUsersPage.verifyRecordSuccessfullyDeletedMessage("Successfully Deleted");
    }

    @Test(priority = 3, groups = {"regression"})
    public void searchTheDeletedUserAndVerifyTheMessageNoRecordFound() {
        loginPage.loginToApplication("Admin", "admin123");
        viewSystemUsersPage.clickOnAdminTab();
        viewSystemUsersPage.verifySystemUsersText("System Users");
        viewSystemUsersPage.inputUserName("Ananya7");
        viewSystemUsersPage.selectUserRole("Admin");
        viewSystemUsersPage.selectUserStatus("Disabled");
        viewSystemUsersPage.clickOnSearchButton();
        viewSystemUsersPage.verifyNoRecordFoundText("No Records Found");
    }
}
