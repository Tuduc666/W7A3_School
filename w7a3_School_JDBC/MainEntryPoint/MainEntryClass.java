package w7a3_School_JDBC.MainEntryPoint;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import w7a3_School_JDBC.CustomExceptions.StudentRegistrationException;
import w7a3_School_JDBC.DAO.AttendingDAO;
import w7a3_School_JDBC.DAO.CourseDAO;
import w7a3_School_JDBC.DAO.InstructorDAO;
import w7a3_School_JDBC.DAO.StudentDAO;
import w7a3_School_JDBC.DAO.TeachingDAO;
import w7a3_School_JDBC.Models.Attending;
import w7a3_School_JDBC.Models.Course;
import w7a3_School_JDBC.Models.Instructor;
import w7a3_School_JDBC.Models.Student;
import w7a3_School_JDBC.Models.Teaching;



public class MainEntryClass
{
	// Session ONE
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, StudentRegistrationException {
      boolean quit = false;
      Scanner reader = new Scanner(System.in);
      MainEntryClass mainObj = new MainEntryClass();
      int InsOrStu = -1;
      Instructor ins = null;  InstructorDAO insDAO = null;   String insROLE = "";
      
      
      Student stu = null;     StudentDAO stuDAO = null;
      
  //    Course co = null;       
      CourseDAO coDAO = null;
      
  //    Attending att = null;   
      AttendingDAO attDAO = null;
      
  //    Teaching tea = null;    
      TeachingDAO teaDAO = null;
      
      String email = "";
      String password = "";
      while(!quit) {
          System.out.println("Are you a(n)\n1. Instructor \n2. Student \n3. quit \nPlease, enter 1, 2 or 3 \n");
          InsOrStu = reader.nextInt();
          if(InsOrStu == 1) {
              boolean logout = false;
              while(!logout) {
                  System.out.println("Enter Your Email:\n");
                  email = reader.next();
                  System.out.println("Enter Your Password:\n");
                  password = reader.next();
                  insDAO = new InstructorDAO();
//                  ins = insDAO.getInstructoByEmail(email);
                  ins = insDAO.getInstructorByEmail(email);                 
                  
                  insROLE = insDAO.validateUser(ins, password);
                  
                  
                  if("Admin".equals(insROLE)) {
                      teaDAO = new TeachingDAO();
                      mainObj.allCoursesWithInstructor();
                      String out = "-1";
                      while(!out.equals("2")) {
                          System.out.printf("\n\n");
                          System.out.println("1. Assign Instructor to Course");
                          System.out.println("2. Logout");
                          out = reader.next();  
                          
                          if(out.equals("1")) {
                              int instructor_id = -1;
                              int course_id = -1;
                              mainObj.allIntructors();
                              System.out.println("\nWhat Instructor?\n");
                              instructor_id = reader.nextInt();
                              mainObj.allCourse();
                              System.out.println("\nWhich Course?\n");
                              course_id = reader.nextInt();
                              int assignId =  teaDAO.assignInstructorToCourse(course_id, instructor_id);
                              if(assignId != -1) {
                                  System.out.println("\n -->Instructor Assigned<--\nNew Record Id: "+ assignId+"\n");
                              }
                              mainObj.allCoursesWithInstructor();
                          }
                      }
                      System.out.printf("\n\n");
                      logout = true;
                  }else if("Instructor".equals(insROLE)) {
                      coDAO = new CourseDAO();
                    //  List<Course> coList = coDAO.getCourseByInstructor(ins.getInstructor_id());
                      List<Course> coList = coDAO.getCourseByInstructor(ins.getIntructor_id());
                      System.out.printf("COURSE NAME \t COURSE MINIMUN GPA\n");
                      for(Course insCO : coList) {
                    //      System.out.printf("%s \t\t %s\n", insCO.getCourse_name(), insCO.getMinimun_gpa());
                    	    System.out.printf("%s \t\t %s\n", insCO.getCourse_name(), insCO.getMinimum_gpa());
                      }
                      String out = "-1";
                      while(!out.equals("1")) {
                          System.out.println("1. Logout");
                          out = reader.next();  
                      }
                      ins = null;
                      logout = true;
                  }else if("Wrong Credentials".equals(insROLE)) {
                      System.out.println(insROLE);
                      continue;
                  }
              
              }
              
          }else if(InsOrStu == 2) {
              stuDAO = new StudentDAO();
              attDAO = new AttendingDAO();
              boolean logout = false;
              while(!logout) {
                  System.out.println("Enter Your Email:\n");
                  email = reader.next();
                  System.out.println("Enter Your Password:\n");
                  password = reader.next();
                  
                  List<Attending> attList = null;
                  stu = stuDAO.getStudentByEmail(email);
                  
                  if(stu != null && stu.getStudent_role() == -1 && stuDAO.validateUser(stu.getPass(), password)) {
                      
                      String classesTo = "";
                      while(!classesTo.equals("2")) {
                          attList = attDAO.getStudentCourse(stu.getStudent_id());
                          mainObj.StudentCourses(attList);
                          System.out.printf("\n1. Register to Class");
                          System.out.printf("\n2. Logout\n");
                          classesTo = reader.next();
                          if(classesTo.equals("1")) {
                              coDAO = new CourseDAO();
                              attDAO = new AttendingDAO();
                              List<Course> coList = coDAO.getAllCourses();
                              mainObj.allCourses(coList);
                              System.out.println("\nWhich Course?\n");
                              int course_idForStudent = reader.nextInt();
                              attDAO.registerStudentToCourse(stu, coList.get(course_idForStudent - 1));
                              classesTo = "";
                          }
                      }
                      logout = true;
                  }else {
                      System.out.printf("\nWrong Credentials\n");
                      continue;
                  }
              }
              
          }else if(InsOrStu == 3) {
              quit = true;
          }
      }
	}

	 public void allIntructors() throws ClassNotFoundException, IOException, SQLException {
	        InstructorDAO insDAO = new InstructorDAO();
	        
	        List<Instructor> allIns = insDAO.getAllInstructors();
	        System.out.printf("\nInstructors:\n======================================================================\n");
	        System.out.printf("%-3s INTRUCTOR NAME \t INSTRUCTOR EMAIL \t INSTRUCTOR SPECIALITY\n","ID");
	        
	        for(Instructor insAGN : allIns) {
	         //   System.out.printf("%-3s %-20s %-23s %s\n",insAGN.getInstructor_id(),  insAGN.getFull_name(), insAGN.getEmail(), insAGN.getSpeciality());
	            System.out.printf("%-3s %-20s %-23s %s\n",insAGN.getIntructor_id(),  insAGN.getFull_name(), insAGN.getEmail(), insAGN.getSpeciality());
	        }
	        
	    }
	    
	    public void allCourse() throws ClassNotFoundException, IOException, SQLException {
	        CourseDAO coDAO = new CourseDAO();
	        
	        List<Course> allCo = coDAO.getAllCourses();
	        System.out.printf("\nCourses:\n===============================================\n");
	        System.out.printf("%-3s COURSE NAME \t MINIMUN GPA\n", "ID");
	        
	        for(Course coAGN : allCo) {
//	            System.out.printf("%-3s %-20s %s\n",coAGN.getCourse_id(),  coAGN.getCourse_name(), coAGN.getMinimun_gpa());
	            System.out.printf("%-3s %-20s %s\n",coAGN.getCourse_id(),  coAGN.getCourse_name(), coAGN.getMinimum_gpa());
	        }
	        
	    }
	    
	    public void allCoursesWithInstructor() throws IOException, SQLException {
	        TeachingDAO teaDAO = new TeachingDAO();
	        List<Teaching> teaList = teaDAO.getIntructorsCourses();
	        System.out.printf("COURSE NAME \t COURSE MINIMUN GPA \t INTRUCTOR NAME \t INSTRUCTOR EMAIL\n\n");
	        for(Teaching teaAGN : teaList) {
	    //        System.out.printf("%-16s %-23s %-23s %s\n", teaAGN.getCourse_name(), teaAGN.getMinimun_gpa(), 
	          	System.out.printf("%-16s %-23s %-23s %s\n", teaAGN.getCourse_name(), teaAGN.getMinimum_gpa(), 
	                    teaAGN.getFull_name(), teaAGN.getEmail());
	        }
	    }
	    
	    public void StudentCourses(List<Attending> attList) {
	        int counter = 1;
	        System.out.printf("\nMy Classes:\n");
	        System.out.printf("%-3s COURSE NAME \t INTRUCTOR NAME \t INSTRUCTOR EMAIL\n", "#");
	        for(Attending att : attList) {
	            System.out.printf("%-3s %-20s %-23s %s\n", counter, att.getCourse_name(), att.getFull_name(), att.getEmail());
	            counter++;
	            
	        }
	    }
	    
	    public void allCourses(List<Course> coList) {
	        int counter = 1;
	        System.out.printf("\nAll Courses:\n");
	        System.out.printf("%-3s COURSE NAME \t MINIMUN GPA\n", "ID");
	        for(Course co : coList) {
	       //     System.out.printf("%-3s %-20s %s\n", counter, co.getCourse_name(), co.getMinimun_gpa());
	        	  System.out.printf("%-3s %-20s %s\n", counter, co.getCourse_name(), co.getMinimum_gpa());
	            counter++;
	        }
	    }
	    
//	TEST #1 - Regular Instructor - lance@gmail.com, 555	  ==================================================================================  
//  TEST #1   Results...
//            COURSE NAME 	 COURSE MINIMUN GPA
//	          English 		 3.1
//	  		    1. Logout	
//	    
//	TEST #2 - Admin Instructor - mark@gmail.com, 666	  ================================================================================== 
//  TEST #2   Results...
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    1
//	    Enter Your Email:
//
//	    mark@gmail.com
//	    Enter Your Password:
//
//	    666
//	    COURSE NAME 	 COURSE MINIMUN GPA 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//
//	    Math             3.1                     Luke                    luke@gmail.com
//	    English          3.1                     lance                   lance@gmail.com
//	    Science          3.2                     mark                    mark@gmail.com
//
//
//	    1. Assign Instructor to Course
//	    2. Logout
//	    1
//
//	    Instructors:
//	    ======================================================================
//	    ID  INTRUCTOR NAME 	 INSTRUCTOR EMAIL 	 INSTRUCTOR SPECIALITY
//	    1   Luke                 luke@gmail.com          Mathemathician
//	    2   lance                lance@gmail.com         scientis
//	    3   mark                 mark@gmail.com          important
//
//	    What Instructor?
//
//	    1
//
//	    Courses:
//	    ===============================================
//	    ID  COURSE NAME 	 MINIMUN GPA
//	    1   Math                 3.1
//	    2   Science              3.2
//	    3   English              3.1
//	    4   GYM                  2.8
//
//	    Which Course?
//
//	    2
//
//	     -->Instructor Assigned<--
//	    New Record Id: 1
//
//	    COURSE NAME 	 COURSE MINIMUN GPA 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//
//	    Math             3.1                     Luke                    luke@gmail.com
//	    Science          3.2                     Luke                    luke@gmail.com
//	    English          3.1                     lance                   lance@gmail.com
//	    Science          3.2                     mark                    mark@gmail.com
//
//
//	    1. Assign Instructor to Course
//	    2. Logout
//	    2
//
//  	TEST #3 - Student - J@gmail.com, 333	    =========================================================================================
//		TEST #3   Results...	    
//	    
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    2
//	    Enter Your Email:
//
//	    J@gmail.com
//	    Enter Your Password:
//
//	    333
//
//	    My Classes:
//	    #   COURSE NAME 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//	    1   Math                 Luke                    luke@gmail.com
//	    2   Science              mark                    mark@gmail.com
//	    3   Science              Luke                    luke@gmail.com
//
//	    1. Register to Class
//	    2. Logout
//	    1
//
//	    All Courses:
//	    ID  COURSE NAME 	 MINIMUN GPA
//	    1   Math                 3.1
//	    2   Science              3.2
//	    3   English              3.1
//	    4   GYM                  2.8
//
//	    Which Course?
//
//	    3
//
//	    My Classes:
//	    #   COURSE NAME 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//	    1   Math                 Luke                    luke@gmail.com
//	    2   Science              mark                    mark@gmail.com
//	    3   English              lance                   lance@gmail.com
//	    4   Science              Luke                    luke@gmail.com
//
//	    1. Register to Class
//	    2. Logout
//	    
//		TEST #4 - Regular Instructor with invalid password - lance@gmail.com, 555	  =================================================================  
//		TEST #4   Results...
//	    
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    1
//	    Enter Your Email:
//
//	    lance@gmail.com
//	    Enter Your Password:
//
//	    111
//	    Wrong Credentials
//	    Enter Your Email:
//
//	    lance@gmail.com
//	    Enter Your Password:
//
//	    555
//	    COURSE NAME 	 COURSE MINIMUN GPA
//	    English 		 3.1
//	    1. Logout
//	    
//	
//  	TEST #5 - Student with invalid password - J@gmail.com, 333	  ================================================================================== 
//		TEST #5   Results...    
//	    
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    2
//	    Enter Your Email:
//
//	    J@gmail.com
//	    Enter Your Password:
//
//	    111
//
//	    Wrong Credentials
//	    Enter Your Email:
//
//	    J@gmail.com
//	    Enter Your Password:
//
//	    333
//
//	    My Classes:
//	    #   COURSE NAME 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//	    1   Math                 Luke                    luke@gmail.com
//	    2   Science              mark                    mark@gmail.com
//	    3   English              lance                   lance@gmail.com
//	    4   Science              Luke                    luke@gmail.com
//
//	    1. Register to Class
//	    2. Logout
//	    
//	    
//	    
//	    
//  	TEST #6 - Student with low GPA - J@gmail.com, 333	  ======================================================================================== 
//		TEST #6   Results... 
//	    Added new test student to database
//	       insert into STUDENT s
//	          (s.FULL_NAME, s.EMAIL, s.GPA, s.PASS, s.STUDENT_ROLE) 
//	          values('Test', 'Test@yahoo.com', 1.1, '111', -1);
//	    
//	       commit;
//	    
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    2
//	    Enter Your Email:
//
//	    Test@yahoo.com
//	    Enter Your Password:
//
//	    111
//
//	    My Classes:
//	    #   COURSE NAME 	 INTRUCTOR NAME 	 INSTRUCTOR EMAIL
//
//	    1. Register to Class
//	    2. Logout
//	    1
//
//	    All Courses:
//	    ID  COURSE NAME 	 MINIMUN GPA
//	    1   Math                 3.1
//	    2   Science              3.2
//	    3   English              3.1
//	    4   GYM                  2.8
//
//	    Which Course?
//
//	    1
//	    Exception in thread "main" w7a3_School_JDBC.CustomExceptions.StudentRegistrationException: 
//	    Did not meet the minimum GPA requirement
//	    Registration Denied
//	    	at w7a3_School_JDBC.DAO.AttendingDAO.registerStudentToCourse(AttendingDAO.java:45)
//	    	at w7a3_School_JDBC.MainEntryPoint.MainEntryClass.main(MainEntryClass.java:143)
//
//	    
//	    
//	    
//
//  	TEST #7 - Option 3 to quit	  ===================================================================================================== 
//		TEST #7   Results... 
//	    
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    1
//	    Enter Your Email:
//
//	    lance@gmail.com
//	    Enter Your Password:
//
//	    555
//	    COURSE NAME 	 COURSE MINIMUN GPA
//	    English 		 3.1
//	    1. Logout
//	    1
//	    Are you a(n)
//	    1. Instructor 
//	    2. Student 
//	    3. quit 
//	    Please, enter 1, 2 or 3 
//
//	    3
//
//	    	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//	    
//    ================================================================================================================================
//    =====================================================     Seperator      =======================================================
//    ================================================================================================================================
//    public void allIntructors() throws ClassNotFoundException, IOException {
//        InstructorDAO insDAO = new InstructorDAO();
//        
//        List<Instructor> allIns = insDAO.getAllInstructors();
//        System.out.printf("\nInstructors:\n======================================================================\n");
//        System.out.printf("%-3s INTRUCTOR NAME \t INSTRUCTOR EMAIL \t INSTRUCTOR SPECIALITY\n","ID");
//        
//        for(Instructor insAGN : allIns) {
//            System.out.printf("%-3s %-20s %-23s %s\n",insAGN.getInstructor_id(),  insAGN.getFull_name(), insAGN.getEmail(), insAGN.getSpeciality());
//        }
//        
//    }
//    
//    public void allCourse() throws ClassNotFoundException, IOException {
//        CourseDAO coDAO = new CourseDAO();
//        
//        List<Course> allCo = coDAO.getAllCourses();
//        System.out.printf("\nCourses:\n===============================================\n");
//        System.out.printf("%-3s COURSE NAME \t MINIMUN GPA\n", "ID");
//        
//        for(Course coAGN : allCo) {
//            System.out.printf("%-3s %-20s %s\n",coAGN.getCourse_id(),  coAGN.getCourse_name(), coAGN.getMinimun_gpa());
//        }
//        
//    }
//    
//    public void allCoursesWithInstructor() {
//        TeachingDAO teaDAO = new TeachingDAO();
//        List<Teaching> teaList = teaDAO.getIntructorsCourses();
//        System.out.printf("COURSE NAME \t COURSE MINIMUN GPA \t INTRUCTOR NAME \t INSTRUCTOR EMAIL\n\n");
//        for(Teaching teaAGN : teaList) {
//            System.out.printf("%-16s %-23s %-23s %s\n", teaAGN.getCourse_name(), teaAGN.getMinimun_gpa(), 
//                    teaAGN.getFull_name(), teaAGN.getEmail());
//        }
//    }
//    
//    public void StudentCourses(List<Attending> attList) {
//        int counter = 1;
//        System.out.printf("\nMy Classes:\n");
//        System.out.printf("%-3s COURSE NAME \t INTRUCTOR NAME \t INSTRUCTOR EMAIL\n", "#");
//        for(Attending att : attList) {
//            System.out.printf("%-3s %-20s %-23s %s\n", counter, att.getCourse_name(), att.getFull_name(), att.getEmail());
//            counter++;
//            
//        }
//    }
//    
//    public void allCourses(List<Course> coList) {
//        int counter = 1;
//        System.out.printf("\nAll Courses:\n");
//        System.out.printf("%-3s COURSE NAME \t MINIMUN GPA\n", "ID");
//        for(Course co : coList) {
//            System.out.printf("%-3s %-20s %s\n", counter, co.getCourse_name(), co.getMinimun_gpa());
//            counter++;
//        }
//    }
//    
//    public static void main(String[] args) throws ClassNotFoundException, IOException, StudentRegistrationException {
//        boolean quit = false;
//        Scanner reader = new Scanner(System.in);
//        MainEntryClass mainObj = new MainEntryClass();
//        int InsOrStu = -1;
//        Instructor ins = null;  InstructorDAO insDAO = null;   String insROLE = "";
//        
//        
//        Student stu = null;     StudentDAO stuDAO = null;
//        
//        Course co = null;       CourseDAO coDAO = null;
//        
//        Attending att = null;   AttendingDAO attDAO = null;
//        
//        Teaching tea = null;    TeachingDAO teaDAO = null;
//        
//        String email = "";
//        String password = "";
//        while(!quit) {
//            System.out.println("Are you a(n)\n1. Instructor \n2. Student \n3. quit \nPlease, enter 1, 2 or 3 \n");
//            InsOrStu = reader.nextInt();
//            if(InsOrStu == 1) {
//                boolean logout = false;
//                while(!logout) {
//                    System.out.println("Enter Your Email:\n");
//                    email = reader.next();
//                    System.out.println("Enter Your Password:\n");
//                    password = reader.next();
//                    insDAO = new InstructorDAO();
//                    ins = insDAO.getInstructoByGmail(email);
//                    
//                    insROLE = insDAO.validateUser(ins, password);
//                    
//                    
//                    if("Admin".equals(insROLE)) {
//                        teaDAO = new TeachingDAO();
//                        mainObj.allCoursesWithInstructor();
//                        String out = "-1";
//                        while(!out.equals("2")) {
//                            System.out.printf("\n\n");
//                            System.out.println("1. Assign Instructor to Course");
//                            System.out.println("2. Logout");
//                            out = reader.next();  
//                            
//                            if(out.equals("1")) {
//                                int instructor_id = -1;
//                                int course_id = -1;
//                                mainObj.allIntructors();
//                                System.out.println("\nWhat Instructor?\n");
//                                instructor_id = reader.nextInt();
//                                mainObj.allCourse();
//                                System.out.println("\nWhich Course?\n");
//                                course_id = reader.nextInt();
//                                int assignId =  teaDAO.assignInstructorToCourse(course_id, instructor_id);
//                                if(assignId != -1) {
//                                    System.out.println("\n -->Instructor Assigned<--\nNew Record Id: "+ assignId+"\n");
//                                }
//                                mainObj.allCoursesWithInstructor();
//                            }
//                        }
//                        System.out.printf("\n\n");
//                        logout = true;
//                    }else if("Instructor".equals(insROLE)) {
//                        coDAO = new CourseDAO();
//                        List<Course> coList = coDAO.getCourseByInstructor(ins.getInstructor_id());
//                        System.out.printf("COURSE NAME \t COURSE MINIMUN GPA\n");
//                        for(Course insCO : coList) {
//                            System.out.printf("%s \t\t %s\n", insCO.getCourse_name(), insCO.getMinimun_gpa());
//                        }
//                        String out = "-1";
//                        while(!out.equals("1")) {
//                            System.out.println("1. Logout");
//                            out = reader.next();  
//                        }
//                        ins = null;
//                        logout = true;
//                    }else if("Wrong Credentials".equals(insROLE)) {
//                        System.out.println(insROLE);
//                        continue;
//                    }
//                
//                }
//                
//            }else if(InsOrStu == 2) {
//                stuDAO = new StudentDAO();
//                attDAO = new AttendingDAO();
//                boolean logout = false;
//                while(!logout) {
//                    System.out.println("Enter Your Email:\n");
//                    email = reader.next();
//                    System.out.println("Enter Your Password:\n");
//                    password = reader.next();
//                    
//                    List<Attending> attList = null;
//                    stu = stuDAO.getStudentByGmail(email);
//                    
//                    if(stu != null && stu.getStudent_role() == -1 && stuDAO.validateUser(stu.getPass(), password)) {
//                        
//                        String classesTo = "";
//                        while(!classesTo.equals("2")) {
//                            attList = attDAO.getStudentCourse(stu.getStudent_id());
//                            mainObj.StudentCourses(attList);
//                            System.out.printf("\n1. Register to Class");
//                            System.out.printf("\n2. Logout\n");
//                            classesTo = reader.next();
//                            if(classesTo.equals("1")) {
//                                coDAO = new CourseDAO();
//                                attDAO = new AttendingDAO();
//                                List<Course> coList = coDAO.getAllCourses();
//                                mainObj.allCourses(coList);
//                                System.out.println("\nWhich Course?\n");
//                                int course_idForStudent = reader.nextInt();
//                                attDAO.registerStudentToCourse(stu, coList.get(course_idForStudent - 1));
//                                classesTo = "";
//                            }
//                        }
//                        logout = true;
//                    }else {
//                        System.out.printf("\nWrong Credentials\n");
//                        continue;
//                    }
//                }
//                
//            }else if(InsOrStu == 3) {
//                quit = true;
//            }
//        }
//        
//    }
}
