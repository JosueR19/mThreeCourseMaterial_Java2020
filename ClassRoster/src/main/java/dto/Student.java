package dto;

import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private String cohort;

    public Student(String studentID){
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentID='" + studentID + '\'' +
                ", cohort='" + cohort + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null){
            return false;
        }
        if(obj.getClass() != getClass()){
            return false;
        }
        final Student other = (Student) obj;

        if (!Objects.equals(this.firstName, other.firstName)){
            return false;
        }
        if(!Objects.equals(this.lastName, other.lastName)){
            return false;
        }
        if(!Objects.equals(this.studentID, other.studentID)){
            return false;
        }
        if(!Objects.equals(this.cohort, other.cohort)){
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.firstName);
        hash = 31 * hash + Objects.hashCode(this.lastName);
        hash = 31 * hash + Objects.hashCode(this.studentID);
        hash = 31 * hash + Objects.hashCode(this.cohort);

        return hash;
    }
}
