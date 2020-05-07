import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=CONNECTIS;integratedSecurity=true;";

        Connection conn = DriverManager.getConnection(url);
        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sqlQuery = "Select * from Employees";
        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getInt(6));
            System.out.println(rs.getInt(7));
            System.out.println(rs.getDate(8));
          /* Przykład
            System.out.println(rs.getDate(8));
            System.out.println(rs.getDate("StartJobDate"));*/
            System.out.println(rs.getInt(9));
        }

        //insert
        String insert = "Insert INTO Employees (LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, "Jarek");
        ps.setString(2, "Garek");
        ps.setString(3, "Bursztynowa");
        ps.setString(4, "Wilno");
        ps.setInt(5, 4500);
        ps.setInt(6, 42);
        ps.setDate(7, Date.valueOf("2018-10-15"));
        ps.setInt(8, 1);

/*        int rowInsert = ps.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }*/

        //update

        String update = "UPDATE Employees SET LastName=?, FirstName=?, Address=?, City=?, Salary=?, Age=?, StartJobDate=?, Benefit=? WHERE ID=?";

        PreparedStatement ps1 = conn.prepareStatement(update);
        ps1.setString(1, "Jarek");
        ps1.setString(2, "Garek");
        ps1.setString(3, "Bursztynowa");
        ps1.setString(4, "Radom");
        ps1.setInt(5, 4500);
        ps1.setInt(6, 42);
        ps1.setDate(7, Date.valueOf("2018-10-15"));
        ps1.setInt(8, 1);
        ps1.setInt(9,7);

/*        int rowInsert = ps1.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }*/

        //delete
        String delete = "DELETE FROM Employees WHERE ID=?";

        PreparedStatement ps2 = conn.prepareStatement(delete);
        ps2.setInt(1, 7);

        int rowInsert = ps2.executeUpdate();
        if(rowInsert > 0) {
            System.out.println("Success!");
        }
    }

    public void transactions(Connection conn) {
        //TODO
        try {
            conn.setAutoCommit(Boolean.FALSE);
            //do
            conn.commit();
        } catch (SQLException e) {
            //      conn.rollback();
            e.printStackTrace();
        } finally {
            //       conn.setAutoCommit(Boolean.TRUE);
        }
    }
}
