import java.sql.*;

public class LoginUsingDB {

    public boolean tryLogin() {

        boolean succes=false;
        do{
            User usr = LoginUsingTextFIles.readFromKb();

            succes=login(usr);

        }
        while(!succes);
        return succes;
    }

    private boolean login(User u) {

        boolean found = false;
        // 1. ma conectez la db
        final String URL = "jdbc:postgresql://idc.cluster-custom-cjcsijnttbb2.eu-central-1.rds.amazonaws.com:5432/ionelcondor";
        final String USERNAME = "ftuser";
        final String PASSWORD = "";
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // 2. fac un query pe o tabela , intai creez obiectul
            PreparedStatement pSt = conn.prepareStatement("SELECT id FROM USERS where username=? and password=?");
            pSt.setString(1,u.getUser());
            pSt.setString(2,u.getPwd());


            // 3. execut acel query
            ResultSet rs = pSt.executeQuery();

            // 4 . optional, fac ce doresc cu datele din acest ResultSet

            while (rs.next()) {
                long id = rs.getLong("id");
                found=true;
            }

            rs.close();
            pSt.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
