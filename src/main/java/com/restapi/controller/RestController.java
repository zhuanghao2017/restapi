package com.restapi.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restapi.pojo.Account;
import com.restapi.service.AccountService;

@RequestMapping("/api")
@Controller 
public class RestController {
	@Autowired
	AccountService accountSrv;
	//根据用户id查询用户数据 
	@GetMapping
	public ResponseEntity<?> getUserById(@Param ("username")String username){
		Account account=accountSrv.getAccountByUsername(username);
		if(account!=null) {
			return ResponseEntity.ok(account.getEmail());
		}
		return ResponseEntity.ok("");
	}
	
	//新增用户
	@PostMapping
	public ResponseEntity<?> addUser(@Param ("user")Account user){
		accountSrv.createAccount(user);
		return ResponseEntity.ok("add success");
		
	}
	
	//更新用户
	@PutMapping
	public ResponseEntity<?> updateUser(Account user){
		accountSrv.updateAccount(user);
		return ResponseEntity.ok("update success");
		
	}
	
	//删除用户
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@Param ("userid")String userid){
		accountSrv.deleteAccount(userid);
		return ResponseEntity.ok("delete success");
		
	}
}
