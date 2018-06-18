package com.mmall.service;

import java.util.List;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

public interface ICategoryService {

	/**
	 * 添加商品品类
	 * @param categoryName
	 * @param parentId
	 * @return
	 */
	ServerResponse addCategory(String categoryName, Integer parentId);

	/**
	 * 更新类别名字
	 * @param categoryId
	 * @param categoryName
	 * @return
	 */
	ServerResponse updateCategoryName(Integer categoryId, String categoryName);

	/**
	 * 返回平级的品类
	 * @param categoryId
	 * @return
	 */
	ServerResponse<List<Category>> getChildrenParalleCategory(Integer categoryId);

	/**
	 * 递归查询品类id，返回本节点的Id和孩子结点的Id
	 * @param categoryId
	 * @return
	 */
	ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

	
}
