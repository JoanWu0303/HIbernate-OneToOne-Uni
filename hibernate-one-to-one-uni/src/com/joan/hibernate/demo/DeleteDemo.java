package com.joan.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.joan.hibernate.entity.Instructor;
import com.joan.hibernate.entity.InstructorDetail;


public class DeleteDemo {

	public static void main(String[] args) {

		// create sessionFactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session currentSession = factory.getCurrentSession();

		try {
			// start a transaction
			currentSession.beginTransaction();

			// get instructor by primary key/id
			int theId = 1;
			Instructor tempInstructor = currentSession.get(Instructor.class, theId);
			System.out.println("Found instructor:" + tempInstructor);

			// delete the instructor
			if (tempInstructor != null) {
				System.out.println("Deleting: " + tempInstructor);

				// Because of CascadeType.ALL, this will ALSO DELETE the details object
				currentSession.delete(tempInstructor);
			}

			// commit transaction
			currentSession.getTransaction().commit();

			System.out.println("DONE!");
		} finally {
			factory.close();
		}

	}
}
