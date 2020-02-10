package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TzRecommendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TzRecommendExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdIsNull() {
            addCriterion("referee_id is null");
            return (Criteria) this;
        }

        public Criteria andRefereeIdIsNotNull() {
            addCriterion("referee_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefereeIdEqualTo(String value) {
            addCriterion("referee_id =", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdNotEqualTo(String value) {
            addCriterion("referee_id <>", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdGreaterThan(String value) {
            addCriterion("referee_id >", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdGreaterThanOrEqualTo(String value) {
            addCriterion("referee_id >=", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdLessThan(String value) {
            addCriterion("referee_id <", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdLessThanOrEqualTo(String value) {
            addCriterion("referee_id <=", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdLike(String value) {
            addCriterion("referee_id like", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdNotLike(String value) {
            addCriterion("referee_id not like", value, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdIn(List<String> values) {
            addCriterion("referee_id in", values, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdNotIn(List<String> values) {
            addCriterion("referee_id not in", values, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdBetween(String value1, String value2) {
            addCriterion("referee_id between", value1, value2, "refereeId");
            return (Criteria) this;
        }

        public Criteria andRefereeIdNotBetween(String value1, String value2) {
            addCriterion("referee_id not between", value1, value2, "refereeId");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andRealIdIsNull() {
            addCriterion("real_id is null");
            return (Criteria) this;
        }

        public Criteria andRealIdIsNotNull() {
            addCriterion("real_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealIdEqualTo(String value) {
            addCriterion("real_id =", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdNotEqualTo(String value) {
            addCriterion("real_id <>", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdGreaterThan(String value) {
            addCriterion("real_id >", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_id >=", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdLessThan(String value) {
            addCriterion("real_id <", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdLessThanOrEqualTo(String value) {
            addCriterion("real_id <=", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdLike(String value) {
            addCriterion("real_id like", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdNotLike(String value) {
            addCriterion("real_id not like", value, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdIn(List<String> values) {
            addCriterion("real_id in", values, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdNotIn(List<String> values) {
            addCriterion("real_id not in", values, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdBetween(String value1, String value2) {
            addCriterion("real_id between", value1, value2, "realId");
            return (Criteria) this;
        }

        public Criteria andRealIdNotBetween(String value1, String value2) {
            addCriterion("real_id not between", value1, value2, "realId");
            return (Criteria) this;
        }

        public Criteria andIsplayIsNull() {
            addCriterion("isplay is null");
            return (Criteria) this;
        }

        public Criteria andIsplayIsNotNull() {
            addCriterion("isplay is not null");
            return (Criteria) this;
        }

        public Criteria andIsplayEqualTo(Integer value) {
            addCriterion("isplay =", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayNotEqualTo(Integer value) {
            addCriterion("isplay <>", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayGreaterThan(Integer value) {
            addCriterion("isplay >", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("isplay >=", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayLessThan(Integer value) {
            addCriterion("isplay <", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayLessThanOrEqualTo(Integer value) {
            addCriterion("isplay <=", value, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayIn(List<Integer> values) {
            addCriterion("isplay in", values, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayNotIn(List<Integer> values) {
            addCriterion("isplay not in", values, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayBetween(Integer value1, Integer value2) {
            addCriterion("isplay between", value1, value2, "isplay");
            return (Criteria) this;
        }

        public Criteria andIsplayNotBetween(Integer value1, Integer value2) {
            addCriterion("isplay not between", value1, value2, "isplay");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIsNull() {
            addCriterion("feedback_status is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIsNotNull() {
            addCriterion("feedback_status is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusEqualTo(Integer value) {
            addCriterion("feedback_status =", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotEqualTo(Integer value) {
            addCriterion("feedback_status <>", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusGreaterThan(Integer value) {
            addCriterion("feedback_status >", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_status >=", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusLessThan(Integer value) {
            addCriterion("feedback_status <", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_status <=", value, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusIn(List<Integer> values) {
            addCriterion("feedback_status in", values, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotIn(List<Integer> values) {
            addCriterion("feedback_status not in", values, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusBetween(Integer value1, Integer value2) {
            addCriterion("feedback_status between", value1, value2, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_status not between", value1, value2, "feedbackStatus");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeIsNull() {
            addCriterion("feedback_fee is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeIsNotNull() {
            addCriterion("feedback_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeEqualTo(Integer value) {
            addCriterion("feedback_fee =", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeNotEqualTo(Integer value) {
            addCriterion("feedback_fee <>", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeGreaterThan(Integer value) {
            addCriterion("feedback_fee >", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("feedback_fee >=", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeLessThan(Integer value) {
            addCriterion("feedback_fee <", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeLessThanOrEqualTo(Integer value) {
            addCriterion("feedback_fee <=", value, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeIn(List<Integer> values) {
            addCriterion("feedback_fee in", values, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeNotIn(List<Integer> values) {
            addCriterion("feedback_fee not in", values, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeBetween(Integer value1, Integer value2) {
            addCriterion("feedback_fee between", value1, value2, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("feedback_fee not between", value1, value2, "feedbackFee");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNull() {
            addCriterion("feedback_time is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIsNotNull() {
            addCriterion("feedback_time is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeEqualTo(Date value) {
            addCriterion("feedback_time =", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotEqualTo(Date value) {
            addCriterion("feedback_time <>", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThan(Date value) {
            addCriterion("feedback_time >", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("feedback_time >=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThan(Date value) {
            addCriterion("feedback_time <", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeLessThanOrEqualTo(Date value) {
            addCriterion("feedback_time <=", value, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeIn(List<Date> values) {
            addCriterion("feedback_time in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotIn(List<Date> values) {
            addCriterion("feedback_time not in", values, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeBetween(Date value1, Date value2) {
            addCriterion("feedback_time between", value1, value2, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andFeedbackTimeNotBetween(Date value1, Date value2) {
            addCriterion("feedback_time not between", value1, value2, "feedbackTime");
            return (Criteria) this;
        }

        public Criteria andMallIdIsNull() {
            addCriterion("mall_id is null");
            return (Criteria) this;
        }

        public Criteria andMallIdIsNotNull() {
            addCriterion("mall_id is not null");
            return (Criteria) this;
        }

        public Criteria andMallIdEqualTo(String value) {
            addCriterion("mall_id =", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdNotEqualTo(String value) {
            addCriterion("mall_id <>", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdGreaterThan(String value) {
            addCriterion("mall_id >", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdGreaterThanOrEqualTo(String value) {
            addCriterion("mall_id >=", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdLessThan(String value) {
            addCriterion("mall_id <", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdLessThanOrEqualTo(String value) {
            addCriterion("mall_id <=", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdLike(String value) {
            addCriterion("mall_id like", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdNotLike(String value) {
            addCriterion("mall_id not like", value, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdIn(List<String> values) {
            addCriterion("mall_id in", values, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdNotIn(List<String> values) {
            addCriterion("mall_id not in", values, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdBetween(String value1, String value2) {
            addCriterion("mall_id between", value1, value2, "mallId");
            return (Criteria) this;
        }

        public Criteria andMallIdNotBetween(String value1, String value2) {
            addCriterion("mall_id not between", value1, value2, "mallId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNull() {
            addCriterion("operater is null");
            return (Criteria) this;
        }

        public Criteria andOperaterIsNotNull() {
            addCriterion("operater is not null");
            return (Criteria) this;
        }

        public Criteria andOperaterEqualTo(String value) {
            addCriterion("operater =", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotEqualTo(String value) {
            addCriterion("operater <>", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThan(String value) {
            addCriterion("operater >", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterGreaterThanOrEqualTo(String value) {
            addCriterion("operater >=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThan(String value) {
            addCriterion("operater <", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLessThanOrEqualTo(String value) {
            addCriterion("operater <=", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterLike(String value) {
            addCriterion("operater like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotLike(String value) {
            addCriterion("operater not like", value, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterIn(List<String> values) {
            addCriterion("operater in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotIn(List<String> values) {
            addCriterion("operater not in", values, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterBetween(String value1, String value2) {
            addCriterion("operater between", value1, value2, "operater");
            return (Criteria) this;
        }

        public Criteria andOperaterNotBetween(String value1, String value2) {
            addCriterion("operater not between", value1, value2, "operater");
            return (Criteria) this;
        }

        public Criteria andTopTwoIsNull() {
            addCriterion("top_two is null");
            return (Criteria) this;
        }

        public Criteria andTopTwoIsNotNull() {
            addCriterion("top_two is not null");
            return (Criteria) this;
        }

        public Criteria andTopTwoEqualTo(Integer value) {
            addCriterion("top_two =", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoNotEqualTo(Integer value) {
            addCriterion("top_two <>", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoGreaterThan(Integer value) {
            addCriterion("top_two >", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoGreaterThanOrEqualTo(Integer value) {
            addCriterion("top_two >=", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoLessThan(Integer value) {
            addCriterion("top_two <", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoLessThanOrEqualTo(Integer value) {
            addCriterion("top_two <=", value, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoIn(List<Integer> values) {
            addCriterion("top_two in", values, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoNotIn(List<Integer> values) {
            addCriterion("top_two not in", values, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoBetween(Integer value1, Integer value2) {
            addCriterion("top_two between", value1, value2, "topTwo");
            return (Criteria) this;
        }

        public Criteria andTopTwoNotBetween(Integer value1, Integer value2) {
            addCriterion("top_two not between", value1, value2, "topTwo");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdIsNull() {
            addCriterion("city_love_id is null");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdIsNotNull() {
            addCriterion("city_love_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdEqualTo(String value) {
            addCriterion("city_love_id =", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdNotEqualTo(String value) {
            addCriterion("city_love_id <>", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdGreaterThan(String value) {
            addCriterion("city_love_id >", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdGreaterThanOrEqualTo(String value) {
            addCriterion("city_love_id >=", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdLessThan(String value) {
            addCriterion("city_love_id <", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdLessThanOrEqualTo(String value) {
            addCriterion("city_love_id <=", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdLike(String value) {
            addCriterion("city_love_id like", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdNotLike(String value) {
            addCriterion("city_love_id not like", value, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdIn(List<String> values) {
            addCriterion("city_love_id in", values, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdNotIn(List<String> values) {
            addCriterion("city_love_id not in", values, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdBetween(String value1, String value2) {
            addCriterion("city_love_id between", value1, value2, "cityLoveId");
            return (Criteria) this;
        }

        public Criteria andCityLoveIdNotBetween(String value1, String value2) {
            addCriterion("city_love_id not between", value1, value2, "cityLoveId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}