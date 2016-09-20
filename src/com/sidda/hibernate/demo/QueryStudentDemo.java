package com.sidda.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sidda.hibernate.demo.entity.Student;

public class QueryStudentDemo {

    public static void main(String[] args) {
        // create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
            .buildSessionFactory();
        // create session
        Session session = factory.getCurrentSession();
        try {
            //start a transaction
            System.out.println("begin transaction");
            session.beginTransaction();

            //query students
            List<Student> students = session.createQuery("from Student").getResultList();
            // display the students
            displayStudents(students);
            //query students: lastname is 'doe'
            students = session.createQuery("from Student s where s.lastName = 'doe'").getResultList();
            System.out.println("students whose lastname is doe");
            displayStudents(students);

            // query students lastName of doe or firstname paul
            students = session.createQuery("from Student s where s.lastName='doe' OR s.firstName='paul'")
                .getResultList();
            System.out.println("students whose lastname is doe or firstname as paul");
            displayStudents(students);

            // query students with email like gmail
            students = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
            System.out.println("students whose email ends with gmail.com");
            displayStudents(students);

            // commit transaction
            System.out.println("commit transaction");
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}