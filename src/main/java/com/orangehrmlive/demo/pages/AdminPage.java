package com.orangehrmlive.demo.pages;

import com.orangehrmlive.demo.utility.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends Utility {

    public AdminPage(){

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//a[@id='menu_admin_UserManagement']")
    WebElement userManagementTab;

    @FindBy(xpath = "//a[@id='menu_admin_Job']")
    WebElement jobTab;

    @FindBy(xpath = "//a[@id='menu_admin_Organization']")
    WebElement organizationTab;


    public void clickOnUserManagementTab(){
        clickOnElement(userManagementTab);
    }

    public void clickOnJobTab(){
        clickOnElement(jobTab);
    }
    public void clickOnOrganizationTab(){
        clickOnElement(organizationTab);
    }
}
