import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Podaj imię");
//        String name = sc.nextLine();
//        System.out.println("Podaj nazwisko");
//        String surname = sc.nextLine();
//        System.out.println("Podaj wypłata");
//        double salary = sc.nextDouble();
//        Employee emp1 = new Employee(name, surname, salary);
//
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee emp2 = employeeDAO.read(1);
        System.out.println(emp2);
        Employee emp3 = new Employee(1, "Karol", "Mocarek", 5000);
        employeeDAO.update(emp3);
        employeeDAO.delete(1);
    }
}
