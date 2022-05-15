package src.balyanova.lesson5.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionPreparing {
//4. Создать класс со статическим методом, который возвращает объект SessionFactory.
    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}
