import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MonthlyVisitDAOTest {

    private static MonthlyVisitDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new MonthlyVisitDAO();
    }

    @Test
    public void testAddAndGetVisit() {
        MonthlyVisit visit = new MonthlyVisit(1, "2025-04-14", "April", "Injectables", 3, true, false, "First time");
        dao.addVisit(visit);

        List<MonthlyVisit> visits = dao.getAllVisits();
        assertFalse(visits.isEmpty());

        MonthlyVisit last = visits.get(visits.size() - 1);
        assertEquals("April", last.getVisitMonth());
        assertEquals("Injectables", last.getCommodity());
    }

    @Test
    public void testUpdateVisit() {
        MonthlyVisit visit = new MonthlyVisit(1, "2025-04-14", "April", "Pills", 2, false, true, "Follow-up");
        dao.addVisit(visit);

        List<MonthlyVisit> visits = dao.getAllVisits();
        MonthlyVisit last = visits.get(visits.size() - 1);

        last.setCommodity("IUD");
        last.setUnitsIssued(1);
        dao.updateVisit(last);

        MonthlyVisit updated = dao.getVisitById(last.getId());
        assertEquals("IUD", updated.getCommodity());
        assertEquals(1, updated.getUnitsIssued());
    }

    @Test
    public void testDeleteVisit() {
        MonthlyVisit visit = new MonthlyVisit(1, "2025-04-14", "May", "Condoms", 10, false, true, "Monthly pickup");
        dao.addVisit(visit);

        List<MonthlyVisit> visits = dao.getAllVisits();
        MonthlyVisit last = visits.get(visits.size() - 1);

        dao.deleteVisit(last.getId());
        MonthlyVisit deleted = dao.getVisitById(last.getId());

        assertNull(deleted);
    }
}

