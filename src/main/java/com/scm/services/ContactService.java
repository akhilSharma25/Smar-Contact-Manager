package com.scm.services;

import com.scm.model.Contacts;

import java.util.List;

public interface ContactService {
    Contacts save(Contacts contacts);
    Contacts update(Contacts contacts);

    List<Contacts> getAll();

    Contacts getById(String id);

    void delete(String id);

    List<Contacts> search(String name,String email,String phoneNumber);

    List<Contacts> getByUserId(String userId);
}
