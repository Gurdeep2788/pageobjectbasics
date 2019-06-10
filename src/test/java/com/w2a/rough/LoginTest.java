package com.w2a.rough;

import java.io.IOException;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) throws IOException {
	
        Page p = new Page();
		
		HomePage home=new HomePage();
		LoginPage lp=home.gotoLogin();		
		ZohoAppPage zp =lp.doLogin("gurukohliopp@gmail.com", "Mskohli@2");		
		zp.gotoCRM();		
		AccountsPage ap=Page.menu.gotoAccounts();	
		CreateAccountPage ca=ap.gotoCreateAccount();		
		ca.createAccount("Gurdeep");
		
				

	}

}
