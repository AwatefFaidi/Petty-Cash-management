package org.sid.pettycach.service;

public interface SecurityService {
	boolean isAuthenticated();
    void autoLogin(String username, String password);

}
