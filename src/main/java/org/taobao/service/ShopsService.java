package org.taobao.service;

import java.util.List;

import org.taobao.pojo.Goods;
import org.taobao.pojo.Shops;

public interface ShopsService {
	List<Shops> queryAll(String sql);
}