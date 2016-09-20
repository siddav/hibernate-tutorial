package com.sidda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
            .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            int studentId = 1;
            //start a transaction
            System.out.println("begin transaction");
            session.beginTransaction();

            // retrieve a student
            System.out.println("getting student with Id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            // update the field of student
            myStudent.setFirstName("scobby");
            // commit transaction
            System.out.println("commit transaction");
            session.getTransaction().commit();

            // New Code
            session = factory.getCurrentSession();
            session.beginTransaction();
            // update email for all students
            session.createQuery("update Student s SET email='foo@gmail.com'").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}