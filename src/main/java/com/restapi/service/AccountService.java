/*
 *    Copyright 2016-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.restapi.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.mapper.AccountMapper;
import com.restapi.pojo.Account;

/**
 * @author Kazuki Shimizu
 */
@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountMapper accountMapper;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public void createAccount(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountMapper.insertAccount(account);
	}

	@Transactional
	public void updateAccount(Account account) {
		accountMapper.updateAccount(account);
	}
	
	@Transactional
	public void deleteAccount(String userid) {
		accountMapper.deleteAccount(userid);
	}
	
	@Transactional
	public Account getAccountByUsername(String username) {
		return accountMapper.getAccountByUsername(username);
	}
	

}
