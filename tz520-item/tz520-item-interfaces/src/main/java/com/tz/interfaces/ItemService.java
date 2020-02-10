package com.tz.interfaces;

import java.util.List;

import com.tz.pojo.vo.TzMallItem;
import com.tz.res.AppMsgResult;


public interface ItemService {

	//商城首页查询商品信息
	 List<TzMallItem> selectIndexItemList();

	//根据商品名称， 模糊搜索商品
	AppMsgResult queryItemByItemTitle(String itemTitle, Integer rows, Integer curPage);

	//展示商品详情
	AppMsgResult queryItemDetailById(String id);

	//随机推荐商品信息
	AppMsgResult recommendItem(Integer i);

	//根据分类查询商品信息
	AppMsgResult queryItemByCategory(String id);

	//根据分类id随机推荐2个商品
	AppMsgResult recommendItemByCategory(String ids);

	//展示商城首页所有的信息
	AppMsgResult showStoreIndex();

}
