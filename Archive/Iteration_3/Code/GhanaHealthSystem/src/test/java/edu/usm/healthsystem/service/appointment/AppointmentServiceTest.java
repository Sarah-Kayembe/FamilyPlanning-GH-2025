package edu.usm.healthsystem.service.appointment;

import edu.usm.healthsystem.model.client.Employee;
import edu.usm.healthsystem.model.client.EmployeeType;
import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.Appointment;
import edu.usm.healthsystem.model.familyplanning.ContraceptiveMethod;
import edu.usm.healthsystem.model.familyplanning.FamilyPlanningPatient;
import edu.usm.healthsystem.model.familyplanning.Item;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

class AppointmentServiceTest {

    private FamilyPlanningPatient createSamplePatient(String username) {
        return new FamilyPlanningPatient(
                username,                      // username
                "Test",                        // name
                "Patient",                     // lastName
                "hashedpass",                  // passwordHash
                "2025-01-01",                  // date
                "NHIS123",                     // nhisNumber
                "CARD123",                     // cardNumber
                "Single",                      // maritalStatus
                "Female",                      // sex
                "123 Street, Community A",     // address
                28,                            // age
                "None",                        // medicalHistory
                true,                          // firstUse
                "Injection",                   // methodOfChoice
                2,                             // parity
                new HashMap<>()                // monthlyUsage
        );
    }

    @Test
    public void testCreateAppointmentSuccess() {
        AppointmentService service = new AppointmentService();
        Employee nurse = new Employee("nurse01", "Jane", "Doe", "hashedpwd", EmployeeType.NURSE, "E001");
        FamilyPlanningPatient patient = createSamplePatient("patient01");

        LocalDateTime appointmentDate = LocalDateTime.of(2025, 4, 15, 10, 0);
        Appointment appt = service.createAppointment(
                nurse, patient, appointmentDate, ContraceptiveMethod.INJECTION, Optional.of("Initial visit"));

        assertNotNull(appt);
        assertEquals("E001", appt.getEmployeeId());
        assertEquals("Injectable Contraceptive", appt.getContraceptiveMethod().getDescription());
        assertTrue(appt.isActive());

        // Check monthly usage
        Item usage = patient.getMonthlyUsage().get(Month.APRIL);
        assertNotNull(usage);
        assertEquals("Injectable Contraceptive", usage.getName());
        assertEquals(1, usage.getAmount());
    }

    @Test
    public void testCreateAppointmentUnauthorizedThrows() {
        AppointmentService service = new AppointmentService();

        // System Admins should be unauthorized to create an appointment
        Employee nurse = new Employee("nurse01", "Jane", "Doe", "hashedpwd", EmployeeType.SYSTEM_ADMIN, "E001");

        // Sample patient
        FamilyPlanningPatient patient = createSamplePatient("patient02");

        // Assert that SecurityException is thrown
        SecurityException ex = assertThrows(SecurityException.class, () -> {
            service.createAppointment(
                    nurse, patient, LocalDateTime.now(), ContraceptiveMethod.PILL, Optional.empty());
        });

        // Check if the exception message matches
        assertEquals("Employee not authorized to manage appointments", ex.getMessage());
    }


    @Test
    public void testUpdateAppointmentSuccess() {
        AppointmentService service = new AppointmentService();
        Employee nurse = new Employee("nurse02", "Kelly", "Wong", "pwdhash", EmployeeType.NURSE, "E003");
        FamilyPlanningPatient patient = createSamplePatient("patient03");

        LocalDateTime originalDate = LocalDateTime.of(2025, 4, 20, 9, 0);
        service.createAppointment(nurse, patient, originalDate, ContraceptiveMethod.PILL, Optional.of("Initial"));

        LocalDateTime newDate = LocalDateTime.of(2025, 4, 25, 9, 0);
        Appointment updated = service.updateAppointment(
                nurse, patient, originalDate, newDate, Optional.of(ContraceptiveMethod.INJECTION), Optional.of("Rescheduled"));

        assertEquals(newDate, updated.getAppointmentDate());
        assertEquals("Injectable Contraceptive", updated.getContraceptiveMethod().getDescription());
        assertEquals("Rescheduled", updated.getNotes().get());
    }

    @Test
    public void testCancelAppointmentSuccess() {
        AppointmentService service = new AppointmentService();
        Employee nurse = new Employee("nurse04", "Sam", "Lee", "pwdhash", EmployeeType.NURSE, "E004");
        FamilyPlanningPatient patient = createSamplePatient("patient04");

        LocalDateTime apptDate = LocalDateTime.of(2025, 5, 1, 11, 0);
        service.createAppointment(nurse, patient, apptDate, ContraceptiveMethod.INJECTION, Optional.empty());
        service.cancelAppointment(nurse, patient, apptDate);

        List<Appointment> active = service.getPatientAppointments(nurse, patient);
        assertTrue(active.isEmpty());
    }

    @Test
    public void testGetAlertsReturnsUpcoming() {
        AppointmentService service = new AppointmentService();
        Employee nurse = new Employee("nurse07", "Nina", "Gray", "pwdhash", EmployeeType.NURSE, "E007");
        FamilyPlanningPatient patient = createSamplePatient("patient07");

        LocalDateTime apptDate = LocalDateTime.of(2025, 4, 10, 10, 0);
        Appointment appt = service.createAppointment(nurse, patient, apptDate, ContraceptiveMethod.INJECTION, Optional.empty());
        appt.calculateNextDose(5); // Assume your Appointment has this method

        List<String> alerts = service.getAlerts(nurse, LocalDateTime.of(2025, 4, 13, 9, 0));
        assertEquals(1, alerts.size());
        assertTrue(alerts.get(0).contains("Reminder"));
    }

}