import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public void addClient(Client client) {
        String sql = "INSERT INTO clients (first_name, last_name, sex, marital_status, age, parity) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getSex());
            pstmt.setString(4, client.getMaritalStatus());
            pstmt.setInt(5, client.getAge());
            pstmt.setInt(6, client.getParity());

            pstmt.executeUpdate();
            System.out.println("Client added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client getClientById(int id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        Client client = null;

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setSex(rs.getString("sex"));
                client.setMaritalStatus(rs.getString("marital_status"));
                client.setAge(rs.getInt("age"));
                client.setParity(rs.getInt("parity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setSex(rs.getString("sex"));
                client.setMaritalStatus(rs.getString("marital_status"));
                client.setAge(rs.getInt("age"));
                client.setParity(rs.getInt("parity"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void updateClient(Client client) {
        String sql = "UPDATE clients SET first_name = ?, last_name = ?, sex = ?, marital_status = ?, age = ?, parity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getSex());
            pstmt.setString(4, client.getMaritalStatus());
            pstmt.setInt(5, client.getAge());
            pstmt.setInt(6, client.getParity());
            pstmt.setInt(7, client.getId());

            pstmt.executeUpdate();
            System.out.println("Client updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        String sql = "DELETE FROM clients WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

