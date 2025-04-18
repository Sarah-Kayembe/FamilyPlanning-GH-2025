import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MethodsOfChoiceDao {

    public void addMethod(MethodsOfChoice method) {
        String sql = "INSERT INTO methods_of_choice (method_name) VALUES (?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, method.getMethodName());
            pstmt.executeUpdate();
            System.out.println("Method added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MethodsOfChoice getMethodById(int id) {
        String sql = "SELECT * FROM methods_of_choice WHERE id = ?";
        MethodsOfChoice method = null;

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                method = new MethodsOfChoice();
                method.setId(rs.getInt("id"));
                method.setMethodName(rs.getString("method_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return method;
    }

    public List<MethodsOfChoice> getAllMethods() {
        List<MethodsOfChoice> methods = new ArrayList<>();
        String sql = "SELECT * FROM methods_of_choice";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MethodsOfChoice method = new MethodsOfChoice();
                method.setId(rs.getInt("id"));
                method.setMethodName(rs.getString("method_name"));
                methods.add(method);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return methods;
    }

    public void updateMethod(MethodsOfChoice method) {
        String sql = "UPDATE methods_of_choice SET method_name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, method.getMethodName());
            pstmt.setInt(2, method.getId());

            pstmt.executeUpdate();
            System.out.println("Method updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMethod(int id) {
        String sql = "DELETE FROM methods_of_choice WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Method deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

