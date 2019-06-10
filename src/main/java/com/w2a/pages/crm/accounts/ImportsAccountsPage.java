package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class ImportsAccountsPage extends Page{
 
	public void gotoCreateAccounts(){
		
		
		click("createaccountslink_XPATH");	
		
	}
	
	public void gotoImportAccounts(){
		
		click("importaccountslink_XPATH");
		
	}
}
