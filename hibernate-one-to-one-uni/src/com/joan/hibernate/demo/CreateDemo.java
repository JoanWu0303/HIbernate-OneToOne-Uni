package com.joan.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.joan.hibernate.entity.Instructor;
import com.joan.hibernate.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create sessionFactory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session currentSession = factory.getCurrentSession();

		try {
			// create the objects
			// Instructor tempInstructor = new Instructor("Joan", "Wu", "joanwu@gmail.com");
			// InstructorDetail tempInstructorDetail = new
			// InstructorDetail("http://wwww.joan.com/youtube", "Hope it success!!!");

			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
					"Luv 2 code!!!");

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			currentSession.beginTransaction();

			// save the instructor
			// Because of CascadeType.ALL, this will ALSO SAVE the details object
			System.out.println("Saving instructor: " + tempInstructor);
			currentSession.save(tempInstructor);

			// commit transaction
			currentSession.getTransaction().commit();

			System.out.println("DONE!");
		} finally {
			factory.close();
		}
	}
}
