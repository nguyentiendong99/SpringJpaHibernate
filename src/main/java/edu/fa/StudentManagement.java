package edu.fa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fa.model.Student;
import edu.fa.service.StudentService;

public class StudentManagement {

	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("context.xml");
//		StudentHibernateDao dao = context.getBean("studentHibernateDao",StudentHibernateDao.class);
//		dao.save(new Student("nguyen tien dong","ha noi"));
		StudentService service = context.getBean("StudentService",StudentService.class);
		service.save(new Student("dong","my duc"));
		service.test();
	}
}
 