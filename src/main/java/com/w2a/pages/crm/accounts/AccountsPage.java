package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class AccountsPage extends Page{
	
	public CreateAccountPage gotoCreateAccount()
	{
		click("createnewaccountbtn_XPATH");
		return new CreateAccountPage();
	}
	

}
