package dao;

import dto.Student;

import java.util.List;

public interface ClassRosterDao {

    Student addStudent(String studentId, Student student)
            throws ClassRosterPersistenceException;

    List<Student> getAllStudent()
            throws ClassRosterPersistenceException;

    Student getStudent(String studentId)
            throws ClassRosterPersistenceException;

    Student removeStudent(String studentId)
            throws ClassRosterPersistenceException;
}
