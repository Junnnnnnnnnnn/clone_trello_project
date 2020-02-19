package com.clone_trello.CT.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.clone_trello.CT.VO.LoginVO;

@Repository
public class LoginRepository implements LoginRepositoryInterface{

	@Autowired
	JdbcTemplate jdbc;
	
	
	private class loginMapper implements RowMapper<LoginVO>{

	      @Override
	      public LoginVO mapRow(ResultSet rs, int count) throws SQLException {
	    	  LoginVO loginVO = new LoginVO();
	    	  
	    	  loginVO.setU_id(rs.getInt("user_uid"));
	    	  loginVO.setId(rs.getString("uname"));
	    	  loginVO.setPassword(rs.getString("pw"));
	    	  
	         return loginVO;
	      }
		
	}
	@Override
	public boolean SearchInfo(String uname) {
		String sql = "select * from loginTable where uname = ?";
		System.out.println("=====");
		LoginVO db_LoginVO = jdbc.queryForObject(sql, new loginMapper() , uname);
		String input_uname = uname;
		String db_uname = db_LoginVO.getId();
		System.out.println("db");
		System.out.println(db_LoginVO.getId());
		System.out.println(uname);
		
		
		if(db_uname.equals(input_uname)) {
			System.out.println("¼º°ø");
			return true;
			
		}
		return false;
	}
}