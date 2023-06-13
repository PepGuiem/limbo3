package es.cc.esliceu.db.limbo.DAO;
import  es.cc.esliceu.db.limbo.APP.Client;

public interface clientDAO {
    Client registreClient (Client client);
    boolean loginClient (String tipus, String contingut);
}
