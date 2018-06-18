package com.mmall.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

public interface IProductService {

	/**
	 * 新增产品或更新产品，id为空则添加，否则更新
	 * @param product
	 * @return
	 */
	ServerResponse saveOrUpdateProduct(Product product);

	/**
	 * 设置产品的销售状态
	 * @param productId
	 * @param status
	 * @return
	 */
	ServerResponse<String> setSaleStatus(Integer productId, Integer status);

	/**
	 * 后端获取产品详细
	 * @param productId
	 * @return
	 */
	ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

	/**
	 * 分页的商品类别
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

	/**
	 * 根据商品名模糊搜索或者id精确搜索，抑或两者结合
	 * @param productName
	 * @param productId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

	/**
	 * 前端商品详细展示
	 * @param productId
	 * @return
	 */
	ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

	/**
	 * 前端根据关键字或者类别分页查询
	 * @param keyword
	 * @param categoryId
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize,
			String orderBy);

}
