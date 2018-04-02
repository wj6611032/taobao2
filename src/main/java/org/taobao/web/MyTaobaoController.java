package org.taobao.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.taobao.pojo.Address;
import org.taobao.pojo.FavoritesGoods;
import org.taobao.pojo.FavoritesShops;
import org.taobao.pojo.Orders;
import org.taobao.pojo.Users;
import org.taobao.service.AddressService;
import org.taobao.service.FavoritesGoodService;
import org.taobao.service.FavoritesShopService;
import org.taobao.service.OrderGoodsService;
import org.taobao.service.OrderService;
import org.taobao.service.UserService;

@Controller
@RequestMapping("/myTaobao")
public class MyTaobaoController {
	@Resource
	private AddressService as;
	@Resource
	private UserService us;
	@Resource
	private OrderService os;
	@Resource
	private FavoritesGoodService fgs;
	@Resource
	private FavoritesShopService fss;
	@Resource
	private OrderGoodsService ogs;
	
	@RequestMapping("/selectAddress")
	@ResponseBody
	public List<Address> selectAddress(Integer userId) { //根据用户查询收货地址
		String sql = "select * from address where userId = "+userId;
		List<Address> address = as.selectAddress(sql);
		return address;
	}
	
	@RequestMapping("/selectOneAddress")
	@ResponseBody
	public Address selectOneAddress(Integer addressId) { //根据id查询收货地址
		Address address = as.selectOneAddress(addressId);
		return address;
	}
	
	@RequestMapping("/updateIsDefault")
	@ResponseBody
	public String updateIsDefault(Integer addressId,Integer userId) { //设为默认地址
		userId = 1;
		String sql = "select * from address where userId = "+userId;
		List<Address> addresses = as.selectAddress(sql);
		for (Address ad: addresses) {
			if (ad.getIsDefault() == 1) {
				ad.setIsDefault(0);
				as.saveOrUpdateAddress(ad);
			}
		}
		Address address = as.selectOneAddress(addressId);
		address.setIsDefault(1);
		as.saveOrUpdateAddress(address);
		return "ok";
	}
	
	@RequestMapping("/updateAddress")
	@ResponseBody
	public String updateAddress(Address address) { // 添加/修改收货地址
		as.saveOrUpdateAddress(address);
		return "ok";
	}
	
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public String deleteAddress(Integer addressId) { //删除收货地址
		as.deleteAddress(addressId);
		return "ok";
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public String updateUser(Users u) { //修改用户密码 头像 昵称
		us.saveOrUpdate(u);
		return "ok";
	}
	
	@RequestMapping("/updatePass")
	@ResponseBody
	public String updatePass(Integer userId,String password) { //修改密码
		Users u = us.selectOne(userId);
		u.setPassword(password);
		us.saveOrUpdate(u);
		return "ok";
	}
	
	@RequestMapping("/selectFavoritesGoods")
	@ResponseBody
	public List<FavoritesGoods> selectFavoritesGoods() { //查询所有关注商品
		String sql = "select * from favoritesGoods";
		List<FavoritesGoods> favoritesGoods = fgs.selectFavoritesGoods(sql);
		return favoritesGoods;
	}
	
	@RequestMapping("/insertFavoritesGood")
	@ResponseBody
	public String insertFavoritesGood(FavoritesGoods fGoods) { //添加关注商品
		fgs.insertFavoritesGood(fGoods);
		return "ok";
	}
	
	@RequestMapping("/deleteFavoritesGood")
	@ResponseBody
	public String deleteFavoritesGood(Integer id) { //删除关注商品
		fgs.deleteFavoritesGood(id);
		return "ok";
	}
	
	@RequestMapping("/selectFavoritesShops")
	@ResponseBody
	public List<FavoritesShops> selectFavoritesShops() { //查询所有关注店铺
		String sql = "select * from favoritesShops";
		List<FavoritesShops> favoritesShops = fss.selectFavoritesShops(sql);
		return favoritesShops;
	}
	
	@RequestMapping("/insertFavoritesShop")
	@ResponseBody
	public String insertFavoritesShop(FavoritesShops fShops) { //添加关注店铺
		fss.insertFavoritesShop(fShops);
		return "ok";
	}
	
	@RequestMapping("/deleteFavoritesShop")
	@ResponseBody
	public String deleteFavoritesShop(Integer id) { //删除关注店铺
		fss.deleteFavoritesShop(id);
		return "ok";
	}
	
	@RequestMapping("/selectOrders")
	@ResponseBody
	public List<Orders> selectOrders(Integer orderStatus) { //按  状态 用户  查询订单
		orderStatus = 1;
		String sql = "";
		if (orderStatus == 0) { //按所有订单查询
			sql = "select * from orders";
		} else if (orderStatus > 0) {
			sql = "select * from orders where orderStatus = "+orderStatus;
		}
		List<Orders> orders = os.selectOrders(sql);
		return orders;
	}
	
	@RequestMapping("/deleteOrderGoods")
	@ResponseBody
	public String deleteOrderGoods(int[] arrays) { //批量删除订单商品
		String hql = "";
		for (int i = 0; i < arrays.length; i++) {
			hql = "update OrderGoods set isDel = 0 where ogId = "+arrays[i];
			ogs.updateIsDel(hql);
		}
		return "ok";
	}
	
	@RequestMapping("/selectUser")
	@ResponseBody
	public String selectUser(String account,String password,HttpServletRequest request) { //按状态查询订单
		HttpSession session = request.getSession();
		String sql = "select * from users where account = "+account+" and password = "+password;
		List<Users> users = us.selectUser(sql);
		String str = "";
		if (users != null && users.size() != 0) {
			str = "ok";
			session.setAttribute("users", users.get(0));
		} else {
			str = "error";
		}
		return str;
	}
	
}
