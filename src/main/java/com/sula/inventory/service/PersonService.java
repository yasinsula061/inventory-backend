package com.sula.inventory.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sula.inventory.dao.PersonDAO;
import com.sula.inventory.model.Person;
import com.sula.inventory.model.User;
import com.sula.inventory.spec.PersonSpec;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends BaseService {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    PersonSpec personSpec;

    @Autowired
    UserService userService;

    public Map<String, Object> addPerson(Person person) {
        Map<String, Object> result = new HashMap<>();
        List<Person> list = personDAO.findAll(personSpec.findByIdAndIdentificationNumberOrEmailOrPhone(person));
        if (list == null || list.size() < 1) {
            personDAO.saveAndFlush(person);
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    public Map<String, Object> editPerson(Person person) {
        Map<String, Object> result = new HashMap<>();
        Person oldPerson = getPerson(person.getId());
        List<Person> list = personDAO.findAll(personSpec.findByIdAndIdentificationNumberOrEmailOrPhone(person));
        if (oldPerson != null && list == null || list.size() < 1) {
            personDAO.saveAndFlush(person);
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    public Map<String, Object> deletePerson(Person person) {
        Map<String, Object> result = new HashMap<>();
        Person oldPerson = getPerson(person.getId());
        if (oldPerson != null) {
            User user = new User();
            user.setPerson(person);
            List<User> list = userService.getUsers(user);
            if (list == null || list.size() < 1) {
                personDAO.deleteById(person.getId());
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        } else {
            result.put("success", false);
        }
        return result;
    }

    public Person getPerson(Long personId) {
        return personDAO.findById(personId).get();
    }

    public Map<String, Object> getPersonList(Person person, Pageable paging) {
        Map<String, Object> result = new HashMap<>();
        List<Person> list = personDAO.findAll(personSpec.findByAndCriteria(person), paging).getContent();
        List<Person> allList = personDAO.findAll(personSpec.findByAndCriteria(person));
        result.put("data", list);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getPersonList(Person person) {
        Map<String, Object> result = new HashMap<>();
        List<Person> allList = personDAO.findAll(personSpec.findByAndCriteria(person));
        result.put("data", allList);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }

    public Map<String, Object> getPersonSumList() {
        Map<String, Object> result = new HashMap<>();
        List<Person> allList = personDAO.findAll();

        JSONArray jsonArr = new JSONArray();
        allList.stream().map((ndata) -> {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", ndata.getId());
            jsonObj.put("identificationNumber", ndata.getIdentificationNumber());
            jsonObj.put("name", ndata.getName());
            jsonObj.put("surname", ndata.getSurname());
            jsonObj.put("userIdnName", ndata.getIdentificationNumber() + ' ' + ndata.getName() + ' ' + ndata.getSurname());
            return jsonObj;
        }).forEachOrdered((jsonObj) -> {
            jsonArr.add(jsonObj);
        });

        result.put("data", jsonArr);
        result.put("success", true);
        result.put("total", allList.size());
        return result;
    }
}
