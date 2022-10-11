
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Wajid_Latif
 */
class Database {

    University head;

    public String addUniversity(String uniName, String RectorName) {
        if (head == null) {
            head = new University(uniName, RectorName);
            return "Successful";
        }
        University temp = head;
        University uTemp = new University(uniName, RectorName);
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = uTemp;
        return "Successful";
    }

    public void addCollege(String uniName, String colName, String deanName) {
        University temp = findUni(uniName);
        if (temp.col == null) {
            temp.col = new College(colName, deanName);
        } else {
            College c = temp.col;
            while (c.next != null) {
                c = c.next;
            }
            c.next = new College(colName, deanName);
        }

    }

    public String addStudent(String uniName, String stuName, int id, String depName) {
        Department temp = findDepartment(uniName, depName);
        Student s1 = new Student(stuName, id);
        if (temp != null) {
            if (temp.st != null) {
                Student sTemp = temp.st;
                while (sTemp.next != null) {
                    sTemp = sTemp.next;
                }
                sTemp.next = s1;
            } else {
                temp.st = s1;
            }
        }
        return null;
    }

    public String deleteUniversity(String uniName) {
        if (head == null) {
            return "There is no University with that Name.";
        }
        if (head.uniName.equalsIgnoreCase(uniName)) {
            head = head.next;
            return "Success!";
        }
        University uTemp = head;
        while (uTemp.next != null) {
            if (uTemp.next.uniName.equalsIgnoreCase(uniName)) {
                uTemp.next = uTemp.next.next;
                return "Success!";
            }
            uTemp = uTemp.next;

        }
        return "No uni with that Name.";
    }

    public String deleteCourse(String uniName, int studentID, String courseName) {
        University temp = head;
        while (temp != null) {
            if (temp.uniName.equalsIgnoreCase(uniName)) {
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            return "No uni found.";
        }
        College cTemp = temp.col;
        if (cTemp == null) {
            return "No Col found.";
        }
        while (cTemp != null) {
            if (cTemp.dep != null) {
                Department dTemp = cTemp.dep;
                while (dTemp != null) {
                    if (dTemp.st != null) {
                        Student sTemp = dTemp.st;
                        while (sTemp != null) {
                            if (sTemp.studentID == studentID) {
                                if (sTemp.crs != null) {
                                    Course coTemp = sTemp.crs;
                                    if (sTemp.crs.courseName.equalsIgnoreCase(courseName)) {
                                        sTemp.crs = sTemp.crs.next;
                                        return "Successfull!";
                                    }
                                    while (coTemp.next != null) {
                                        if (coTemp.next.courseName.equalsIgnoreCase(courseName)) {
                                            coTemp.next = coTemp.next.next;
                                            return "Successfull!";
                                        }
                                        coTemp = coTemp.next;
                                    }
                                }
                            }
                            sTemp = sTemp.next;
                        }
                    }
                        dTemp = dTemp.next;
                }
                cTemp = cTemp.next;
            }
            return "Not Successfull.";

        }
        return "Not Successfull";
    }

    public void deleteCollege(String uniName, String colName) {
        University temp = findUni(uniName);
        if (temp == null) {
            return;
        }
        if (temp.col.colName.equalsIgnoreCase(colName)) {
            temp.col = temp.col.next;
            return;
        }
        College cTemp = temp.col;
        while (cTemp.next != null) {
            if (cTemp.next.colName.equalsIgnoreCase(colName)) {
                cTemp.next = cTemp.next.next;
                return;
            }
            cTemp = cTemp.next;
        }
    }

    public void deleteDepartment(String uniName, String depName) {
        University temp = findUni(uniName);
        if (temp == null) {
            return;
        }
        College cTemp = temp.col;
        if (cTemp == null) {
            return;
        }
        while (cTemp != null) {
            if (cTemp.dep != null) {
                Department dTemp = cTemp.dep;
                if (cTemp.dep.depName.equalsIgnoreCase(depName)) {
                    cTemp.dep = cTemp.dep.next;
                    return;
                }
                while (dTemp.next != null) {
                    if (dTemp.next.depName.equalsIgnoreCase(depName)) {
                        dTemp.next = dTemp.next.next;
                        return;
                    }
                    dTemp = dTemp.next;
                }
            }
            cTemp = cTemp.next;
        }
    }

    public String addDepartment(String uniName, String colName, String depName, String depChair) {
        if (head == null) {
            return "There is no university with that Name.";
        }
        Department newDep = new Department(depName, depChair);
        University htemp = head;
        while (htemp != null) {
            if (htemp.uniName.equalsIgnoreCase(uniName)) {
                break;
            }
            htemp = htemp.next;
        }
        if (htemp == null) {
            return "There is no University with that Name";
        }
        if (htemp.col == null) {
            return "There is no college with that Name";
        }
        College cTemp = htemp.col;
        while (cTemp != null) {
            if (cTemp.colName.equalsIgnoreCase(colName)) {
                break;
            }
            cTemp = cTemp.next;
        }
        if (cTemp == null) {
            return "There is no College with that Name";
        }
        if (cTemp.dep == null) {
            cTemp.dep = newDep;
            return "Success";
        }
        Department dTemp = cTemp.dep;
        while (dTemp.next != null) {
            dTemp = dTemp.next;
        }
        dTemp.next = newDep;
        return "Success";

    }

    public void copyCollege(String uniName, String colName, String uniName2) {
        College copy = findCollege(uniName, colName);
        University uTemp = findUni(uniName2);

        if (uTemp == null) {
            return;
        }
        College h = uTemp.col;
        if (h == null) {
            return;
        }
        while (h.next != null) {
            h = h.next;
        }
        h.next = new College(copy);

    }

    public String printgpa4() {
        if (head == null) {
            return "db is empty";
        }
        String gpaTemp = "";
        University temp = head;
        while (temp != null) {
            if (temp.col != null) {
                College cTemp = temp.col;
                while (cTemp != null) {
                    Department dTemp = cTemp.dep;
                    while (dTemp != null) {
                        Student sTemp = dTemp.st;
                        while (sTemp != null) {
                            if (sTemp.crs != null) {
                                if (sTemp.calculateGPA() == 4) {
                                    gpaTemp += sTemp.stuName + ", ";
                                }
                            }
                            sTemp = sTemp.next;
                        }
                        dTemp = dTemp.next;
                    }
                    cTemp = cTemp.next;
                }
            }
            temp = temp.next;
        }
        if (gpaTemp.length() == 0) {
            return "No Student has a GPA of 4.0";
        }
        return "These student(s) have a gpa of 4.0)-> " + gpaTemp + " ";
    }

    public void Universityfour(String uniName) {
        if (head == null) {
            return;
        }
        University temp = head;
        String s = "";
        while (temp != null) {
            if (temp.uniName.equalsIgnoreCase(uniName)) {
                if (temp.col != null) {
                    College cTemp = temp.col;
                    while (cTemp != null) {
                        if (cTemp.dep != null) {
                            Department dTemp = cTemp.dep;
                            while (dTemp != null) {
                                if (dTemp.st != null) {
                                    Student sTemp = dTemp.st;
                                    while (sTemp != null) {
                                        if (sTemp.calculateGPA() == 4) {
                                            s += sTemp.stuName +", ID:"+ sTemp.studentID + "  ";
                                        }
                                        sTemp = sTemp.next;
                                    }
                                }
                                dTemp = dTemp.next;
                            }
                        }
                        cTemp = cTemp.next;
                    }
                }
            }
            temp = temp.next;
        }
        System.out.println(s);
    }

    public void bestUniversity(String grade) {
        if (head == null) {
            return;
        }
        String bestUniversity = "No Student has this grade.";
        University temp = head;
        int numofA = 0;
        while (temp != null) {
            int tempN = 0;
            if (temp.col != null) {
                College cTemp = temp.col;
                while (cTemp != null) {
                    if (cTemp.dep != null) {
                        Department dTemp = cTemp.dep;
                        while (dTemp != null) {
                            if (dTemp.st != null) {
                                Student sTemp = dTemp.st;
                                while (sTemp != null) {
                                    if (sTemp.crs != null) {
                                        Course coTemp = sTemp.crs;
                                        while (coTemp != null) {
                                            if (coTemp.grade.equalsIgnoreCase(grade)) {
                                                tempN++;
                                            }
                                            if (tempN > numofA) {
                                                numofA = tempN;
                                                bestUniversity = dTemp.depName + ", " + temp.uniName;
                                            }
                                            coTemp = coTemp.next;
                                        }
                                    }
                                    sTemp = sTemp.next;
                                }
                            }
                            dTemp = dTemp.next;

                        }
                    }
                    cTemp = cTemp.next;
                }
            }
            temp = temp.next;
        }
        System.out.println(bestUniversity);
    }

    public void deleteDatabase() {
        head = null;
    }

    public void addCourse(String uniName, int id, String courseName, String grade, String semester) {
        University temp = findUni(uniName);
        if (temp == null) {
            return;
        }
        College cTemp = temp.col;
        while (cTemp != null) {
            Department dTemp = cTemp.dep;
            while (dTemp != null) {
                Student sTemp = dTemp.st;
                while (sTemp != null) {
                    if (sTemp.studentID == id) {
                        if (sTemp.crs == null) {
                            sTemp.crs = new Course(courseName, grade, semester);
                            return;
                        } else {
                            Course coTemp = sTemp.crs;
                            while (coTemp.next != null) {
                                coTemp = coTemp.next;
                            }
                            coTemp.next = new Course(courseName, grade, semester);
                        }
                    }
                    sTemp = sTemp.next;
                }
                dTemp = dTemp.next;
            }
            cTemp = cTemp.next;
        }

    }

    @Override
    public String toString() {
        if (head == null) {
            return "Database is Empty";
        }
        University temp = head;

        College cTemp = temp.col;
        String str = "---------------------------------\nDatabase:  ";
        int no = 0;
        while (temp != null) {
            if (no != 0) {
                str += "\n----------------Next University:-------------------------\n  ";
            }
            no++;
            str += "\nUniversity Name: " + temp.uniName + " Rector Name: " + temp.rectorName + " \n";

            cTemp = temp.col;

            while (cTemp != null) {
                str += "\n---------------------College Information----------------------------------\n";
                str += "College Name: " + cTemp.colName + "\nDean of College is: " + cTemp.deanName + "\n";
                Department dTemp = cTemp.dep;
                while (dTemp != null) {
                    str += "\n-----------------Department Info:-----------------------------\n";
                    str += "Department Name: " + dTemp.depName + " Chair of Department: " + dTemp.chairName + "\n";
                    Student sTemp = dTemp.st;
                    while (sTemp != null) {
                        str += "\n-----------------Student In Department-----------------------------\n";
                        str += "Student Name: " + sTemp.stuName + " Student ID: " + sTemp.studentID + " Student GPA: " + sTemp.calculateGPA();
                        Course coTemp = sTemp.crs;
                        int x = 0;
                        while (coTemp != null) {
                            if (x == 0) {
                                str += "\nCourses Completed: \n";
                                x++;
                            }
                            str += "Course Name: " + coTemp.courseName + ", Completed In Semester: " + coTemp.semester + " With Grade:" + coTemp.grade + "\n";
                            coTemp = coTemp.next;
                        }
                        str += "\n---------------------------------------------------\n";
                        sTemp = sTemp.next;
                    }
                    dTemp = dTemp.next;
                }
                cTemp = cTemp.next;
            }
            temp = temp.next;
        }
        return str;
    }

    void filldatabase() {
        this.addUniversity("PMU", "Dr.Essa");
        this.addCollege("PMU", "CCES", "Dr. Thanos");
        this.addCollege("PMU", "COE", "Dr. Shahubudin");
        this.addDepartment("PMU", "CCES", "IT", "Dr. Ghassen");
        this.addDepartment("PMU", "CCES", "CE", "Dr. Loay");
        this.addDepartment("PMU", "CCES", "CS", "Dr. Zikira");
        this.addDepartment("PMU", "COE", "Mechanical", "Dr. Ghazanfar");
        this.addStudent("PMU", "Tariq", 201700150, "IT");
        this.addStudent("PMU", "Ahmed", 201501110, "CS");
        this.addStudent("PMU", "Muhammad Wajid", 201601923, "CS");
        this.addStudent("PMU", "Hamza", 201645166, "Mechanical");
        this.addCourse("PMU", 201501110, "CSI", "A+", "Spring 2016");
        //this.addCourse("PMU", 201501110, "Data Structures", "D+", "Spring 2016");
        this.addCourse("PMU", 201601923, "CSI", "A+", "Spring 2016");
        this.addCourse("PMU", 201700150, "CSII", "B+", "Fall 2017");
        this.addCourse("PMU", 201601923, "CSII", "A+", "Fall 2017");
        this.addUniversity("KFUPM", "Dr XYZ");
        this.addCollege("KFUPM", "CCES", "Dr. PQR");
        this.addDepartment("KFUPM", "CCES", "IT", "Dr. iranoutofnames");
        this.addDepartment("KFUPM", "CCES", "CS", "Dr. What");
        this.addDepartment("KFUPM", "CCES", "CE", "Dr. Riyad");
        this.addStudent("KFUPM", "Tariq", 201700150, "IT");
        this.addStudent("KFUPM", "Zikiria", 201523033, "CS");
        this.addStudent("KFUPM", "Ahmad", 201659403, "CE");
        this.addCourse("KFUPM", 201700150, "CSI", "A+", "Spring 2016");
        this.addCourse("KFUPM", 201700150, "Data Structures", "A+", "Fall 2016");
    }

    String removeCourse(String uniName, int studentID, String courseName) {
        University temp = head;
        while (temp != null) {
            if (temp.uniName.equalsIgnoreCase(uniName)) {
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            return "No uni found.";
        }
        College cTemp = temp.col;
        if (cTemp == null) {
            return "No Col found.";
        }
        while (cTemp != null) {
            if (cTemp.dep != null) {
                Department dTemp = cTemp.dep;
                while (dTemp != null) {
                    if (dTemp.st != null) {
                        Student sTemp = dTemp.st;
                        while (sTemp != null) {
                            if (sTemp.crs.courseName.equalsIgnoreCase(courseName)) {
                                sTemp.crs = sTemp.crs.next;
                            }

                            sTemp = sTemp.next;
                        }

                    }
                    dTemp = dTemp.next;
                }
            }
            cTemp = cTemp.next;
        }
        return "Not Successfull.";
    }

    String removeStudent(String uniName, int studentID) {
        University temp = head;
        while (temp != null) {
            if (temp.uniName.equalsIgnoreCase(uniName)) {
                break;
            }
            temp = temp.next;
        }
        if (temp == null) {
            return "No uni found.";
        }
        College cTemp = temp.col;
        if (cTemp == null) {
            return "No Col found.";
        }
        while (cTemp != null) {
            if (cTemp.dep != null) {
                Department dTemp = cTemp.dep;
                while (dTemp != null) {
                    if (dTemp.st != null) {
                        Student sTemp = dTemp.st;
                        if (dTemp.st.studentID == studentID) {
                            dTemp.st = dTemp.st.next;
                        }
                        while (sTemp.next != null) {
                            if (sTemp.next.studentID == studentID) {
                                sTemp.next = sTemp.next.next;
                                return "success";
                            }
                            sTemp = sTemp.next;
                        }
                    }
                    dTemp = dTemp.next;
                }
            }
            cTemp = cTemp.next;
        }
        return "Not Successfull.";
    }

    private Department findDepartment(String uniName, String depName) {
        University temp = head;
        while (temp != null) {
            if (temp.uniName.equalsIgnoreCase(uniName)) {
                College cTemp = temp.col;
                while (cTemp != null) {
                    if (cTemp.dep != null) {
                        Department dTemp = cTemp.dep;
                        while (dTemp != null) {
                            if (dTemp.depName.equalsIgnoreCase(depName)) {
                                return dTemp;
                            }
                            dTemp = dTemp.next;
                        }
                    }
                    cTemp = cTemp.next;
                }
            }
            temp = temp.next;
        }
        return null;
    }

    private College findCollege(String uniName, String colName) {
        University temp = findUni(uniName);
        if (temp.col == null) {
            return null;
        } else {
            College cTemp = temp.col;
            while (cTemp != null) {
                if (cTemp.colName.equalsIgnoreCase(colName)) {
                    return cTemp;
                }
                cTemp = cTemp.next;
            }
        }
        return null;
    }

    private University findUni(String uniName) {
        University temp = head;
        while (temp != null) {
            if (temp.uniName.equals(uniName)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

}

class University {

    String uniName;
    String rectorName;
    University next;
    College col;

    public University(String uniName, String rectorName) {
        this.uniName = uniName;
        this.rectorName = rectorName;
    }
}

class College {

    String colName;
    String deanName;
    College next;
    Department dep;

    public College(College c) {
        colName = c.colName;
        deanName = c.deanName;
        next = null;
        Department h = c.dep;
        Department temp;
        while (h != null) {
            temp = new Department(h);
            temp.next = dep;
            dep = temp;
            h = h.next;
        }
    }

    public College(String colName, String deanName) {
        this.colName = colName;
        this.deanName = deanName;
    }

}

class Department {

    String depName;
    String chairName;
    Department next;
    Student st;
    int GPA;

    public Department(Department c) {
        depName = c.depName;
        chairName = c.chairName;
        next = null;
        Student h = c.st;
        while (h != null) {
            Student temp = new Student(h);
            temp.next = st;
            st = temp;
            h = h.next;
        }
    }

    public Department(String depName, String chairName) {
        this.depName = depName;
        this.chairName = chairName;
    }

}

class Student {

    public final String stuName;
    public final int studentID;
    public Student next;
    public Course crs;
    double gpa;

    public Student(String stuName, int studentID) {
        this.stuName = stuName;
        this.studentID = studentID;
    }

    public Student(Student h) {
        stuName = h.stuName;
        studentID = h.studentID;
        Course s = h.crs;
        while (h != null) {
            while (s != null) {
                Course temp = new Course(s);
                temp.next = crs;
                crs = temp;
                s = s.next;
            }
            h = h.next;
        }
    }

    public double calculateGPA() {
        if (crs == null) {
            return 0;
        }
        int i = 0;
        gpa = 0;
        Course temp = crs;
        while (temp != null) {
            gpa += temp.cGPA;
            i++;
            temp = temp.next;
        }
        double tGPA = gpa / i;
        return (tGPA);
    }
}

class Course {

    Course next;
    String courseName;
    String grade;
    double cGPA = 0;
    String semester;

    public Course(Course c) {
        courseName = c.courseName;
        cGPA = c.cGPA;
        semester = c.semester;
    }

    public Course(String courseName, String grade, String semester) {
        this.courseName = courseName;
        this.grade = grade;
        this.semester = semester;
        if ("A+".equalsIgnoreCase(grade)) {
            cGPA += 4;
        } else if ("A".equalsIgnoreCase(grade)) {
            cGPA += 3.75;
        } else if ("B+".equalsIgnoreCase(grade)) {
            cGPA += 3.5;
        } else if ("B".equalsIgnoreCase(grade)) {
            cGPA += 3.25;
        } else if ("C+".equalsIgnoreCase(grade)) {
            cGPA += 3.0;
        } else if ("C".equalsIgnoreCase(grade)) {
            cGPA += 2.75;
        } else if ("D+".equalsIgnoreCase(grade)) {
            cGPA += 2.5;
        } else if ("D".equalsIgnoreCase(grade)) {
            cGPA += 1.9;
        } else if ("F".equalsIgnoreCase(grade)) {
            cGPA += 0;
        }
    }
}

public class UniversityProject {

    public static void Intro() {
        Database db = new Database();
        Scanner in = new Scanner(System.in);

        int option = 0;
        String contin;
        while (true) {
            try {
                System.out.println("Please select any item from the following menu: \n"
                        + "1:Add a new university in the list \n"
                        + "2:Delete a university from the list \n"
                        + "3:Add a department in a college\n"
                        + "4:Delete a department from a college.\n"
                        + "5:Print The whole database\n"
                        + "6:Add a College to a University\n"
                        + "7:Print all students with a GPA of 4.0\n"
                        + "8:Copy College Information to another University\n"
                        + "9:Add a Student to a college\n"
                        + "10:Add a Course for a student\n"
                        + "11:Automatically fill the database\n"
                        + "12:Delete a specific Uni/Col/Dep/Stu/Course from the database\n"
                        + "13:Find University with the highest amount of A+'s\n"
                        + "14:Find Students with a GPA of 4.0 in a specific University\n"
                        + "0: Exit"
                );
                System.out.print("Enter Choose an option: ");
                option = in.nextInt();

                System.out.println("-------------------------------------------");
                if (option == 1) {
                    System.out.println("Please enter University Name:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter Rector Name:");
                    String recName = in.next();
                    recName += in.nextLine();
                    db.addUniversity(uniName, recName);
                } else if (option == 2) {
                    System.out.println("Please enter the name of the University that you want to "
                            + "delete:\n");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    db.deleteUniversity(uniName);
                    System.out.println("University Deleted");
                } else if (option == 3) {
                    System.out.println("Please enter Name of University:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter Name of College:");
                    String colName = in.next();
                    colName += in.nextLine();
                    System.out.println("Please enter Name of Department:");
                    String depName = in.next();
                    depName += in.nextLine();
                    System.out.println("Please enter Name of Chair:");
                    String chairName = in.next();
                    chairName += in.nextLine();
                    System.out.println(db.addDepartment(uniName, colName, depName, chairName));
                } else if (option == 5) {
                    System.out.println(db);
                } else if (option == 6) {
                    System.out.println("Please enter Name of University:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter Name of College:");
                    String colName = in.next();
                    colName += in.nextLine();
                    System.out.println("Please enter Name of Dean:");
                    String deanName = in.next();
                    deanName += in.nextLine();
                    db.addCollege(uniName, colName, deanName);
                } else if (option == 7) {
                    System.out.println(db.printgpa4());
                } else if (option == 8) {
                    System.out.println("Please enter Name of Univeristy you want to copy a college from:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter the name of the college you want to copy:");
                    String colName = in.next();
                    colName += in.nextLine();
                    System.out.println("Please enter the name of the University that you want to copy the "
                            + "college Information too:");
                    String uniName2 = in.next();
                    uniName2 += in.nextLine();
                    db.copyCollege(uniName, colName, uniName2);
                } else if (option == 9) {
                    System.out.println("Please enter Name of University:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter Student Name");
                    String stuName = in.next();
                    stuName += in.nextLine();
                    System.out.println("Please enter Student ID");
                    int studentID = in.nextInt();
                    System.out.println("Pleast enter Department Name");
                    String depName = in.next();
                    depName += in.nextLine();
                    db.addStudent(uniName, stuName, studentID, depName);
                } else if (option == 10) {
                    System.out.println("Please enter Name of University:");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    System.out.println("Please enter Student ID");
                    int stuID = in.nextInt();
                    System.out.println("Please Enter Course Name");
                    String courseName = in.next();
                    courseName += in.nextLine();
                    System.out.println("Please enter Course Grade");
                    String courseGrade = in.next();
                    System.out.println("Please enter Semester");
                    String semester = in.next();
                    db.addCourse(uniName, stuID, courseName, courseGrade, semester);
                } else if (option == 11) {
                    db.filldatabase();
                } else if (option == 12) {
                    System.out.println("What do you want to delete");
                    String deleteValue = in.next();
                    if (deleteValue.equalsIgnoreCase("University")) {
                        System.out.println("What is the Name of the University?");
                        String uniName = in.next();
                        uniName += in.nextLine();
                        db.deleteUniversity(uniName);
                    } else if (deleteValue.equalsIgnoreCase("College")) {
                        System.out.println("What is the Name of the University?");
                        String uniName = in.next();
                        uniName += in.nextLine();
                        System.out.println("What is the Name of the college?");
                        String colName = in.next();
                        colName += in.nextLine();
                        db.deleteCollege(uniName, colName);
                    } else if (deleteValue.equalsIgnoreCase("Student")) {
                        System.out.println("What is the name of the University?");
                        String uniName = in.next();
                        uniName += in.nextLine();
                        System.out.println("What is the ID Of the student?");
                        int studentID = in.nextInt();
                        db.removeStudent(uniName, studentID);
                    } else if (deleteValue.equalsIgnoreCase("Department")) {
                        System.out.println("What is the name of the University?");
                        String uniName = in.next();
                        uniName += in.nextLine();
                        System.out.println("What is the Name of the Department?");
                        String depName = in.next();
                        depName += in.nextLine();
                        db.deleteDepartment(uniName, depName);
                    } else if (deleteValue.equalsIgnoreCase("Course")) {
                        System.out.println("What is the name of the University?");
                        String uniName = in.next();
                        uniName += in.nextLine();
                        System.out.println("What is the ID of the student?");
                        int studentID = in.nextInt();
                        System.out.println("Please Enter Course Name");
                        String courseName = in.next();
                        courseName += in.nextLine();
                        System.out.println(db.deleteCourse(uniName, studentID, courseName));
                    }
                } else if (option == 13) {
                    System.out.println("Please enter grade you want to check:\n");
                    String grade = in.next();
                    db.bestUniversity(grade);
                } else if (option == 14) {
                    System.out.println("Please enter the name of the University that you want to "
                            + "find out about:\n");
                    String uniName = in.next();
                    uniName += in.nextLine();
                    db.Universityfour(uniName);

                } else if (option == 0) {
                    break;
                }
                System.out.println("\n-------------------------------------------");
            } catch (InputMismatchException ime) {
                in.nextLine();
                System.out.println("\n***Integer Expected, Please try again; Restarting.***\n");
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        Database db = new Database(); //this is the database in which the entire structure will go.
        Intro();
//      db.filldatabase();
        System.out.println("Thank you for using this program! :)");
    }
}

//updated 19:21, 07-Dec-2017
//updated 18:20, 27-Dec-2017
//updated 10:09, 28-Dec-2017