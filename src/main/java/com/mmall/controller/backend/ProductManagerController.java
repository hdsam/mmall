package com.mmall.controller.backend;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IFileService;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import com.mmall.util.PropertiesUtil;

/**
 * @author green 商品管理
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManagerController {

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IProductService iProductService;

	@Autowired
	private IFileService IFileService;

	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse productSave(HttpSession session, Product product) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			return iProductService.saveOrUpdateProduct(product);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}
	}

	@RequestMapping(value = "set_sale_status.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<String> setSaleStatus(HttpSession session, Integer productId, Integer status) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			return iProductService.setSaleStatus(productId, status);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}
	}

	@RequestMapping(value = "detail.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getDetail(HttpSession session, Integer productId) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			return iProductService.manageProductDetail(productId);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}

	}

	@RequestMapping(value = "list.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			return iProductService.getProductList(pageNum, pageSize);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}

	}

	@RequestMapping(value = "search.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse productSearch(HttpSession session, String productName, Integer productId,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			return iProductService.searchProduct(productName, productId, pageNum, pageSize);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}
	}

	// 配合spring-mvc中配置的MutiPartResover实现文件上传
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	@ResponseBody
	public ServerResponse<Map> upload(HttpSession session,
			@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {

		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要管理员登录");
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			String path = request.getSession().getServletContext().getRealPath("upload");
			String targetFileName = IFileService.upload(file, path);
			String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
			Map<String, String> fileMap = Maps.newHashMap();
			fileMap.put("uri", targetFileName);
			fileMap.put("url", url);
			return ServerResponse.createBySuccess(fileMap);
		} else {
			return ServerResponse.createByErrorMessage("无权限操作");
		}
	}
	@RequestMapping(value = "richtext_img_upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> richTextImgUpload(HttpSession session,
			@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> resultMap = Maps.newHashMap();
		User user = (User) session.getAttribute(Const.CURRENT_USER);
		if (user == null) {
			resultMap.put("success",false);
			resultMap.put("msg","请登录管理员");
			return resultMap;
		}
		if (iUserService.checkAdminRole(user).isSuccess()) {
			// 有权限,调用service逻辑层处理
			String path = request.getSession().getServletContext().getRealPath("upload");
			String targetFileName = IFileService.upload(file, path);
			if(StringUtils.isBlank(targetFileName)) {
				resultMap.put("success",false);
				resultMap.put("msg","上传失败");
				return resultMap;
			}
			String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;
			resultMap.put("success",true);
			resultMap.put("msg","上传成功");
			resultMap.put("file_path", url);
			//修改response的Header
			response.addHeader("Access-Control-Allow-Headers", "X-File-Name");
			return resultMap;
		} else {
			resultMap.put("success",false);
			resultMap.put("msg","无权限操作");
			return resultMap;
		}
	}
	
	
}
