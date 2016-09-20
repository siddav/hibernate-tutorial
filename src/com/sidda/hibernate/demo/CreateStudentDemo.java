package com.sidda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class CreateStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
            .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            // use session object to save the object
            // create a student object
            Student tempStudent = new Student("pauline", "diesel", "paul@gmail.com");
            System.out.println("created student " + tempStudent);
            //start a transaction
            System.out.println("begin transaction");
            session.beginTransaction();
            //save student object
            System.out.println("saving student object");
            session.save(tempStudent);
            // commit transaction
            System.out.println("commit transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}