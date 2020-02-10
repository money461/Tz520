package com.tz.serviceImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tz.id.IDUtils;
import com.tz.mapper.TzAppMapper;
import com.tz.mapper.TzAuthFunctionMapper;
import com.tz.mapper.TzRoleFunctionMapper;
import com.tz.mapper.vo.TzAuthFunctionMapperVo;
import com.tz.pojo.TzApp;
import com.tz.pojo.TzAppExample;
import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzAuthFunctionExample;
import com.tz.pojo.TzManager;
import com.tz.pojo.TzRole;
import com.tz.pojo.TzRoleFunctionExample;
import com.tz.pojo.TzUser;
import com.tz.pojo.TzUserMall;
import com.tz.pojo.TzAuthFunctionExample.Criteria;
import com.tz.res.MsgResult;
import com.tz.service.AppService;
import com.tz.service.AuthFunctionService;


@Service
public class AppServiceImpl implements AppService  {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private TzAppMapper appMapper ;
	private TzAppExample appExample;
	private TzAppExample.Criteria criteria;
	
	@Override
	public MsgResult findAll(Integer rows, Integer curPage) throws Exception {
		MsgResult msgResult = null;
		rows = rows == null?10:rows;
		curPage = curPage == null?1:curPage;
		//分页处理
        PageHelper.startPage(curPage, rows);
        //设置查询条件
        appExample = new TzAppExample();
		criteria = appExample.createCriteria();
		appExample.setOrderByClause(" created_time DESC ");	
		System.out.println("分页查询信息");
		//调用Mapper进行查询
		List<TzApp> apps = appMapper.selectByExample(appExample);
		 
		//获取分页数据结果
	    PageInfo<TzApp> pageInfo = new PageInfo<TzApp>(apps);
	    msgResult = MsgResult.result(true, "", pageInfo);
		return msgResult;
	}

	@Override
	public MsgResult add(TzApp app) throws Exception {
		// TODO Auto-generated method stub
		MsgResult msgResult = null;
		app.setCreatedTime(new Date());
		app.setUpdatedTime(new Date());
		Subject subject = SecurityUtils.getSubject();
		TzManager manager = (TzManager)subject.getSession().getAttribute("manager");
		String id = manager.getId();
		app.setOperater(id);
		int res = 0;
		res = appMapper.insertSelective(app);
		if(res == 1){
			msgResult = MsgResult.nodata(true, "操作成功！");
		}else{
			msgResult = MsgResult.nodata(false, "添加失败，请稍后重试！");
		}
		return msgResult;
	}

	@Override
	public MsgResult deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		MsgResult  msgResult = null;
		if(id > 0){
			int res = appMapper.deleteByPrimaryKey(id);
			if(res == 1){
				msgResult = MsgResult.nodata(true, "操作成功！");
			}else{
				msgResult = MsgResult.nodata(false, "删除失败，请稍后重试！");
			}	
		}else{
			msgResult = MsgResult.result(false, "删除失败，请稍后重试！", "参数id不能为空！");
		}	
		return msgResult;
	}


	


	
	
}
