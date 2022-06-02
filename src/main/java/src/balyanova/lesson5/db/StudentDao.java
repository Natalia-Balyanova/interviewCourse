package src.balyanova.lesson5.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentDao {
    //5. Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи по ID и всех записей.
    SessionFactory sessionFactory;

    public StudentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.delete(student);
            session.getTransaction().commit();
        }
    }

    public void update(Student student) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Student updStudent = session.get(Student.class, student.getId());
            updStudent.setName(student.getName());
            updStudent.setMark(student.getMark());
            session.getTransaction().commit();
        }
    }

    public Student findById(Long id) {
        Student student;
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            student = session.get(Student.class, id);
            session.getTransaction().commit();
        }
        return student;
    }

    public List<Student> findAll() {
        List<Student> students;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            students = session.createQuery("from Student").getResultList();//идея ругается на такой запрос, но метод отрабатывает
            session.getTransaction().commit();
        }
        return students;
    }
}
