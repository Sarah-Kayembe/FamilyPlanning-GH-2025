package edu.usm.healthsystem.storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthlyVisitDAO {

    public void addVisit(MonthlyVisit visit) {
        String sql = "INSERT INTO monthly_visits (client_id, visit_date, visit_month, commodity, units_issued, referred, expected_to_return, remarks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, visit.getClientId());
            pstmt.setString(2, visit.getVisitDate());
            pstmt.setString(3, visit.getVisitMonth());
            pstmt.setString(4, visit.getCommodity());
            pstmt.setInt(5, visit.getUnitsIssued());
            pstmt.setBoolean(6, visit.isReferred());
            pstmt.setBoolean(7, visit.isExpectedToReturn());
            pstmt.setString(8, visit.getRemarks());

            pstmt.executeUpdate();
            System.out.println("Monthly visit added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MonthlyVisit getVisitById(int id) {
        String sql = "SELECT * FROM monthly_visits WHERE id = ?";
        MonthlyVisit visit = null;

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                visit = new MonthlyVisit();
                visit.setId(rs.getInt("id"));
                visit.setClientId(rs.getInt("client_id"));
                visit.setVisitDate(rs.getString("visit_date"));
                visit.setVisitMonth(rs.getString("visit_month"));
                visit.setCommodity(rs.getString("commodity"));
                visit.setUnitsIssued(rs.getInt("units_issued"));
                visit.setReferred(rs.getBoolean("referred"));
                visit.setExpectedToReturn(rs.getBoolean("expected_to_return"));
                visit.setRemarks(rs.getString("remarks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visit;
    }

    public List<MonthlyVisit> getAllVisits() {
        List<MonthlyVisit> visits = new ArrayList<>();
        String sql = "SELECT * FROM monthly_visits";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MonthlyVisit visit = new MonthlyVisit();
                visit.setId(rs.getInt("id"));
                visit.setClientId(rs.getInt("client_id"));
                visit.setVisitDate(rs.getString("visit_date"));
                visit.setVisitMonth(rs.getString("visit_month"));
                visit.setCommodity(rs.getString("commodity"));
                visit.setUnitsIssued(rs.getInt("units_issued"));
                visit.setReferred(rs.getBoolean("referred"));
                visit.setExpectedToReturn(rs.getBoolean("expected_to_return"));
                visit.setRemarks(rs.getString("remarks"));
                visits.add(visit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }

    public void updateVisit(MonthlyVisit visit) {
        String sql = "UPDATE monthly_visits SET client_id = ?, visit_date = ?, visit_month = ?, commodity = ?, units_issued = ?, referred = ?, expected_to_return = ?, remarks = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, visit.getClientId());
            pstmt.setString(2, visit.getVisitDate());
            pstmt.setString(3, visit.getVisitMonth());
            pstmt.setString(4, visit.getCommodity());
            pstmt.setInt(5, visit.getUnitsIssued());
            pstmt.setBoolean(6, visit.isReferred());
            pstmt.setBoolean(7, visit.isExpectedToReturn());
            pstmt.setString(8, visit.getRemarks());
            pstmt.setInt(9, visit.getId());

            pstmt.executeUpdate();
            System.out.println("Monthly visit updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVisit(int id) {
        String sql = "DELETE FROM monthly_visits WHERE id = ?";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Monthly visit deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

