package com.java.spring.struts2.action;

import com.java.spring.struts2.service.PersonService;

public class PersonAction {
    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String execute() {
        System.out.println("PersonAction.execute()... ");
        return "success";
    }
}
