import java.sql.*;

public class EmployeeDAO {

    private static final String url = "jdbc:mysql://localhost:3306/pracownicy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";
    private Connection connection;

    public EmployeeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void save(Employee employee) throws SQLException {
        final String insert = "insert into pracownicy(firstName,lastName,salary) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setDouble(3, employee.getSalary());
        int executeUpdate = preparedStatement.executeUpdate();
    }

    public Employee read(long id) {
        final String read = "Select id, firstName, lastName, salary from pracownicy where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(read);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setSalary(resultSet.getDouble("salary"));
                return employee;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void update(Employee employee) {
        final String sql = "update pracownicy set firstName=?, lastName=?, salary=? where id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, employee.getFirstName());
            prepStmt.setString(2, employee.getLastName());
            prepStmt.setDouble(3, employee.getSalary());
            prepStmt.setLong(4, employee.getId());
            int executeUpdate = prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not update record");
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        final String sql = "delete from pracownicy where id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            int executeUpdate = prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not delete row");
        }
    }
}