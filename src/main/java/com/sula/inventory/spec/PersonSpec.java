package com.sula.inventory.spec;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sula.inventory.model.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PersonSpec {

    public Specification<Person> findByAndCriteria(Person person) {
        return (Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (person.getId() != null) {
                predicates.add(builder.equal(root.get("id"), person.getId()));
            }

            if (person.getIdentificationNumber() != null) {
                predicates.add(builder.like(root.get("identificationNumber"), '%' + person.getIdentificationNumber() + '%'));
            }

            if (person.getName() != null) {
                predicates.add(builder.like(root.get("name"), '%' + person.getName() + '%'));
            }

            if (person.getSurname() != null) {
                predicates.add(builder.like(root.get("surname"), '%' + person.getSurname() + '%'));
            }

            if (person.getBirthDate() != null) {
                predicates.add(builder.equal(root.get("birthDate"), person.getBirthDate()));
            }

            if (person.getEmail() != null) {
                predicates.add(builder.like(root.get("email"), '%' + person.getEmail() + '%'));
            }

            if (person.getPhone() != null) {
                predicates.add(builder.like(root.get("phone"), '%' + person.getPhone() + '%'));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Person> findByOrCriteria(Person person) {
        return (Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (person.getId() != null) {
                predicates.add(builder.equal(root.get("id"), person.getId()));
            }

            if (person.getIdentificationNumber() != null) {
                predicates.add(builder.like(root.get("identificationNumber"), '%' + person.getIdentificationNumber() + '%'));
            }

            if (person.getName() != null) {
                predicates.add(builder.like(root.get("name"), '%' + person.getName() + '%'));
            }

            if (person.getSurname() != null) {
                predicates.add(builder.like(root.get("surname"), '%' + person.getSurname() + '%'));
            }

            if (person.getBirthDate() != null) {
                predicates.add(builder.equal(root.get("birthDate"), person.getBirthDate()));
            }

            if (person.getEmail() != null) {
                predicates.add(builder.like(root.get("email"), '%' + person.getEmail() + '%'));
            }

            if (person.getPhone() != null) {
                predicates.add(builder.like(root.get("phone"), '%' + person.getPhone() + '%'));
            }

            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public Specification<Person> findByIdAndIdentificationNumberOrEmailOrPhone(Person person) {
        return (Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (person.getIdentificationNumber() != null) {
                predicates.add(builder.equal(root.get("identificationNumber"), person.getIdentificationNumber()));
            }
            if (person.getEmail() != null) {
                predicates.add(builder.like(root.get("email"), '%' + person.getEmail() + '%'));
            }

            if (person.getPhone() != null) {
                predicates.add(builder.like(root.get("phone"), '%' + person.getPhone() + '%'));
            }
            if (person.getId() != null) {
                return builder.and(builder.notEqual(root.get("id"), person.getId()), builder.or(predicates.toArray(new Predicate[0])));
            } else {
                return builder.or(predicates.toArray(new Predicate[0]));
            }
        };
    }
}

