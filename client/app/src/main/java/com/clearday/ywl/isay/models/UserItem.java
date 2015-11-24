package com.clearday.ywl.isay.models;

public class UserItem {

	public UserItem(boolean isShowTopDivider, int navIcon, String navTitle, String navSubTitle) {
		this.isShowTopDivider = isShowTopDivider;
		this.navIcon = navIcon;
		this.navTitle = navTitle;
		this.navSubTitle = navSubTitle;
	}

	private boolean isShowTopDivider;
	private int navIcon;
	private String navTitle;
	private String navSubTitle;

	public boolean isShowTopDivider() {
		return isShowTopDivider;
	}

	public void setShowTopDivider(boolean isShowTopDivider) {
		this.isShowTopDivider = isShowTopDivider;
	}

	public int getNavIcon() {
		return navIcon;
	}

	public void setNavIcon(int navIcon) {
		this.navIcon = navIcon;
	}

	public String getNavTitle() {
		return navTitle;
	}

	public void setNavTitle(String navTitle) {
		this.navTitle = navTitle;
	}

	public String getNavSubTitle() {
		return navSubTitle;
	}

	public void setNavSubTitle(String navSubTitle) {
		this.navSubTitle = navSubTitle;
	}

}
