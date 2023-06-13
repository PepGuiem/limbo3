package es.cc.esliceu.db.limbo;
import es.cc.esliceu.db.limbo.APP.Client;
import es.cc.esliceu.db.limbo.DAO.clientDAOimpl;
import  es.cc.esliceu.db.limbo.util.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Limbo {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        clientDAOimpl cldao = new clientDAOimpl();
        FileInputStream input = new FileInputStream("C:\\Users\\pepgu\\IdeaProjects\\limbo\\resources" +
                "\\limbo.properties");
        Properties props = new Properties();
        props.load(input);
        String URL = props.getProperty("url");
        String USERNAME = props.getProperty("user");
        String PASSWORD = props.getProperty("password");

        info("username " + USERNAME);

        char c = Menu(sc);
        do {
            if (c == '1') {
                Client client = dadesClientRegistre(sc);
                Client cl = cldao.registreClient(client);
                System.out.println(Color.BLUE_BOLD + "Usuari: " + cl.getUsernameClient()
                        + " creat correctament" + " amb id: "
                        + cl.getNumeroClient() + Color.RESET);
            } else if (c == '2') {
                System.out.println(Color.YELLOW_BOLD + "\t*****************************************");
                System.out.println(Color.YELLOW_BOLD + "\t**               LOGIN                 **");
                System.out.println(Color.YELLOW_BOLD + "\t*****************************************" + Color.RESET);
                String contingut = dadesClientLoginUsername(sc).getUsernameClient();
                comprovacióPassIUser(cldao, contingut, "Username", true,sc);
                contingut = dadesClientLoginPassword(sc).getContrasenyaClient();
                comprovacióPassIUser(cldao,contingut,"Password", false,sc);
            } else if (c == 'h') {

            }
        } while (c != 'x');

        if (c == 'x') {
            System.out.println(Color.GREEN_BOLD + "\t Has sortit de la APP LIMBO.");
        }

    }

    private static void comprovacióPassIUser(clientDAOimpl cldao, String contingut,
                                             String tipus, boolean s, Scanner sc) throws IOException {
        int rec = 0;
        while (true){
            if (cldao.loginClient(tipus.toLowerCase(), contingut)){
                System.out.println(Color.RED_BOLD + tipus + " incorrecte!!" + Color.RESET);
                if (rec == 2) break;
                rec++;
            }else{
                System.out.println(Color.BLUE_BOLD + tipus + " correcte!!" + Color.RESET);
                break;
            }
            if(s){
                contingut = dadesClientLoginUsername(sc).getUsernameClient();
            }else{
                contingut = dadesClientLoginPassword(sc).getContrasenyaClient();
            }
        }
    }

    private static Client dadesClientLoginPassword(Scanner sc) {
        Client client = new Client();
        System.out.println(Color.YELLOW_BACKGROUND + "\b Password: " + Color.RESET);
        client.setContrasenyaClient(GeneradorHash.generaHash(sc.nextLine()));
        return client;
    }

    private static Client dadesClientLoginUsername(Scanner sc) {
        Client client = new Client();
        System.out.println(Color.YELLOW_BACKGROUND + "\b Username: " + Color.RESET);
        client.setUsernameClient(sc.nextLine());
        return client;
    }

    private static Client dadesClientRegistre(Scanner sc) {
        Client client = new Client();
        System.out.println(Color.YELLOW_BOLD + "\t*****************************************");
        System.out.println(Color.YELLOW_BOLD + "\t**            REGISTRE                 **");
        System.out.println(Color.YELLOW_BOLD + "\t*****************************************" + Color.RESET);
        System.out.println("Username: ");
        client.setUsernameClient(sc.nextLine());
        System.out.println("Email: ");
        client.setEmailClient(sc.nextLine());
        System.out.println("Password: ");
        client.setContrasenyaClient(GeneradorHash.generaHash(sc.nextLine()));
        System.out.println("Nom: ");
        client.setNomClient(sc.nextLine());
        System.out.println("Cognom1: ");
        client.setCognom1Client(sc.nextLine());
        System.out.println("Cognom2: ");
        client.setCognom2Client(sc.nextLine());
        return client;
    }

    private static char Menu(Scanner sc) {
        System.out.println(Color.YELLOW_BOLD + "\t*****************************************");
        System.out.println(Color.YELLOW_BOLD + "\t**            LIMBO APP                **");
        System.out.println(Color.YELLOW_BOLD + "\t*****************************************");
        System.out.println(Color.BLUE_BOLD + "\t 1)" + Color.WHITE + "\t Registre-se");
        System.out.println(Color.BLUE_BOLD + "\t 2)" + Color.WHITE + "\t Login");
        System.out.println(Color.BLUE_BOLD + "\t h)" + Color.WHITE + "\t Ajuda");
        System.out.println(Color.BLUE_BOLD + "\t x)" + Color.WHITE + "\t Sortir");
        System.out.print(Color.YELLOW_BACKGROUND_BRIGHT + "\b" + Color.BLACK_BOLD + "\t Escolliu l'opció:"
                + Color.RESET);
        char c = sc.nextLine().charAt(0);
        return c;
    }

    public static void info(String texte) {
        System.out.println(Color.BLUE_BOLD + "\t" + texte + Color.RESET);
    }

    public static void errada(String texte) {
        System.out.println(Color.RED_BOLD + "\t" + texte + Color.RESET);
    }
}
