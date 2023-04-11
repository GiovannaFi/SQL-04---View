import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<students> italianStudents = new ArrayList<>();
        ArrayList<students> germanStudents = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/giodb", "developer", "developer");
            Statement statement = connection.createStatement();

            statement.executeUpdate("create view italian_students as select first_name, last_name from students where country = 'italy'");
            statement.executeUpdate("create view german_students as select first_name, last_name from students where country = 'germany'");

            ResultSet resultSet = statement.executeQuery("select first_name, last_name from italian_students");
            while (resultSet.next()) {
                String name = resultSet.getString("first_name");
                String surname = resultSet.getString("last_name");
                italianStudents.add(new students(name, surname));

            }

            ResultSet resultSet2 = statement.executeQuery("select first_name, last_name from german_students");
            while (resultSet2.next()) {
                String name = resultSet2.getString("first_name");
                String surname = resultSet2.getString("last_name");
                germanStudents.add(new students(name, surname));

            }

            System.out.println("STUDENTI ITALIANI");

            for (students student : italianStudents) {
                System.out.println(student.getName());
                System.out.println(student.getSurname());
            }

            System.out.println("STUDENTI TEDESCHI");


            for (students student : germanStudents) {
                System.out.println(student.getName());
                System.out.println(student.getSurname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}