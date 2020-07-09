package controller;

import dao.ClassRosterPersistenceException;
import dto.Student;
import service.ClassRosterDataValidationException;
import service.ClassRosterDuplicateIdException;
import service.ClassRosterServiceLayer;
import ui.ClassRosterView;

import java.util.List;

public class ClassRosterController {

    private ClassRosterView view;
    private ClassRosterServiceLayer service;

    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view){
        this.service = service;
        this.view = view;
    }

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try{
            while(keepGoing){

                menuSelection = getMenuSelection();

                switch(menuSelection){
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch(ClassRosterPersistenceException ex){
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do{
            Student newStudent = view.getNewStudentInfo();
            try{
                service.createStudent(newStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            }catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException ex){
                hasErrors = true;
                view.displayErrorMessage(ex.getMessage());
            }
        }while(hasErrors);
    }

    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }

    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }

    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = service.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }


}
