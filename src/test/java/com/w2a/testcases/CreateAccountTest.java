package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.Utilities;

@Guice
public class CreateAccountTest extends Page{
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void createAccountTest(Hashtable<String,String> data){	
		
	ZohoAppPage zp = new ZohoAppPage();
	zp.gotoCRM();
	AccountsPage ap = Page.menu.gotoAccounts();
	CreateAccountPage ca = ap.gotoCreateAccount();
	ca.createAccount(data.get("accountname"));
	}

}
