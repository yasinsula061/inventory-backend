package com.sula.inventory.spec;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sula.inventory.model.Role;
import com.sula.inventory.model.User;
import com.sula.inventory.model.UserRole;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserRoleSpec {

    public Specification<UserRole> findByAndCriteria(UserRole userRole) {
        return (Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userRole.getId() != null) {
                predicates.add(builder.equal(root.get("id"), userRole.getId()));
            }

            if (userRole.getUser() != null) {
                predicates.add(builder.equal(root.get("user"), userRole.getUser()));
            }

            if (userRole.getRole() != null) {
                predicates.add(builder.equal(root.get("role"), userRole.getRole()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<UserRole> findByOrCriteria(UserRole userRole) {
        return (Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userRole.getId() != null) {
                predicates.add(builder.equal(root.get("id"), userRole.getId()));
            }

            if (userRole.getUser() != null) {
                predicates.add(builder.equal(root.get("user"), userRole.getUser()));
            }

            if (userRole.getRole() != null) {
                predicates.add(builder.equal(root.get("role"), userRole.getRole()));
            }

            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<UserRole> findByUser(User user) {
        return (Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), user));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<UserRole> findByRole(Role role) {
        return (Root<UserRole> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("role"), role));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
