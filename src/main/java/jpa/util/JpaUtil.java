package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static final String PERSISTENCE_UNIT_NAME = "SMS";

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManagerFactory getEntityManagerFactory() {

        return factory;
    }

}