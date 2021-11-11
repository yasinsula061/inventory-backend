package com.sula.inventory.controller;

import java.text.ParseException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sula.inventory.config.ApplicationPathsConfig;
import com.sula.inventory.model.Person;
import com.sula.inventory.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(ApplicationPathsConfig.PersonCtrl.CTRL)
public class PersonController extends BaseController {

    @Autowired
    PersonService personService;

    @ResponseBody
    @RequestMapping(value = "/add.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.person.add')")
    public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Person person = new Person();

        if (request.getParameter("identificationNumber") != null) {
            person.setIdentificationNumber(request.getParameter("identificationNumber"));
        }
        if (request.getParameter("name") != null) {
            person.setName(request.getParameter("name"));
        }
        if (request.getParameter("surname") != null) {
            person.setSurname(request.getParameter("surname"));
        }
        if (request.getParameter("birthDate") != null && !request.getParameter("birthDate").equals("")) {
            try {
                person.setBirthDate(genericDateFormat.parse(request.getParameter("birthDate")));
            } catch (ParseException ex) {
                log.error(ex.toString());
            }
        }
        if (request.getParameter("email") != null) {
            person.setEmail(request.getParameter("email"));
        }
        if (request.getParameter("phone") != null) {
            person.setPhone(request.getParameter("phone"));
        }
        if (request.getParameter("language") != null) {
            person.setLanguage(request.getParameter("language"));
        }
        return personService.addPerson(person);
    }

    @ResponseBody
    //@PreAuthorize("hasAuthority('directory.person.edit')")
    @RequestMapping(value = "/edit.ajax", method = RequestMethod.GET)
    public Map<String, Object> edit(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Person person = new Person();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            person.setId(Long.parseLong(request.getParameter("id")));
        }
        if (request.getParameter("identificationNumber") != null) {
            person.setIdentificationNumber(request.getParameter("identificationNumber"));
        }
        if (request.getParameter("name") != null) {
            person.setName(request.getParameter("name"));
        }
        if (request.getParameter("surname") != null) {
            person.setSurname(request.getParameter("surname"));
        }
        if (request.getParameter("birthDate") != null && !request.getParameter("birthDate").equals("")) {
            try {
                person.setBirthDate(genericDateFormat.parse(request.getParameter("birthDate")));
            } catch (ParseException ex) {
                log.error(ex.toString());
            }
        }
        if (request.getParameter("email") != null) {
            person.setEmail(request.getParameter("email"));
        }
        if (request.getParameter("phone") != null) {
            person.setPhone(request.getParameter("phone"));
        }
        if (request.getParameter("language") != null) {
            person.setLanguage(request.getParameter("language"));
        }

        return personService.editPerson(person);
    }

    @ResponseBody
    @RequestMapping(value = "/delete.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.person.delete')")
    public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Person person = new Person();
        if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            person.setId(Long.parseLong(request.getParameter("id")));
        }
        return personService.deletePerson(person);
    }

    @ResponseBody
    @RequestMapping(value = "/list.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.person.list')")
    public Map<String, Object> list(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        Person person = new Person();
        if (request.getParameter("identificationNumber") != null) {
            person.setIdentificationNumber(request.getParameter("identificationNumber"));
        }
        if (request.getParameter("name") != null) {
            person.setName(request.getParameter("name"));
        }
        if (request.getParameter("surname") != null) {
            person.setSurname(request.getParameter("surname"));
        }
        if (request.getParameter("birthDate") != null && !request.getParameter("birthDate").equals("")) {
            try {
                person.setBirthDate(genericDateFormat.parse(request.getParameter("birthDate")));
            } catch (ParseException ex) {
                log.error(ex.toString());
            }
        }
        if (request.getParameter("email") != null) {
            person.setEmail(request.getParameter("email"));
        }
        if (request.getParameter("phone") != null) {
            person.setPhone(request.getParameter("phone"));
        }
        if (request.getParameter("language") != null) {
            person.setLanguage(request.getParameter("language"));
        }

        if (request.getParameter("pageable") != null && Boolean.parseBoolean(request.getParameter("pageable")) == true) {
            PageRequest pr = getPage(request.getParameter("page"), request.getParameter("limit"));
            return personService.getPersonList(person, pr);
        } else {
            return personService.getPersonList(person);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sumlist.ajax", method = RequestMethod.GET)
    //@PreAuthorize("hasAuthority('directory.person.list')")
    public Map<String, Object> sumlist(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        return personService.getPersonSumList();
    }
}
