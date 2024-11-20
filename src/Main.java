import components.MyConnectionDB;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MyConnectionDB myConnectionDB = new MyConnectionDB();

        String result = myConnectionDB.insert("psicologo", 3,1,1,"Roberto Carlos", 1111111111, 0455, "Terapia Universal");
        System.out.println(result);

    }
}
