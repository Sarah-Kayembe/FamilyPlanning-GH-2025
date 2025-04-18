import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ClientDAOTest {

    private static ClientDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new ClientDAO();
    }

    @Test
    public void testAddAndGetClient() {
        Client client = new Client("John", "Doe", "M", "Single", 28, 0);
        dao.addClient(client);

        List<Client> clients = dao.getAllClients();
        assertFalse(clients.isEmpty());

        Client last = clients.get(clients.size() - 1);
        assertEquals("John", last.getFirstName());
        assertEquals("Doe", last.getLastName());
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client("Anna", "Smith", "F", "Married", 32, 2);
        dao.addClient(client);

        List<Client> clients = dao.getAllClients();
        Client last = clients.get(clients.size() - 1);

        last.setMaritalStatus("Single");
        dao.updateClient(last);

        Client updated = dao.getClientById(last.getId());
        assertEquals("Single", updated.getMaritalStatus());
    }

    @Test
    public void testDeleteClient() {
        Client client = new Client("Jane", "Doe", "F", "Single", 22, 0);
        dao.addClient(client);

        List<Client> clients = dao.getAllClients();
        Client last = clients.get(clients.size() - 1);

        dao.deleteClient(last.getId());
        Client deleted = dao.getClientById(last.getId());

        assertNull(deleted);
    }
}

