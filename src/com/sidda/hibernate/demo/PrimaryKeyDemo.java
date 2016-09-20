package com.sidda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
            .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            // use session object to save the object
            // create 3 student objects
            System.out.println("creating 3 students... ");
            Student tempStudent1 = new Student("john", "doe", "jdoe@gmail.com");
            Student tempStudent2 = new Student("mary", "public", "mpublic@gmail.com");
            Student tempStudent3 = new Student("bonita", "applebum", "balpabam@hotmail.com");
            //start a transaction
            System.out.println("begin transaction");
            session.beginTransaction();
            //save student object
            System.out.println("saving student objects");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            // commit transaction
            System.out.println("commit transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }

    }
}
