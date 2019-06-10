package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {	

	public  void gotoChat(){

		click("chatlink_XPATH");

	}

	public  CRMHomePage gotoCRM(){

		click("crmlink_XPATH");
		return new CRMHomePage();
	}

	public  void gotoSalesIQ(){
		click("salesIQlink_XPATH");

	}

}
