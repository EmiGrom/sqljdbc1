import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databases=Connectis;integratedSecurity=true;";
        String USERNAME = "dbo";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(url);

        //Connection conn = DriverManager.getConnection(url);
        System.out.println(conn);

        Statement statement = conn.createStatement();
        String sqlQuery = "Select * from Connectis.dbo.Employee";
        ResultSet rs = statement.executeQuery(sqlQuery);
        System.out.println(rs);
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
            System.out.println(rs.getDate("StartJobDate"));
            System.out.println(rs.getInt(9));*/
        }

        //insert
        String insert = "Insert INTO Connectis.dbo.Employee (LastName, FirstName, Address, City, Salary, Age, StartJobDate, Benefit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, "Anna");
        ps.setString(2, "Annowska");
        ps.setString(3, "ul. Nowa 5");
        ps.setString(4, "Gdańsk");
        ps.setInt(5, 2900);
        ps.setInt(6, 37);
        ps.setDate(7, Date.valueOf("2016-08-21"));
        ps.setInt(8, 1);

        /* int rowInsert = ps.executeUpdate();
        if (rowInsert > 0) {
            System.out.println("Success!");
        }*/

        //update

        String update = "UPDATE Connectis.dbo.Employee SET LastName=?, FirstName=?, Address=?, City=?, Salary=?, Age=?, StartJobDate=?, Benefit=? WHERE ID=?";

        PreparedStatement ps1 = conn.prepareStatement(update);
        ps1.setString(1, "Anna");
        ps1.setString(2, "Annowska");
        ps1.setString(3, "ul. Nowa 5");
        ps1.setString(4, "Gdańsk");
        ps1.setInt(5, 2900);
        ps1.setInt(6, 37);
        ps1.setDate(7, Date.valueOf("2016-08-21"));
        ps1.setInt(8, 1);
        ps1.setInt(9, 7);

        /*int rowInsert = ps1.executeUpdate();
        if (rowInsert > 0) {
            System.out.println("Success!");
        }*/

        //delete
        String delete = "DELETE FROM Connectis.dbo.Employee WHERE ID=?";

        PreparedStatement ps2 = conn.prepareStatement(delete);
        ps2.setInt(1, 7);

        int rowInsert = ps2.executeUpdate();
        if (rowInsert > 0) {
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
