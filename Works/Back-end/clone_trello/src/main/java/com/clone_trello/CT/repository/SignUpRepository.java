package com.clone_trello.CT.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.clone_trello.CT.VO.LoginVO;

@Repository
public class SignUpRepository implements SignUpRepositoryInterface{

	@Autowired
	JdbcTemplate jdbc;
	
	public void InsertInfo(String id , String password) {
		String sql = "insert into loginTable values(seq_loginno.NEXTVAL,?,?)";
		String commit = "commit";
		jdbc.update(sql , id , password);
		jdbc.update(commit);
	}
}