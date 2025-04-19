package edu.usm.healthsystem.storage;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LocationDetailsDAOTest {

    private static LocationDetailsDao dao;

    @BeforeAll
    public static void setup() {
        dao = new LocationDetailsDao();
    }

    @Test
    public void testAddAndGetLocation() {
        LocationDetails location = new LocationDetails("123 Main St", "Central", "North Block", "Zone A");
        dao.addLocation(location);

        List<LocationDetails> locations = dao.getAllLocations();
        assertFalse(locations.isEmpty());

        LocationDetails last = locations.get(locations.size() - 1);
        assertEquals("Zone A", last.getFacilityZone());
    }

    @Test
    public void testUpdateLocation() {
        LocationDetails location = new LocationDetails("456 Maple Ave", "West", "Sector 5", "Zone B");
        dao.addLocation(location);

        List<LocationDetails> locations = dao.getAllLocations();
        LocationDetails last = locations.get(locations.size() - 1);

        last.setFacilityZone("Zone C");
        dao.updateLocation(last);

        LocationDetails updated = dao.getLocationById(last.getId());
        assertEquals("Zone C", updated.getFacilityZone());
    }

    @Test
    public void testDeleteLocation() {
        LocationDetails location = new LocationDetails("789 Elm St", "East", "Sector 7", "Zone D");
        dao.addLocation(location);

        List<LocationDetails> locations = dao.getAllLocations();
        LocationDetails last = locations.get(locations.size() - 1);

        dao.deleteLocation(last.getId());
        LocationDetails deleted = dao.getLocationById(last.getId());

        assertNull(deleted);
    }
}

