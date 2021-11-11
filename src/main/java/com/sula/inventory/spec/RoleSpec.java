package com.sula.inventory.spec;

import com.sula.inventory.model.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleSpec {

    public Specification<Role> findByAndCriteria(Role role) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (role.getId() != null) {
                predicates.add(builder.equal(root.get("id"), role.getId()));
            }

            if (role.getName() != null) {
                predicates.add(builder.like(root.get("name"), '%' + role.getName() + '%'));
            }

            if (role.isActive() != true) {
                predicates.add(builder.equal(root.get("active"), role.isActive()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Role> findByOrCriteria(Role role) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (role.getId() != null) {
                predicates.add(builder.equal(root.get("id"), role.getId()));
            }

            if (role.getName() != null) {
                predicates.add(builder.like(root.get("name"), '%' + role.getName() + '%'));
            }

            if (role.isActive() != true) {
                predicates.add(builder.equal(root.get("active"), role.isActive()));
            }

            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Role> findByIdAndName(Role role) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (role.getName() != null) {
                predicates.add(builder.equal(root.get("name"), role.getName()));
            }
            if (role.getId() != null) {
                return builder.and(builder.notEqual(root.get("id"), role.getId()), builder.or(predicates.toArray(new Predicate[0])));
            } else {
                return builder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public Specification<Role> findByInCriteria(List<Long> roleIds, String roleName) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get("active"), true));
            if (roleName != null || roleName.length() > 0) {
                predicates.add(builder.like(root.get("name"), '%' + roleName + '%'));
            }

            if (roleIds != null && !roleIds.isEmpty()) {
                predicates.add(root.get("id").in(roleIds));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Role> findByNotInCriteria(List<Long> roleIds, String roleName) {
        return (Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(builder.equal(root.get("active"), true));
            if (roleName != null || roleName.length() > 0) {
                predicates.add(builder.like(root.get("name"), '%' + roleName + '%'));
            }

            if (roleIds != null && !roleIds.isEmpty()) {
                predicates.add(builder.not(root.get("id").in(roleIds)));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}