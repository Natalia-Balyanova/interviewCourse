package src.balyanova.lesson5.db;

import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionPreparing.getSessionFactory();
        StudentDao studentDao = new StudentDao(sessionFactory);

        for (int i = 1; i <= 1000; i++) {
            int markRandom = (int) (Math.random() * 3 + 3);//выставляем студентам оценки в диапазоне 3-5

            Student student = new Student("Student # " + i, markRandom);
            studentDao.add(student);
        }

        System.out.println("Student with id 1 = " + studentDao.findById(1L));
        Student updatedStudent = new Student(1L, "Bob White",4);
        studentDao.update(updatedStudent);
        System.out.println("Student with id 1 = " + studentDao.findById(1L));
        System.out.println("***********************************************");

        System.out.println(studentDao.findAll());
        studentDao.delete(1L);
        System.out.println("***********************************************");
        System.out.println("Student with id 1 = " + studentDao.findById(1L));//Student with id 1 = null

        System.out.println("***********************************************");
        System.out.println("Student with id 1000 = " + studentDao.findById(1000L));//Student with id 1000 = Student{id=1000, name='Student # 1000', mark=5}

    }
}
