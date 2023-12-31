package com.hrm.hrm.Business_Logic_Layer.Specification;

import com.hrm.hrm.entity.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class UserSpecification implements Specification<User> {

    private String field;
    private String operator;
    private Object value;

    public UserSpecification(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(operator.equalsIgnoreCase("%LIKE%")){
            return criteriaBuilder.like(root.get(field),"%"+value.toString()+"%");
        }
        else if(operator.equalsIgnoreCase("=")){
            return criteriaBuilder.equal(root.get(field),value.toString());
        }
        else if(operator.equalsIgnoreCase(">=")){
            if (value instanceof Date) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Date) value);
        }}
        else if(operator.equalsIgnoreCase("<=")){
            if (value instanceof Date) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(field), (Date) value);
        }}
        return null;
    }
}
