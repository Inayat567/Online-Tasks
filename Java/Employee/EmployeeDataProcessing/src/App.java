// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.function.Function;

// class Employee {
//     private String name;
//     private int age;
//     private String department;
//     private double salary;

//     public Employee(String name, int age, String department, double salary) {
//         this.name = name;
//         this.age = age;
//         this.department = department;
//         this.salary = salary;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getDepartment() {
//         return department;
//     }

//     public double getSalary() {
//         return salary;
//     }

//     public int getAge() {
//         return age;
//     }
// }

// public class App {

//     public static void main(String[] args) {
//         List<Employee> employees = readEmployeeDataFromFile(
//                 "E:/Online Tasks/Java/Employee/EmployeeDataProcessing/src/employee_data.csv");

//         Function<Employee, String> concatNameAndDept = employee -> employee.getName() + " - "
//                 + employee.getDepartment();

//         List<String> employeeInfoList = employees.stream()
//                 .map(concatNameAndDept)
//                 .collect(Collectors.toList());

//         System.out.println("Concatenated Names and Departments:");
//         employeeInfoList.forEach(System.out::println);

//         double averageSalary = employees.stream()
//                 .mapToDouble(Employee::getSalary)
//                 .average()
//                 .orElse(0.0);

//         System.out.println("Average Salary: " + averageSalary);

//         int ageThreshold = 30;
//         List<String> aboveThresholdEmployeeInfoList = employees.stream()
//                 .filter(employee -> employee.getAge() > ageThreshold)
//                 .map(concatNameAndDept)
//                 .collect(Collectors.toList());

//         System.out.println("Employees above " + ageThreshold + " years old:");
//         aboveThresholdEmployeeInfoList.forEach(System.out::println);
//     }

//     public static List<Employee> readEmployeeDataFromFile(String fileName) {
//         List<Employee> employees = new ArrayList<>();

//         try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//             String line;
//             while ((line = br.readLine()) != null) {
//                 String[] data = line.split(",");
//                 if (data.length == 4) {
//                     String name = data[0];
//                     int age = Integer.parseInt(data[1]);
//                     String department = data[2];
//                     double salary = Double.parseDouble(data[3]);
//                     employees.add(new Employee(name, age, department, salary));
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("Error reading data from the file: " + e.getMessage());
//         }

//         return employees;
//     }
// }




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private String department;
    private double salary;

    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }
}

public class App {

    public static void main(String[] args) {
        List<Employee> employees = readEmployeeDataFromFile("E:/Online Tasks/Java/Employee/EmployeeDataProcessing/src/employee_data.csv");

        Function<Employee, String> concatNameAndDept = employee -> employee.getName() + " - " + employee.getDepartment();

        List<String> employeeInfoList = employees.stream()
                .map(concatNameAndDept)
                .collect(Collectors.toList());

        System.out.println("Concatenated Names and Departments:");
        employeeInfoList.forEach(System.out::println);

        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.println("Average Salary: " + averageSalary);

        int ageThreshold = 30;
        List<String> aboveThresholdEmployeeInfoList = employees.stream()
                .filter(employee -> employee.getAge() > ageThreshold)
                .map(concatNameAndDept)
                .collect(Collectors.toList());

        System.out.println("Employees above " + ageThreshold + " years old:");
        aboveThresholdEmployeeInfoList.forEach(System.out::println);
    }

    public static List<Employee> readEmployeeDataFromFile(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Read line: " + line);
                String[] data = line.split(",");
                System.out.println("Read line: " + data.length);
                if (data.length == 4) {
                    String name = data[0];
                    double ageDouble = Double.parseDouble(data[1]); // Parse as double
                    int age = (int) ageDouble; // Convert to int
                    String department = data[2];
                    double salary = Double.parseDouble(data[3]); // Parse as double
                    System.out.print(name + age + department + salary);
                    employees.add(new Employee(name, age, department, salary));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data from the file: " + e.getMessage());
        }

        return employees;
    }
}
