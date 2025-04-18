import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDetailsDao {

    public void addLocation(LocationDetails location) {
        String sql = "INSERT INTO location_details (address, district, sub_district, facility_zone) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, location.getAddress());
            pstmt.setString(2, location.getDistrict());
            pstmt.setString(3, location.getSubDistrict());
            pstmt.setString(4, location.getFacilityZone());

            pstmt.executeUpdate();
            System.out.println("Location added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocationDetails getLocationById(int id) {
        String sql = "SELECT * FROM location_details WHERE id = ?";
        LocationDetails location = null;

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                location = new LocationDetails();
                location.setId(rs.getInt("id"));
                location.setAddress(rs.getString("address"));
                location.setDistrict(rs.getString("district"));
                location.setSubDistrict(rs.getString("sub_district"));
                location.setFacilityZone(rs.getString("facility_zone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    public List<LocationDetails> getAllLocations() {
        List<LocationDetails> locations = new ArrayList<>();
        String sql = "SELECT * FROM location_details";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                LocationDetails location = new LocationDetails();
                location.setId(rs.getInt("id"));
                location.setAddress(rs.getString("address"));
                location.setDistrict(rs.getString("district"));
                location.setSubDistrict(rs.getString("sub_district"));
                location.setFacilityZone(rs.getString("facility_zone"));
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public void updateLocation(LocationDetails location) {
        String sql = "UPDATE location_details SET address = ?, district = ?, sub_district = ?, facility_zone = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, location.getAddress());
            pstmt.setString(2, location.getDistrict());
            pstmt.setString(3, location.getSubDistrict());
            pstmt.setString(4, location.getFacilityZone());
            pstmt.setInt(5, location.getId());

            pstmt.executeUpdate();
            System.out.println("Location updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteLocation(int id) {
        String sql = "DELETE FROM location_details WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Location deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
