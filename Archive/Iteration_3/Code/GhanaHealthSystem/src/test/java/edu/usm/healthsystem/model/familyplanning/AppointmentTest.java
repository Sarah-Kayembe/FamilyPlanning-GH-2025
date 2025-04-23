package edu.usm.healthsystem.model.familyplanning;

import edu.usm.healthsystem.model.client.Employee;
import edu.usm.healthsystem.model.client.EmployeeType;
import edu.usm.healthsystem.model.client.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    private FamilyPlanningPatient patient;
    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        patient = new FamilyPlanningPatient();
        patient.setName("Jane");
        patient.setLastName("Doe");
        appointment = new Appointment(
                patient,
                "EMP123",
                LocalDateTime.of(2025, Month.APRIL, 13, 10, 0),
                ContraceptiveMethod.INJECTION
        );
    }

    @Test
    public void testAppointmentStoresEmployeeIdCorrectly() {
        FamilyPlanningPatient patient = new FamilyPlanningPatient();
        patient.setName("Patient");
        patient.setLastName("LastName");

        Employee employee = new Employee(
                "nurse001", "Nurse", "LastName", "password",
                EmployeeType.NURSE, "EMP001"
        );

        Appointment appointment = new Appointment(
                patient,
                employee.getEmployeeId(),
                LocalDateTime.of(2025, 4, 13, 10, 0),
                ContraceptiveMethod.INJECTION
        );

        assertEquals("EMP001", appointment.getEmployeeId());
    }

    @Test
    public void testCalculateNextDoseUpdatesDateAndPatientMethod() {
        appointment.calculateNextDose(30);
        assertTrue(appointment.getNextDoseDate().isPresent());
        assertEquals(LocalDateTime.of(2025, Month.MAY, 13, 10, 0), appointment.getNextDoseDate().get());
        assertEquals("Injectable Contraceptive", patient.getMethodOfChoice());
    }

    @Test
    public void testGenerateAlertReturnsReminderWhenNextDoseIsSoon() {
        FamilyPlanningPatient patient = new FamilyPlanningPatient();
        patient.setName("Jane");
        patient.setLastName("Doe");

        Appointment appointment = new Appointment(
                patient,
                "EMP001",
                LocalDateTime.of(2025, Month.APRIL, 5, 9, 0),  // fixed appointment date
                ContraceptiveMethod.INJECTION
        );

        appointment.calculateNextDose(5); // nextDose = April 10

        Optional<String> alert = appointment.generateAlert(LocalDateTime.of(2025, Month.APRIL, 9, 9, 0));

        assertTrue(alert.isPresent());
        assertTrue(alert.get().contains("Reminder: Patient Jane Doe needs Injectable Contraceptive follow-up"));
    }

    @Test
    public void testCancelDeactivatesAppointment() {
        appointment.cancel();
        assertFalse(appointment.isActive());
        assertEquals(Optional.empty(), appointment.generateAlert(LocalDateTime.now()));
    }

    @Test
    public void testCalculateNextDoseThrowsExceptionForInvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> appointment.calculateNextDose(0));
    }

}
