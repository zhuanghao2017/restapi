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
package com.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * 主配置文件
 * 
 * @author Kazuki Shimizu
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@ControllerAdvice
	static class WebMvcControllerAdvice {
		/*应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器*/
		@InitBinder
		public void registerCustomEditors(WebDataBinder binder) {
			// configure for empty string change to null
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		}
		@ExceptionHandler(NullPointerException.class)
	    public String processUnauthenticatedException(NativeWebRequest request, NullPointerException e) {  
	        System.out.println("===========应用到所有@RequestMapping注解的方法，在其抛出NullPointerException异常时执行");  
	        return "catalog/catalog"; //返回一个逻辑视图名  
	    } 
	}
	
	 

//	@Autowired
//	protected void configureThymeleafSpringTemplateEngine(SpringTemplateEngine templateEngine) {
//		templateEngine.setEnableSpringELCompiler(true);
//	}

}