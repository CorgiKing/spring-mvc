package com.corgiking.interceptor.auth;

import java.util.List;

public class AccountIdentity {

  /**
   * 登录账号
   * */
  private String accountID;
  
  /**
   * 用户角色
   * */
  private List<String> roleCodeList;

  /**
     * 权限集合
     */
  private List<String> permissionList;

  /**
   * 登录标志
   */
  private boolean loginFlag;

  public String getAccountID() {
    return accountID;
  }



  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }



  public List<String> getPermissionList() {
    return permissionList;
  }



  public void setPermissionList(List<String> permissionList) {
    this.permissionList = permissionList;
  }



  public boolean isLoginFlag() {
    return loginFlag;
  }



  public void setLoginFlag(boolean loginFlag) {
    this.loginFlag = loginFlag;
  }



  public List<String> getRoleCodeList() {
    return roleCodeList;
  }



  public void setRoleCodeList(List<String> roleCodeList) {
    this.roleCodeList = roleCodeList;
  }



  @Override
  public String toString() {
    return "AccountIdentity [permissionList=" + permissionList + ", accountID=" + accountID + "]";
  }
}
