package com.tz.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.cache.RedisCache;
import com.tz.date.DateUtile;
import com.tz.id.IDUtils;
import com.tz.mapper.TzCityMapper;
import com.tz.mapper.TzUserCityMapper;
import com.tz.mapper.vo.TzCityMapperVo;
import com.tz.pojo.TzCity;
import com.tz.pojo.TzCityExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzUserCity;
import com.tz.pojo.TzUserCityExample;
import com.tz.pojo.vo.CityVo;
import com.tz.pojo.vo.CommonVo;
import com.tz.pojo.vo.UserVo;
import com.tz.res.MsgResult;
import com.tz.service.CityService;
import com.tz.service.UserCityService;

@Service
public class CityServiceImpl implements CityService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	TzCityMapper cityMapper;	
	private TzCityExample example;
	private TzCityExample.Criteria criteria;
	
	@Autowired 
	TzCityMapperVo cityMapperVo;	
	
	@Autowired 
	TzUserCityMapper userCityMapper;	
	private TzUserCityExample userCityExample;
	private TzUserCityExample.Criteria criteria2;
	
	@Autowired
	private RedisCache cache;
	//公共配置类
    @Autowired
    private CommonVo common;
    
	@Override
	public MsgResult getCityList(CityVo cityVo,Integer rows,Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //封装map集合
        Map<String,Object> map = new HashMap<String, Object>();
    	String cityName = cityVo.getCityName();
    	//手机号码
    	if(StringUtil.isNotEmpty(cityName)){
    		map.put("cityName", "%"+cityName+"%");
    	}else{
    		map.put("cityName", null);
    	}
    	//时间段
    	if(null != cityVo.getStartTime() && null != cityVo.getEndTime()){
    		map.put("startTime",DateUtile.pushDays(cityVo.getStartTime(),0) );
    		map.put("endTime", DateUtile.pushDays(cityVo.getEndTime(),0));
    	}else{
    		map.put("startTime",null);
    		map.put("endTime",null);
    	}
        List<CityVo> lists = cityMapperVo.selectCityAndUserList(map);
        //取记录总条数
        PageInfo<CityVo> pageInfo = new PageInfo<>(lists);
		msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}
	@Transactional
	@Override
	public MsgResult addOrUpdate(TzCity city, String type,String oldCityName) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		if(null != city){
			List<TzCity> list = new ArrayList<TzCity>();
			String cityName = city.getCityName();
			if("update".equals(type)){
				if(cityName.equals(oldCityName)){	
				}else{
					example = new TzCityExample();
			        criteria = example.createCriteria();
			        criteria.andCityNameEqualTo(cityName);
			        list = cityMapper.selectByExample(example);
				}
			}else{
				example = new TzCityExample();
		        criteria = example.createCriteria();
		        criteria.andCityNameEqualTo(cityName);
		        list = cityMapper.selectByExample(example);
			}
			if(list.size()>0){
				msgResult = MsgResult.nodata(false, "城市名已被注册！");
			}
			else{
					city.setUpdatedTime(new Date());
					Subject subject = SecurityUtils.getSubject();
					TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
					String id = manager.getId();
					//操作人
					city.setOperator(id);
					//新增
					if("add".equals(type)){		
						city.setCreatedTime(new Date());
						city.setId(IDUtils.genId());
						int res = cityMapper.insertSelective(city);
						if(res == 1){
							msgResult = MsgResult.nodata(true, "操作成功！");
						}else{
							msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
						}
					}//修改
					else if("update".equals(type)){
						int res = cityMapper.updateByPrimaryKeySelective(city);
						if(res == 1){
							msgResult = MsgResult.nodata(true, "操作成功！");
						}else{
							msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
						}
					}else{
						msgResult = MsgResult.nodata(false, "系统无法识别，请传递类型！");
					}
				}	
		}else{
			msgResult = MsgResult.nodata(false, "传递的参数不能为空！");
		}
		return msgResult;
	}
	@Override
	public MsgResult deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){ 
			userCityExample = new TzUserCityExample();
	        criteria2 = userCityExample.createCriteria();
	        criteria2.andCityIdEqualTo(id);
	        List<TzUserCity>  lists= userCityMapper.selectByExample(userCityExample);
	        if(lists.size() > 0){
	        	msgResult = MsgResult.nodata(false, "该城市已经被代理，请先取消代理！");
	        }else{
	        	int res = cityMapper.deleteByPrimaryKey(id);
				if(res == 1){
					msgResult = MsgResult.nodata(true, "操作成功！");
				}else{
					msgResult = MsgResult.nodata(false, "删除失败，请稍后重试！");
				}
	        }		
		}else{
			msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}
	@Override
	public MsgResult updateStatusById(String id, Integer state) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			TzCity city = new TzCity();
			city.setUpdatedTime(new Date());
			Subject subject = SecurityUtils.getSubject();
			TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
			String OperaterId = manager.getId();
			//操作人
			city.setOperator(OperaterId);
			//状态
			city.setState(state);
			city.setId(id);
			int res = cityMapper.updateByPrimaryKeySelective(city);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "操作失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.result(false, "操作失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}
	@Override
	public MsgResult selectById(String id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(StringUtil.isNotEmpty(id)){
			TzCity city  = cityMapper.selectByPrimaryKey(id);
			if(null != city){
				msgResult = MsgResult.result(true, "查询成功！", city);
			}else{
				msgResult = MsgResult.nodata(false, "查询失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.result(false, "操作失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}
	@Override
	public MsgResult getCityListByState(TzCity city,String type) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(null != city){
			//封装map集合
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("type", type);
			List<CityVo> lists = cityMapperVo.selectCityNotUserList(map);
			if(null != lists){
				msgResult = MsgResult.result(true, "查询成功！", lists);
			}else{
				msgResult = MsgResult.nodata(false, "查询失败，请稍后重试！");
			}
		}else{
			msgResult = MsgResult.result(false, "操作失败，请稍后重试！", "参数不能为空！");
		}	
		return msgResult;
	}

}
