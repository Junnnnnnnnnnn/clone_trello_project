package com.clone_trello.CT.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone_trello.CT.repository.LoginRepository;
import com.clone_trello.CT.repository.SignUpRepository;

@Service
public class SignUpService implements SignUpServiceInterface {
	
	@Autowired
	SignUpRepository signUpRespository;
	
	@Override
	public void InsertInfo(String id , String password) {
		signUpRespository.InsertInfo(id, password);
	}

}
