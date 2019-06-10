package com.w2a.pages;

import com.w2a.base.Page;

public class HomePage extends Page {

	public LoginPage gotoLogin() {

		IsElementClickable("loginlink_XPATH");

		click("loginlink_XPATH");

		return new LoginPage();
	}

	public void gotoSignUp() {

		IsElementClickable("signuplink_XPATH");
		click("signuplink_XPATH");

	}

	public void gotoSupport() {

		IsElementClickable("gotosupportlink_XPATH");
		click("gotosupportlink_XPATH");
	}

	public void gotoLearnMore() {

	}

	public void validateFooterLinks() {

	}

}
