package com.kratos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kratos.common.utils.SpringUtils;
import com.kratos.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class BaseDTO<D extends BaseDTO, E extends BaseEntity> {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;
    @ApiModelProperty(value = "是否逻辑删除")
    private Boolean logicallyDeleted;
    @ApiModelProperty(value = "数据创建时间", notes = "自动生成，系统默认字段")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date createdDate;
    @ApiModelProperty(value = "数据修改时间", notes = "自动生成，系统默认字段")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updatedDate;
    @ApiModelProperty(required = true, value = "oauth client id")
    @JsonIgnore
    private String clientId;
    @ApiModelProperty(required = true, value = "创建人id")
    @JsonIgnore
    private String createUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public Boolean getLogicallyDeleted() {
        return logicallyDeleted;
    }

    public void setLogicallyDeleted(Boolean logicallyDeleted) {
        this.logicallyDeleted = logicallyDeleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Autowired
    protected DTOConverter<D, E> converter;

    @JsonIgnore
    public DTOConverter<D, E> getConverter() {
        if(converter == null) {
            BaseDTO baseDTO = SpringUtils.getBean(this.getClass());
            converter = baseDTO.converter;
        }
        return converter;
    }

    @SuppressWarnings("unchecked")
    public E convert() {
        return getConverter().convert((D) this);
    }

    public D convertFor(E e) {
        return getConverter().reverse().convert(e);
    }

    public List<E> convert(Iterable<D> dList) {
        return (List<E>) getConverter().convert(dList);
    }

    public List<D> convertFor(Iterable<E> eList) {
        return (List<D>) getConverter().reverse().convert(eList);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BaseDTO && this.id != null) {
            return this.id.equals(((BaseDTO) obj).getId());
        } else {
            return false;
        }
    }
}
