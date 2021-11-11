package com.sula.inventory.spec;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sula.inventory.model.Person;
import com.sula.inventory.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public class UserSpec {

    public Specification<User> findByAndCriteria(User user) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user.getId() != null) {
                predicates.add(builder.equal(root.get("id"), user.getId()));
            }

            if (user.getPerson() != null) {
                predicates.add(builder.equal(root.get("person"), user.getPerson()));
            }

            if (user.getUsername() != null) {
                predicates.add(builder.like(root.get("username"), '%' + user.getUsername() + '%'));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<User> findByOrCriteria(User user) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user.getId() != null) {
                predicates.add(builder.equal(root.get("id"), user.getId()));
            }

            if (user.getPerson() != null) {
                predicates.add(builder.equal(root.get("person"), user.getPerson()));
            }

            if (user.getUsername() != null) {
                predicates.add(builder.like(root.get("username"), '%' + user.getUsername() + '%'));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<User> findByIdAndPersonOrUser(User user) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (user.getPerson() != null) {
                predicates.add(builder.equal(root.get("person"), user.getPerson()));
            }

            if (user.getUsername() != null) {
                predicates.add(builder.equal(root.get("username"), user.getUsername()));
            }

            if (user.getId() != null) {
                return builder.and(builder.notEqual(root.get("id"), user.getId()), builder.or(predicates.toArray(new Predicate[0])));
            } else {
                return builder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public Specification<User> findByPerson(Person person) {
        return (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("person"), person));
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

