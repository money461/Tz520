package com.tz.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TzMallExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TzMallExample() {
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

        public Criteria andMallNameIsNull() {
            addCriterion("mall_name is null");
            return (Criteria) this;
        }

        public Criteria andMallNameIsNotNull() {
            addCriterion("mall_name is not null");
            return (Criteria) this;
        }

        public Criteria andMallNameEqualTo(String value) {
            addCriterion("mall_name =", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameNotEqualTo(String value) {
            addCriterion("mall_name <>", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameGreaterThan(String value) {
            addCriterion("mall_name >", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameGreaterThanOrEqualTo(String value) {
            addCriterion("mall_name >=", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameLessThan(String value) {
            addCriterion("mall_name <", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameLessThanOrEqualTo(String value) {
            addCriterion("mall_name <=", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameLike(String value) {
            addCriterion("mall_name like", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameNotLike(String value) {
            addCriterion("mall_name not like", value, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameIn(List<String> values) {
            addCriterion("mall_name in", values, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameNotIn(List<String> values) {
            addCriterion("mall_name not in", values, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameBetween(String value1, String value2) {
            addCriterion("mall_name between", value1, value2, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallNameNotBetween(String value1, String value2) {
            addCriterion("mall_name not between", value1, value2, "mallName");
            return (Criteria) this;
        }

        public Criteria andMallUrlIsNull() {
            addCriterion("mall_url is null");
            return (Criteria) this;
        }

        public Criteria andMallUrlIsNotNull() {
            addCriterion("mall_url is not null");
            return (Criteria) this;
        }

        public Criteria andMallUrlEqualTo(String value) {
            addCriterion("mall_url =", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlNotEqualTo(String value) {
            addCriterion("mall_url <>", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlGreaterThan(String value) {
            addCriterion("mall_url >", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlGreaterThanOrEqualTo(String value) {
            addCriterion("mall_url >=", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlLessThan(String value) {
            addCriterion("mall_url <", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlLessThanOrEqualTo(String value) {
            addCriterion("mall_url <=", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlLike(String value) {
            addCriterion("mall_url like", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlNotLike(String value) {
            addCriterion("mall_url not like", value, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlIn(List<String> values) {
            addCriterion("mall_url in", values, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlNotIn(List<String> values) {
            addCriterion("mall_url not in", values, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlBetween(String value1, String value2) {
            addCriterion("mall_url between", value1, value2, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallUrlNotBetween(String value1, String value2) {
            addCriterion("mall_url not between", value1, value2, "mallUrl");
            return (Criteria) this;
        }

        public Criteria andMallStatusIsNull() {
            addCriterion("mall_status is null");
            return (Criteria) this;
        }

        public Criteria andMallStatusIsNotNull() {
            addCriterion("mall_status is not null");
            return (Criteria) this;
        }

        public Criteria andMallStatusEqualTo(Integer value) {
            addCriterion("mall_status =", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusNotEqualTo(Integer value) {
            addCriterion("mall_status <>", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusGreaterThan(Integer value) {
            addCriterion("mall_status >", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("mall_status >=", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusLessThan(Integer value) {
            addCriterion("mall_status <", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusLessThanOrEqualTo(Integer value) {
            addCriterion("mall_status <=", value, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusIn(List<Integer> values) {
            addCriterion("mall_status in", values, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusNotIn(List<Integer> values) {
            addCriterion("mall_status not in", values, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusBetween(Integer value1, Integer value2) {
            addCriterion("mall_status between", value1, value2, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("mall_status not between", value1, value2, "mallStatus");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceIsNull() {
            addCriterion("mall_introduce is null");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceIsNotNull() {
            addCriterion("mall_introduce is not null");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceEqualTo(String value) {
            addCriterion("mall_introduce =", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceNotEqualTo(String value) {
            addCriterion("mall_introduce <>", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceGreaterThan(String value) {
            addCriterion("mall_introduce >", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("mall_introduce >=", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceLessThan(String value) {
            addCriterion("mall_introduce <", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceLessThanOrEqualTo(String value) {
            addCriterion("mall_introduce <=", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceLike(String value) {
            addCriterion("mall_introduce like", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceNotLike(String value) {
            addCriterion("mall_introduce not like", value, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceIn(List<String> values) {
            addCriterion("mall_introduce in", values, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceNotIn(List<String> values) {
            addCriterion("mall_introduce not in", values, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceBetween(String value1, String value2) {
            addCriterion("mall_introduce between", value1, value2, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallIntroduceNotBetween(String value1, String value2) {
            addCriterion("mall_introduce not between", value1, value2, "mallIntroduce");
            return (Criteria) this;
        }

        public Criteria andMallManagerIsNull() {
            addCriterion("mall_manager is null");
            return (Criteria) this;
        }

        public Criteria andMallManagerIsNotNull() {
            addCriterion("mall_manager is not null");
            return (Criteria) this;
        }

        public Criteria andMallManagerEqualTo(String value) {
            addCriterion("mall_manager =", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerNotEqualTo(String value) {
            addCriterion("mall_manager <>", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerGreaterThan(String value) {
            addCriterion("mall_manager >", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerGreaterThanOrEqualTo(String value) {
            addCriterion("mall_manager >=", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerLessThan(String value) {
            addCriterion("mall_manager <", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerLessThanOrEqualTo(String value) {
            addCriterion("mall_manager <=", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerLike(String value) {
            addCriterion("mall_manager like", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerNotLike(String value) {
            addCriterion("mall_manager not like", value, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerIn(List<String> values) {
            addCriterion("mall_manager in", values, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerNotIn(List<String> values) {
            addCriterion("mall_manager not in", values, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerBetween(String value1, String value2) {
            addCriterion("mall_manager between", value1, value2, "mallManager");
            return (Criteria) this;
        }

        public Criteria andMallManagerNotBetween(String value1, String value2) {
            addCriterion("mall_manager not between", value1, value2, "mallManager");
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