package com.scm.services.impl;

import com.scm.helper.ResourceNotFoundExpection;
import com.scm.model.Contacts;
import com.scm.repo.ContactRepo;
import com.scm.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactServiceImp implements ContactService {
    @Autowired
    private ContactRepo repo;
    @Override
    public Contacts save(Contacts contacts) {
        String contactId= UUID.randomUUID().toString();
        contacts.setId(contactId);
        return repo.save(contacts);

    }

    @Override
    public Contacts update(Contacts contacts) {
//        Optional<Contacts> contacts1= repo.findById(contacts.getId()).orElseThrow(()->new ResourceNotFoundExpection("Contact not found with given "+contacts.getId()));

       return  null;
    }

    @Override
    public List<Contacts> getAll() {
        return repo.findAll();
    }

    @Override
    public Contacts getById(String id) {
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Contact not found with given "+id));
    }

    @Override
    public void delete(String id) {
        com.scm.model.Contacts contacts =repo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("Contact not found with given "+id));
        repo.delete(contacts);

    }

    @Override
    public List<Contacts> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contacts> getByUserId(String userId) {

        return repo.findByUserId(userId);
    }
}
