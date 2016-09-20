package com.sidda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
            // delete a object
            //            System.out.println("deleting the student with Id: " + studentId);
            //            Student myStudent = session.get(Student.class, studentId);
            //            System.out.println("retrieved student.. " + myStudent);
            //            session.delete(myStudent);
            //            System.out.println("delete student " + myStudent);

            // delete student with Id: 2 using alternate approach
            session.createQuery("delete from Student where id=2").executeUpdate();
            System.out.println("deleted student with id 2");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}