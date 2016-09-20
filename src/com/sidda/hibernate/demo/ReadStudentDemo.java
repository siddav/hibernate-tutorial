package com.sidda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class ReadStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
            .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            // use session object to save the object
            // create a student object
            Student tempStudent = new Student("daffy", "duck", "daffy@yahoo.com");
            System.out.println("created student " + tempStudent);
            //start a transaction
            System.out.println("begin transaction");
            session.beginTransaction();
            //save student object
            System.out.println("saving student object" + tempStudent);
            session.save(tempStudent);
            // commit transaction
            System.out.println("commit transaction");
            session.getTransaction().commit();

            //My new code
            // find out students Id: primary key
            System.out.println("generated Id: " + tempStudent.getId());

            // get a new session
            session = factory.getCurrentSession();
            //start a transaction
            session.beginTransaction();
            //retreive student based on primary key.
            System.out.println("getting student with Id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get complete.." + myStudent);
            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}