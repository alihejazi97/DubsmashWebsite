package com.ali.database.repository;

import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class customUserRepo {

    @PersistenceContext
    EntityManager manager;
}
