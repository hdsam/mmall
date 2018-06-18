package com.mmall.controller.backend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;

/**
 * @author green
 * 商品类别管理
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryController {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private ICategoryService iCategoryService;

	@RequestMapping(value = "add_category.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<?> addCategory(HttpSession session, String categoryName,
			@RequestParam(value = "parentId", defaultValue = "0") int parentId) {

		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		// 校验是否是管理员
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 是管理员，有权限操作
			return iCategoryService.addCategory(categoryName, parentId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}

	@RequestMapping(value = "set_category_name.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		// 校验是否是管理员
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 是管理员，有权限操作
			return iCategoryService.updateCategoryName(categoryId, categoryName);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}

	}

	@RequestMapping(value = "get_category.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<List<Category>> getChildrenParalleCategory(HttpSession session,
			@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		// 校验是否是管理员
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 是管理员，有权限操作:查询子节点的category值，不递归，获得平级的列表集合
			return iCategoryService.getChildrenParalleCategory(categoryId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}

	@RequestMapping(value = "get_deep_category.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,
			@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
		}
		// 校验是否是管理员
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 是管理员，有权限操作:查询子节点的category值，递归，获得下级的列表集合
			return iCategoryService.selectCategoryAndChildrenById(categoryId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
		}
	}
}
