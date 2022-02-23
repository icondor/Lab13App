public class App {

    public static void main(String[] args) {

        // modul de login

              // incarca db in memorie
             // cere user si parola
             // daca userul si parola sunt in db mergi mai departe altfel repeta

//    LoginUsingTextFIles login = new LoginUsingTextFIles();
//     login.tryLogin();

        LoginUsingDB db = new LoginUsingDB();
        db.tryLogin();

//        NewsReport nr = new NewsReport();
//        nr.readNews("stiri.txt");


        // modul de citit stiri si parsare si generare raport si mail

             // citire fisier de stiri
             // impartire pe stiri
             // pt fiecare stire
                  // aplica "alg"  -----
                  //trimite mail   --- nu am mai facut






    }
}
