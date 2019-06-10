package com.w2a.pages;

import com.w2a.base.Page;

public class LoginPage extends Page{
	
	
	
	public ZohoAppPage doLogin(String username,String password){
		
		type("email_XPATH",username);
		type("password_XPATH",password);
        click("loginsubmitbtn_XPATH");		
		
		return new ZohoAppPage();
	}

}
