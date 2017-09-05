package com.corgiking.interceptor.auth;

import java.util.Date;
import java.util.List;

public class AccessToken {
    private String token;

    private String accountID;

    private List<String> accountRoles;

    private Date expireTime;

    private AccessTokenStatus status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public List<String> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(List<String> accountRoles) {
		this.accountRoles = accountRoles;
	}

	public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public AccessTokenStatus getStatus() {
        return status;
    }

    public void setStatus(AccessTokenStatus status) {
        this.status = status;
    }
    
}
