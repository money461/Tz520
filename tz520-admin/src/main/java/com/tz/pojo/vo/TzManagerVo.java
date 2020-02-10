package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;

import com.tz.pojo.TzManager;
import com.tz.pojo.TzRole;

public class TzManagerVo extends TzManager {

	// 查询开始时间
		private Date startTime;
		// 更新结束时间
		private Date endTime;
		
		//一个账户对应多个角色
		List<TzRole> roleList;  //账户赋予角色
		
		public List<TzRole> getRoleList() {
			return roleList;
		}
		public void setRoleList(List<TzRole> roleList) {
			this.roleList = roleList;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		
	
}
