package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = sessionFactory.getCurrentSession();
		try {
			Instructor instructor = new Instructor("Taher", "Ali", "tali20@gmail.com");
			InstructorDetail instructorDetail = new InstructorDetail("http://www.gmail.com", "Codeing");
			// associate the objects
			instructor.setInstructorDetail(instructorDetail);
			// start a transaction
			session.beginTransaction();
			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + instructor);
			session.save(instructor);
			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
