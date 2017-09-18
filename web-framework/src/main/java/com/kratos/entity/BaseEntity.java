package com.kratos.entity;

import com.kratos.common.CrudService;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable {
    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @ApiModelProperty(value = "主键", notes = "uuid自动生成，系统默认字段")
    private String id;
    @ApiModelProperty(value = "数据创建时间", notes = "自动生成，系统默认字段")
    private Long createdDate;
    @ApiModelProperty(value = "数据修改时间", notes = "自动生成，系统默认字段")
    private Long updatedDate;
    @ApiModelProperty(value = "是否逻辑删除", notes = "自动生成，系统默认字段")
    private Boolean logicallyDeleted = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean isLogicallyDeleted() {
        return logicallyDeleted;
    }

    public void setLogicallyDeleted(Boolean logicallyDeleted) {
        this.logicallyDeleted = logicallyDeleted;
    }
}
