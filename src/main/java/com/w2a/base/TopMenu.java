package com.w2a.base;


import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu {

	WebDriver driver;

	public TopMenu(WebDriver driver) {
		
		this.driver=driver;

	}

	public void gotoHome() {

	}

	public void gotoLeads() {

	}

	public void gotoContacts() {

	}

	public AccountsPage gotoAccounts() {
		
		Page.IsElementClickable("accountstab_XPATH");
        Page.click("accountstab_XPATH");
		return new AccountsPage();
	}

	public void gotoDeals() {

	}

}
