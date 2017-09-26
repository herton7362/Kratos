package com.kratos.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.util.List;

@JsonFilter(TreeEntity.CUSTOMER_FILTER)
@MappedSuperclass
public abstract class TreeEntity<T extends TreeEntity> extends BaseEntity {
    public static final String CUSTOMER_FILTER = "CUSTOMER_FILTER";
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private T parent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.LAZY)
    @Where(clause="logically_deleted = 0")
    @OrderBy(clause="updated_date desc, order_number asc")
    private List<T> children;

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
