package com.tz.pojo.vo;

import java.io.Serializable;

public class RecommendVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6041182121626192873L;
	//总爱心值
	private Integer loveTotal = 0;
	//剩余爱心值
	private Integer loveSurplus = 0;
    //一级推荐
    private Integer gradeOne = 0;
    //二级推荐
    private Integer gradeTwo = 0;
    //状态
    private Integer status = 1;

	public Integer getGradeOne() {
		return gradeOne;
	}

	public void setGradeOne(Integer gradeOne) {
		this.gradeOne = gradeOne;
	}

	public Integer getGradeTwo() {
		return gradeTwo;
	}

	public void setGradeTwo(Integer gradeTwo) {
		this.gradeTwo = gradeTwo;
	}

	public Integer getLoveTotal() {
		return loveTotal;
	}

	public void setLoveTotal(Integer loveTotal) {
		this.loveTotal = loveTotal;
	}

	public Integer getLoveSurplus() {
		return loveSurplus;
	}

	public void setLoveSurplus(Integer loveSurplus) {
		this.loveSurplus = loveSurplus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
}