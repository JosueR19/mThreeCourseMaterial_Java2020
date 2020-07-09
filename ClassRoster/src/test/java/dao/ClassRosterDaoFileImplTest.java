package dao;

import dto.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassRosterDaoFileImplTest {

    ClassRosterDao testDao;

    public ClassRosterDaoFileImplTest(){
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testroster.txt";
        new FileWriter(testFile);
        testDao = new ClassRosterDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetStudent() throws Exception{
        String studentID = "0001";
        Student student = new Student(studentID);
        student.setFirstName("Ada");
        student.setLastName("Lovelace");
        student.setCohort("Java-May-1845");

        testDao.addStudent(studentID, student);
        Student retrievedStudent = testDao.getStudent(studentID);

        assertEquals(student.getStudentID(), retrievedStudent.getStudentID(), "Checking student ID.");
        assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "Checking student first name.");
        assertEquals(student.getLastName(), retrievedStudent.getLastName(), "Checking student last name.");
        assertEquals(student.getCohort(), retrievedStudent.getCohort(), "Checking student cohort.");
    }

    public void testAddGetAllStudents() throws Exception{
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        testDao.addStudent(firstStudent.getStudentID(), firstStudent);
        testDao.addStudent(secondStudent.getStudentID(), secondStudent);

        List<Student> allStudents = testDao.getAllStudent();

        assertNotNull(allStudents, "The list of students must not be null.");
        assertEquals(2, allStudents.size(), "List of students should have 2 students.");
        assertTrue(testDao.getAllStudent().contains(firstStudent));
        assertTrue(testDao.getAllStudent().contains(secondStudent));
    }

    public void testRemoveStudent() throws Exception{
        Student firstStudent = new Student("0001");
        firstStudent.setFirstName("Ada");
        firstStudent.setLastName("Lovelace");
        firstStudent.setCohort("Java-May-1845");

        Student secondStudent = new Student("0002");
        secondStudent.setFirstName("Charles");
        secondStudent.setLastName("Babbage");
        secondStudent.setCohort(".NET-May-1845");

        testDao.addStudent(firstStudent.getStudentID(), firstStudent);
        testDao.addStudent(secondStudent.getStudentID(), secondStudent);

        Student removedStudent = testDao.removeStudent(firstStudent.getStudentID());

        assertEquals(removedStudent, firstStudent, "The removed student should be Ada.");

        List<Student> allStudents = testDao.getAllStudent();

        assertNotNull(allStudents, "All students list should be not null.");
        assertEquals(1, allStudents.size(), "All students should only have 1 student.");

        assertFalse(allStudents.contains(firstStudent), "All students should NOT include Ada.");
        assertTrue(allStudents.contains(secondStudent), "All students hsould not include Charles.");

        removedStudent = testDao.removeStudent(secondStudent.getStudentID());
        assertEquals(removedStudent, secondStudent, "The removed student should be Charles.");

        allStudents = testDao.getAllStudent();

        assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty.");

        Student retrievedStudent = testDao.getStudent(firstStudent.getStudentID());
        assertNull(retrievedStudent, "Ada was removed, should be null.");

        retrievedStudent = testDao.getStudent(secondStudent.getStudentID());
        assertNull(retrievedStudent, "Charles was removed, should be null.");
    }
}