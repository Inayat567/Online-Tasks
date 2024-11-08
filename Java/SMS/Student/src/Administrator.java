public class Administrator {

  public static void main(String[] args) {
    CommonFunctions fn = new CommonFunctions();
    int choice;

    do {
      System.out.println(
          "\n\n\t\t\t\t############***************Student Record Management System   ***************############");
      System.out.println(
          "\t\t\t\t############***************1. Add Student                     ***************############");
      System.out.println(
          "\t\t\t\t############***************2. Update Student                  ***************############");
      System.out.println(
          "\t\t\t\t############***************3. View Student Details            ***************############");
          System.out.println(
          "\t\t\t\t############***************4. Check Total Student             ***************############");
      System.out.println(
          "\t\t\t\t############***************5. Exit                            ***************############");
      // System.out.print("\t\t\t\t############***************Enter your choice: \t");
      choice = fn.getNumberFromUser(
          "\t\t\t\t############***************Enter your choice: \t");

      switch (choice) {
        case 1:
          // Add Student
          String name = fn.getStringFromUser("Enter student name: ");
          int id = fn.getNumberFromUser("Enter student ID: ");
          int age = fn.getNumberFromUser("Enter student age: ");
          String grade = fn.getStringFromUser("Enter student grade: ");
          Student stdExist =  SMS.getStudentDetails(id);
          if(stdExist == null){
            SMS.addStudent(name, id, age, grade);
            System.out.println(
              "\n\t\t\t###*******  Student added successfully.\n");
          }else{
            System.out.println(
              "\n\t\t\t Error! Student Already Exists.\n");
          }
          
          break;
        case 2:
          // Update Student
          int stdID = fn.getNumberFromUser("Enter student ID to update: ");
          Student std = SMS.getStudentDetails(stdID);
          if (std != null) {
            String newName = fn.getStringFromUser("Enter new student name: ");
            int newAge = fn.getNumberFromUser("Enter new student age: ");
            String newGrade = fn.getStringFromUser("Enter new student grade: ");
            SMS.updateStudent(stdID, newName, newAge, newGrade);
            System.out.println(
                "\n\t\t\t###*******  Student updated successfully.\n");
          } else {
            System.out.println(
                "\n\t\t Student Record not found with ID: " + stdID + " \n");
          }
          break;
        case 3:
          // View Student Details
          stdID = fn.getNumberFromUser("Enter student ID to view details: ");
          std = SMS.getStudentDetails(stdID);
          if (std != null) {
            System.out.println(
                "\t\t\t\t############***************  Student Detail  :");
            System.out.println(
                "\t\t\t\t############***************  Name              : " +
                    std.getStudentName());
            System.out.println(
                "\t\t\t\t############***************  ID                : " +
                    std.getStudentID());
            System.out.println(
                "\t\t\t\t############***************  Age               : " +
                    std.getStudentAge());
            System.out.println(
                "\t\t\t\t############***************  Grade             : " +
                    std.getStudentGrade());
          } else {
            System.out.println("Student Record not found with ID: " + stdID);
          }
          break;
        case 4:
          System.out.println("Total number of Student = "+SMS.getTotalStudents());
          break;
        case 5:
          System.out.println("############***************    Quiting........");
          break;
        default:
          System.out.println("Invalid choice! Please try again.");
      }
    } while (choice != 5);
  }
}
