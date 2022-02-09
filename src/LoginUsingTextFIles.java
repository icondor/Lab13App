import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LoginUsingTextFIles {

    List<User> dbusers = null;

    private User readFromKb() {

        System.out.print("U:");
        String user = new Scanner(System.in).nextLine();
        System.out.print("P:");
        String pwd = new Scanner(System.in).nextLine();

        User u = new User(user, pwd);
        return u;
    }


    /*
    citeste un fisier text de forma
    ionel pwd
    maria pwo
    ana  ppp

       si pune in memorie un array cu ele
     */

    private void loadDB() {
        dbusers= new ArrayList<>();
        Path p = Paths.get("dbusers.txt");
        List<String> lista = null;
        try {
            lista = Files.readAllLines(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lista.size(); i++) {
            User u = new User();
            String currentLine = lista.get(i);
            StringTokenizer st = new StringTokenizer(currentLine);
            while (st.hasMoreTokens()) {
                String user = st.nextToken();
                String pwd = st.nextToken();
                u.setUser(user);
                u.setPwd(pwd);
            }
            dbusers.add(u);
        }
        //System.out.println("incard din nd atatea elem:"+dbusers.size());
    }


    private boolean login(User userinput) {

        boolean isLogin=false;
        System.out.println(dbusers.size());
        for (int i = 0; i < dbusers.size(); i++) {
            User u = dbusers.get(i);

            String userdb = u.getUser();
            String pwddb = u.getPwd();
            String inputuser = userinput.getUser();
            String inputpwd = userinput.getPwd();

           // System.out.println(userdb+pwddb+inputuser+inputpwd);

            if(userdb.equalsIgnoreCase(inputuser) && pwddb.equals(inputpwd)) {
                System.out.println("de succes finally");
                isLogin = true;
                break;
            }

        }
        return isLogin;
    }



    public boolean tryLogin() {

        loadDB();
        boolean succes=false;
       do{
            User usr = readFromKb();
            succes=login(usr);

        }
       while(!succes);
       return succes;
    }


//    public static void main(String[] args) {
//
//        LoginUsingTextFIles l = new LoginUsingTextFIles();
//        l.tryLogin();
//
//
//
//    }
}
