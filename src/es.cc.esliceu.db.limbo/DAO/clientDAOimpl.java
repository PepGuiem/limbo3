package es.cc.esliceu.db.limbo.DAO;

import es.cc.esliceu.db.limbo.APP.Client;
import es.cc.esliceu.db.limbo.util.Color;

import java.sql.*;

public class clientDAOimpl implements clientDAO {
    @Override
    public Client registreClient(Client client) {
        try {
            Client cl = new Client();
            ConnectionToBBDD cn = new ConnectionToBBDD();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(cn.getUrl(), cn.getUsername(), cn.getPassword());
            Statement statement = connection.createStatement();
            String insert = "INSERT INTO `client` (`username`,`email`,`contrasenya`,`nom`,`cognom1`,`cognom2`)" +
                    "VALUES('" + client.getUsernameClient() + "','" + client.getEmailClient() + "','" +
                    client.getContrasenyaClient() + "','" + client.getNomClient() + "','" +
                    client.getCognom1Client() + "','" + client.getCognom2Client() + "');";
            try {
                statement.execute(insert);
                String selectId = "SELECT `numero_client` FROM `client` WHERE `username` ='"
                        + client.getUsernameClient() + "';";
                PreparedStatement statementSelect = connection.prepareStatement(selectId);
                ResultSet rs = statementSelect.executeQuery();
                if (rs.next()) {
                    cl.setUsernameClient(client.getUsernameClient());
                    cl.setNumeroClient(rs.getInt("numero_client"));
                }
                connection.close();
                return cl;
            } catch (SQLIntegrityConstraintViolationException ex) {
                String[] strings = ex.getMessage().split("_");
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].equals("email")) {
                        System.out.println(Color.RED_BOLD + "Email existent !!!" + Color.RESET);
                        break;
                    } else if (strings[i].equals("nick")) {
                        System.out.println(Color.RED_BOLD + "Username existent !!!" + Color.RESET);
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println(Color.RED_BOLD + "No se ha pogut inserir el registre!!!" + Color.RESET);
            }
            connection.close();
        } catch (Exception exception) {
            System.out.println(Color.RED_BOLD + "\t No se ha pogut connectar a la BBDD!!" + Color.RESET);
        }
        return null;
    }

    @Override
    public boolean loginClient(String tipus, String contingut) {
        try {
            ConnectionToBBDD cn = new ConnectionToBBDD();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(cn.getUrl(), cn.getUsername(), cn.getPassword());
            Statement statement = connection.createStatement();
            String select = "SELECT * FROM `client` WHERE `" + tipus + "`=?;";
            PreparedStatement statementSelect = connection.prepareStatement(select);
            statementSelect.setString(1,contingut);
            ResultSet rs = statementSelect.executeQuery();
            if (rs.next()) {
                return false;
            }
        }catch (SQLException e){

        }catch (Exception e) {
            System.out.println(Color.RED_BOLD + "\t No se ha pogut connectar a la BBDD!!" + Color.RESET);
        }
        return true;
    }
}
