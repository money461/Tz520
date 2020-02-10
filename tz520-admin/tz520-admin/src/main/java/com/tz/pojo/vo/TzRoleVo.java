package com.tz.pojo.vo;

import java.util.Date;
import java.util.List;

import com.tz.pojo.TzAuthFunction;
import com.tz.pojo.TzRole;

public class TzRoleVo extends TzRole {

	   //查询开始时间
		private Date startTime;
		//更新结束时间
		private Date endTime;
		
		//一个角色对应的多个权限信息
		private List<TzAuthFunction> authFunctionList;
		
		
		public List<TzAuthFunction> getAuthFunctionList() {
			return authFunctionList;
		}
		public void setAuthFunctionList(List<TzAuthFunction> authFunctionList) {
			this.authFunctionList = authFunctionList;
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
