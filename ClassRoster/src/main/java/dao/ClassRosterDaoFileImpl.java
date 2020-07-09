package dao;

import dto.Student;

import java.io.*;
import java.util.*;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
    private Map<String, Student> students = new HashMap<>();
    private final String ROSTER_FILE;
    public static final String DELIMITER = "::";

    public ClassRosterDaoFileImpl(){
        ROSTER_FILE = "roster.txt";
    }

    public ClassRosterDaoFileImpl(String rosterTextFile){
        ROSTER_FILE = rosterTextFile;
    }

    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
            loadRoster();
            Student prevStudent = students.put(studentId, student);
            writeRoster();
            return prevStudent;
    }

    @Override
    public List<Student> getAllStudent() throws ClassRosterPersistenceException {
            loadRoster();
            return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
            loadRoster();
            return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
            loadRoster();
            Student removedStudent = students.remove(studentId);
            writeRoster();
            return removedStudent;
    }

    private Student unmarshallStudent(String studentAsText){
        String[] studentTokens = studentAsText.split(DELIMITER);
        String studentId = studentTokens[0];
        Student studentFromFile = new Student(studentId);

        studentFromFile.setFirstName(studentTokens[1]);
        studentFromFile.setLastName(studentTokens[2]);
        studentFromFile.setCohort(studentTokens[3]);

        return studentFromFile;
    }

    private void loadRoster() throws ClassRosterPersistenceException {
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch(FileNotFoundException ex){
            throw new ClassRosterPersistenceException("-_- Could not load roster data into memory.", ex);
        }

        String currentLine;
        Student currentStudent;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentStudent = unmarshallStudent(currentLine);
            students.put(currentStudent.getStudentID(), currentStudent);
        }
        sc.close();
    }

    private String marshallStudent(Student aStudent){
        String studentAsText = aStudent.getStudentID() + DELIMITER;

        studentAsText += aStudent.getFirstName() + DELIMITER;
        studentAsText += aStudent.getLastName() + DELIMITER;
        studentAsText += aStudent.getCohort() + DELIMITER;

        return studentAsText;
    }

    private void writeRoster() throws ClassRosterPersistenceException {
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch(IOException ex){
            throw new ClassRosterPersistenceException("Could not save student data.", ex);
        }

        String studentAsText;
        List<Student> studentList = new ArrayList(students.values());
        for(Student currentStudent : studentList){
            studentAsText = marshallStudent(currentStudent);
            out.println(studentAsText);
            out.flush();
        }
        out.close();
    }
}
