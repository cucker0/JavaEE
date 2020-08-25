package com.java.ref.bean;

public class Service {
    private Dao dao;

    public Service() {}

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void save() {
        System.out.println("Service save ops...");
        dao.update();
    }
}
