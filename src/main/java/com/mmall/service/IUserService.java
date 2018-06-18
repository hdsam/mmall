package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

public interface IUserService {

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	ServerResponse<User> login(String username,String password);
	
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	ServerResponse<String> register(User user);


	/**
	 * 根据类型检查username，或者email是否有效
	 * @param str 
	 * @param type 
	 * @return
	 */
	ServerResponse<String> checkValid(String str, String type);


	/**
	 * 根据用户名来获取问题题目
	 * @param username
	 * @return
	 */
	ServerResponse<String> selectQuestion(String username);


	/**
	 * 验证问题的答案是否正确
	 * @param username
	 * @param question
	 * @param answer
	 * @return
	 */
	ServerResponse<String> checkAnswer(String username, String question, String answer);


	/**
	 * 未登录并忘记密码时，修改密码
	 * @param username
	 * @param passwordNew
	 * @param forgetToken
	 * @return
	 */
	ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);


	/**
	 * 登录后修改密码
	 * @param passwordOld
	 * @param passwordNew
	 * @param user
	 * @return
	 */
	ServerResponse<String> restPassword(String passwordOld, String passwordNew, User user);


	/**
	 * 更新用户的信息，其中username不能更新，email不能是旧的
	 * @param user
	 * @return
	 */
	ServerResponse<User> updateInformation(User user);


	/**
	 * 获取用户的信息
	 * @param userId
	 * @return
	 */
	ServerResponse<User> getInformation(Integer userId);


	/**
	 * 后端，校验是否是管理员
	 * @param user
	 * @return
	 */
	ServerResponse<?> checkAdminRole(User user);
	
	
	
}
