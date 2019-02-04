package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = sessionFactory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor from DB
			int id = 2;
			Instructor instructor = session.get(Instructor.class, 1);

			if (instructor != null) {
				List<Course> course = instructor.getCourses();
				for (Course course2 : course) {
					System.out.println("Courses addigned to Instructor are " + course2.getTitle());
				}
			}

			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
