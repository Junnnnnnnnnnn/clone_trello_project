package com.clone_trello.CT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone_trello.CT.repository.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Override
	public boolean SearchInfo(String uname) {
		boolean result = loginRepository.SearchInfo(uname);
		return result;
	}
}
