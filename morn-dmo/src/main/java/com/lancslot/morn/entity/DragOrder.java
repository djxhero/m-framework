package com.lancslot.morn.entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Table(name="sky_drag_order")
public class DragOrder implements Serializable {
    @Id
    private Integer id;

    /**
     * 主订单号
     */
    private String morderNo;

    /**
     * 订单号，发给企业家
     */
    private String orderNo;

    /**
     * 机会id
     */
    private Long opportunityId;

    /**
     * 学员id
     */
    private Integer studentId;

    /**
     * 咨询师263@前
     */
    private String consultantAccount;

    /**
     * 销售组id
     */
    private Integer groupId;

    /**
     * 销售部id
     */
    private Integer saleDepartmentId;

    /**
     * 军团id
     */
    private Integer legionId;

    /**
     * 订单归属的量子团
     */
    private Integer quantumId;

    /**
     * 事业部id
     */
    private Integer businessId;

    /**
     * 项目id
     */
    private Integer firstProjId;

    /**
     * 班级id
     */
    private Integer classId;

    /**
     * 班型价格
     */
    private BigDecimal classPrice;

    /**
     * 省份id
     */
    private Integer provinceId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     * 订单手机号
     */
    private String mobile;

    /**
     * 订单：NEW、DEAL、PRODCHANGED、REFUND、NORESPONSE、CANCEL分别对应新建、成交、转班型、退款、未响应、取消
     */
    private String state;

    /**
     * 优惠券代码
     */
    private String couponCode;

    /**
     * 优惠券金额
     */
    private BigDecimal couponPrice;

    /**
     * 是否有保险：0没有，1有
     */
    private Byte insuranceFlag;

    /**
     * 保险金额
     */
    private BigDecimal insurancePrice;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 当周/跨期
     */
    private Boolean isThisWeek;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 0不删除1删除
     */
    private Byte deleteFlag;

    /**
     * 第一次有效跟进距离当前的天数
     */
    private Integer durationFirstTime;

    /**
     * 0:无关联订单，1:有关联订单
     */
    private Byte isHaveRelatedOrder;

    /**
     * 关联订单号
     */
    private String relatedOrderNo;

    /**
     * 报考省份
     */
    private Integer examProvinceId;

    /**
     * 服务期天数
     */
    private Integer servicePeriod;

    /**
     * 服务期截止日期
     */
    private String serviceEndDate;

    /**
     * 订单金额
     */
    private BigDecimal orderPrice;

    /**
     * 商品编号
     */
    private String commodityNo;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品价格
     */
    private BigDecimal commodityPrice;

    /**
     * 商品保险价格（不为空，则买了保险；为空，则没买保险）
     */
    private BigDecimal commodityInsurancePrice;

    /**
     * 一级类目id
     */
    private Integer firstCategoryId;

    /**
     * 一级类目名称
     */
    private String firstCategoryName;

    /**
     * 二级类目id
     */
    private Integer secCategoryId;

    /**
     * 二级类目名称
     */
    private String secCategoryName;

    /**
     * 报考省份（来自商品中心）
     */
    private Integer productCenterExamProvinceId;

    /**
     * 报考省份名称（来自商品中心）
     */
    private String productCenterExamProvinceName;

    /**
     * 服务期类型：0-固定服务期；1-弹性服务期
     */
    private Byte servicePeriodType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMorderNo() {
        return morderNo;
    }

    public void setMorderNo(String morderNo) {
        this.morderNo = morderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Long opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getConsultantAccount() {
        return consultantAccount;
    }

    public void setConsultantAccount(String consultantAccount) {
        this.consultantAccount = consultantAccount;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSaleDepartmentId() {
        return saleDepartmentId;
    }

    public void setSaleDepartmentId(Integer saleDepartmentId) {
        this.saleDepartmentId = saleDepartmentId;
    }

    public Integer getLegionId() {
        return legionId;
    }

    public void setLegionId(Integer legionId) {
        this.legionId = legionId;
    }

    public Integer getQuantumId() {
        return quantumId;
    }

    public void setQuantumId(Integer quantumId) {
        this.quantumId = quantumId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getFirstProjId() {
        return firstProjId;
    }

    public void setFirstProjId(Integer firstProjId) {
        this.firstProjId = firstProjId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public BigDecimal getClassPrice() {
        return classPrice;
    }

    public void setClassPrice(BigDecimal classPrice) {
        this.classPrice = classPrice;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Byte getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(Byte insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public BigDecimal getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(BigDecimal insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Boolean getIsThisWeek() {
        return isThisWeek;
    }

    public void setIsThisWeek(Boolean isThisWeek) {
        this.isThisWeek = isThisWeek;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDurationFirstTime() {
        return durationFirstTime;
    }

    public void setDurationFirstTime(Integer durationFirstTime) {
        this.durationFirstTime = durationFirstTime;
    }

    public Byte getIsHaveRelatedOrder() {
        return isHaveRelatedOrder;
    }

    public void setIsHaveRelatedOrder(Byte isHaveRelatedOrder) {
        this.isHaveRelatedOrder = isHaveRelatedOrder;
    }

    public String getRelatedOrderNo() {
        return relatedOrderNo;
    }

    public void setRelatedOrderNo(String relatedOrderNo) {
        this.relatedOrderNo = relatedOrderNo;
    }

    public Integer getExamProvinceId() {
        return examProvinceId;
    }

    public void setExamProvinceId(Integer examProvinceId) {
        this.examProvinceId = examProvinceId;
    }

    public Integer getServicePeriod() {
        return servicePeriod;
    }

    public void setServicePeriod(Integer servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    public String getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(String serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getCommodityNo() {
        return commodityNo;
    }

    public void setCommodityNo(String commodityNo) {
        this.commodityNo = commodityNo;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public BigDecimal getCommodityInsurancePrice() {
        return commodityInsurancePrice;
    }

    public void setCommodityInsurancePrice(BigDecimal commodityInsurancePrice) {
        this.commodityInsurancePrice = commodityInsurancePrice;
    }

    public Integer getFirstCategoryId() {
        return firstCategoryId;
    }

    public void setFirstCategoryId(Integer firstCategoryId) {
        this.firstCategoryId = firstCategoryId;
    }

    public String getFirstCategoryName() {
        return firstCategoryName;
    }

    public void setFirstCategoryName(String firstCategoryName) {
        this.firstCategoryName = firstCategoryName;
    }

    public Integer getSecCategoryId() {
        return secCategoryId;
    }

    public void setSecCategoryId(Integer secCategoryId) {
        this.secCategoryId = secCategoryId;
    }

    public String getSecCategoryName() {
        return secCategoryName;
    }

    public void setSecCategoryName(String secCategoryName) {
        this.secCategoryName = secCategoryName;
    }

    public Integer getProductCenterExamProvinceId() {
        return productCenterExamProvinceId;
    }

    public void setProductCenterExamProvinceId(Integer productCenterExamProvinceId) {
        this.productCenterExamProvinceId = productCenterExamProvinceId;
    }

    public String getProductCenterExamProvinceName() {
        return productCenterExamProvinceName;
    }

    public void setProductCenterExamProvinceName(String productCenterExamProvinceName) {
        this.productCenterExamProvinceName = productCenterExamProvinceName;
    }

    public Byte getServicePeriodType() {
        return servicePeriodType;
    }

    public void setServicePeriodType(Byte servicePeriodType) {
        this.servicePeriodType = servicePeriodType;
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DragOrder other = (DragOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMorderNo() == null ? other.getMorderNo() == null : this.getMorderNo().equals(other.getMorderNo()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getOpportunityId() == null ? other.getOpportunityId() == null : this.getOpportunityId().equals(other.getOpportunityId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getConsultantAccount() == null ? other.getConsultantAccount() == null : this.getConsultantAccount().equals(other.getConsultantAccount()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getSaleDepartmentId() == null ? other.getSaleDepartmentId() == null : this.getSaleDepartmentId().equals(other.getSaleDepartmentId()))
            && (this.getLegionId() == null ? other.getLegionId() == null : this.getLegionId().equals(other.getLegionId()))
            && (this.getQuantumId() == null ? other.getQuantumId() == null : this.getQuantumId().equals(other.getQuantumId()))
            && (this.getBusinessId() == null ? other.getBusinessId() == null : this.getBusinessId().equals(other.getBusinessId()))
            && (this.getFirstProjId() == null ? other.getFirstProjId() == null : this.getFirstProjId().equals(other.getFirstProjId()))
            && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
            && (this.getClassPrice() == null ? other.getClassPrice() == null : this.getClassPrice().equals(other.getClassPrice()))
            && (this.getProvinceId() == null ? other.getProvinceId() == null : this.getProvinceId().equals(other.getProvinceId()))
            && (this.getCityId() == null ? other.getCityId() == null : this.getCityId().equals(other.getCityId()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCouponCode() == null ? other.getCouponCode() == null : this.getCouponCode().equals(other.getCouponCode()))
            && (this.getCouponPrice() == null ? other.getCouponPrice() == null : this.getCouponPrice().equals(other.getCouponPrice()))
            && (this.getInsuranceFlag() == null ? other.getInsuranceFlag() == null : this.getInsuranceFlag().equals(other.getInsuranceFlag()))
            && (this.getInsurancePrice() == null ? other.getInsurancePrice() == null : this.getInsurancePrice().equals(other.getInsurancePrice()))
            && (this.getPaymentTime() == null ? other.getPaymentTime() == null : this.getPaymentTime().equals(other.getPaymentTime()))
            && (this.getIsThisWeek() == null ? other.getIsThisWeek() == null : this.getIsThisWeek().equals(other.getIsThisWeek()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getOperator() == null ? other.getOperator() == null : this.getOperator().equals(other.getOperator()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getDurationFirstTime() == null ? other.getDurationFirstTime() == null : this.getDurationFirstTime().equals(other.getDurationFirstTime()))
            && (this.getIsHaveRelatedOrder() == null ? other.getIsHaveRelatedOrder() == null : this.getIsHaveRelatedOrder().equals(other.getIsHaveRelatedOrder()))
            && (this.getRelatedOrderNo() == null ? other.getRelatedOrderNo() == null : this.getRelatedOrderNo().equals(other.getRelatedOrderNo()))
            && (this.getExamProvinceId() == null ? other.getExamProvinceId() == null : this.getExamProvinceId().equals(other.getExamProvinceId()))
            && (this.getServicePeriod() == null ? other.getServicePeriod() == null : this.getServicePeriod().equals(other.getServicePeriod()))
            && (this.getServiceEndDate() == null ? other.getServiceEndDate() == null : this.getServiceEndDate().equals(other.getServiceEndDate()))
            && (this.getOrderPrice() == null ? other.getOrderPrice() == null : this.getOrderPrice().equals(other.getOrderPrice()))
            && (this.getCommodityNo() == null ? other.getCommodityNo() == null : this.getCommodityNo().equals(other.getCommodityNo()))
            && (this.getCommodityName() == null ? other.getCommodityName() == null : this.getCommodityName().equals(other.getCommodityName()))
            && (this.getCommodityPrice() == null ? other.getCommodityPrice() == null : this.getCommodityPrice().equals(other.getCommodityPrice()))
            && (this.getCommodityInsurancePrice() == null ? other.getCommodityInsurancePrice() == null : this.getCommodityInsurancePrice().equals(other.getCommodityInsurancePrice()))
            && (this.getFirstCategoryId() == null ? other.getFirstCategoryId() == null : this.getFirstCategoryId().equals(other.getFirstCategoryId()))
            && (this.getFirstCategoryName() == null ? other.getFirstCategoryName() == null : this.getFirstCategoryName().equals(other.getFirstCategoryName()))
            && (this.getSecCategoryId() == null ? other.getSecCategoryId() == null : this.getSecCategoryId().equals(other.getSecCategoryId()))
            && (this.getSecCategoryName() == null ? other.getSecCategoryName() == null : this.getSecCategoryName().equals(other.getSecCategoryName()))
            && (this.getProductCenterExamProvinceId() == null ? other.getProductCenterExamProvinceId() == null : this.getProductCenterExamProvinceId().equals(other.getProductCenterExamProvinceId()))
            && (this.getProductCenterExamProvinceName() == null ? other.getProductCenterExamProvinceName() == null : this.getProductCenterExamProvinceName().equals(other.getProductCenterExamProvinceName()))
            && (this.getServicePeriodType() == null ? other.getServicePeriodType() == null : this.getServicePeriodType().equals(other.getServicePeriodType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMorderNo() == null) ? 0 : getMorderNo().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getOpportunityId() == null) ? 0 : getOpportunityId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getConsultantAccount() == null) ? 0 : getConsultantAccount().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getSaleDepartmentId() == null) ? 0 : getSaleDepartmentId().hashCode());
        result = prime * result + ((getLegionId() == null) ? 0 : getLegionId().hashCode());
        result = prime * result + ((getQuantumId() == null) ? 0 : getQuantumId().hashCode());
        result = prime * result + ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
        result = prime * result + ((getFirstProjId() == null) ? 0 : getFirstProjId().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassPrice() == null) ? 0 : getClassPrice().hashCode());
        result = prime * result + ((getProvinceId() == null) ? 0 : getProvinceId().hashCode());
        result = prime * result + ((getCityId() == null) ? 0 : getCityId().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCouponCode() == null) ? 0 : getCouponCode().hashCode());
        result = prime * result + ((getCouponPrice() == null) ? 0 : getCouponPrice().hashCode());
        result = prime * result + ((getInsuranceFlag() == null) ? 0 : getInsuranceFlag().hashCode());
        result = prime * result + ((getInsurancePrice() == null) ? 0 : getInsurancePrice().hashCode());
        result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
        result = prime * result + ((getIsThisWeek() == null) ? 0 : getIsThisWeek().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOperator() == null) ? 0 : getOperator().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getDurationFirstTime() == null) ? 0 : getDurationFirstTime().hashCode());
        result = prime * result + ((getIsHaveRelatedOrder() == null) ? 0 : getIsHaveRelatedOrder().hashCode());
        result = prime * result + ((getRelatedOrderNo() == null) ? 0 : getRelatedOrderNo().hashCode());
        result = prime * result + ((getExamProvinceId() == null) ? 0 : getExamProvinceId().hashCode());
        result = prime * result + ((getServicePeriod() == null) ? 0 : getServicePeriod().hashCode());
        result = prime * result + ((getServiceEndDate() == null) ? 0 : getServiceEndDate().hashCode());
        result = prime * result + ((getOrderPrice() == null) ? 0 : getOrderPrice().hashCode());
        result = prime * result + ((getCommodityNo() == null) ? 0 : getCommodityNo().hashCode());
        result = prime * result + ((getCommodityName() == null) ? 0 : getCommodityName().hashCode());
        result = prime * result + ((getCommodityPrice() == null) ? 0 : getCommodityPrice().hashCode());
        result = prime * result + ((getCommodityInsurancePrice() == null) ? 0 : getCommodityInsurancePrice().hashCode());
        result = prime * result + ((getFirstCategoryId() == null) ? 0 : getFirstCategoryId().hashCode());
        result = prime * result + ((getFirstCategoryName() == null) ? 0 : getFirstCategoryName().hashCode());
        result = prime * result + ((getSecCategoryId() == null) ? 0 : getSecCategoryId().hashCode());
        result = prime * result + ((getSecCategoryName() == null) ? 0 : getSecCategoryName().hashCode());
        result = prime * result + ((getProductCenterExamProvinceId() == null) ? 0 : getProductCenterExamProvinceId().hashCode());
        result = prime * result + ((getProductCenterExamProvinceName() == null) ? 0 : getProductCenterExamProvinceName().hashCode());
        result = prime * result + ((getServicePeriodType() == null) ? 0 : getServicePeriodType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", morderNo=").append(morderNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", opportunityId=").append(opportunityId);
        sb.append(", studentId=").append(studentId);
        sb.append(", consultantAccount=").append(consultantAccount);
        sb.append(", groupId=").append(groupId);
        sb.append(", saleDepartmentId=").append(saleDepartmentId);
        sb.append(", legionId=").append(legionId);
        sb.append(", quantumId=").append(quantumId);
        sb.append(", businessId=").append(businessId);
        sb.append(", firstProjId=").append(firstProjId);
        sb.append(", classId=").append(classId);
        sb.append(", classPrice=").append(classPrice);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", mobile=").append(mobile);
        sb.append(", state=").append(state);
        sb.append(", couponCode=").append(couponCode);
        sb.append(", couponPrice=").append(couponPrice);
        sb.append(", insuranceFlag=").append(insuranceFlag);
        sb.append(", insurancePrice=").append(insurancePrice);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", isThisWeek=").append(isThisWeek);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", operator=").append(operator);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", durationFirstTime=").append(durationFirstTime);
        sb.append(", isHaveRelatedOrder=").append(isHaveRelatedOrder);
        sb.append(", relatedOrderNo=").append(relatedOrderNo);
        sb.append(", examProvinceId=").append(examProvinceId);
        sb.append(", servicePeriod=").append(servicePeriod);
        sb.append(", serviceEndDate=").append(serviceEndDate);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", commodityNo=").append(commodityNo);
        sb.append(", commodityName=").append(commodityName);
        sb.append(", commodityPrice=").append(commodityPrice);
        sb.append(", commodityInsurancePrice=").append(commodityInsurancePrice);
        sb.append(", firstCategoryId=").append(firstCategoryId);
        sb.append(", firstCategoryName=").append(firstCategoryName);
        sb.append(", secCategoryId=").append(secCategoryId);
        sb.append(", secCategoryName=").append(secCategoryName);
        sb.append(", productCenterExamProvinceId=").append(productCenterExamProvinceId);
        sb.append(", productCenterExamProvinceName=").append(productCenterExamProvinceName);
        sb.append(", servicePeriodType=").append(servicePeriodType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}