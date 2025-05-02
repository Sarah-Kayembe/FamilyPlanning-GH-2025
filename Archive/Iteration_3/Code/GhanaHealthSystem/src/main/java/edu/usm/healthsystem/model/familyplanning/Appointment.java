package edu.usm.healthsystem.model.familyplanning;

import edu.usm.healthsystem.model.client.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * Represents a contraceptive appointment for a patient.
 * Stores details such as patient information, appointment date, contraceptive method,
 * follow-up dose information, and appointment status.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    /** Reference to the patient associated with the appointment. */
    private FamilyPlanningPatient patient;

    /** ID of the employee managing the appointment. */
    private String employeeId;

    /** Date and time of the appointment. */
    private LocalDateTime appointmentDate;

    /** Type of contraceptive method being used. */
    private ContraceptiveMethod contraceptiveMethod;

    /** Optional date for the next dose, if applicable. */
    private Optional<LocalDateTime> nextDoseDate;

    /** Optional additional notes related to the appointment. */
    private Optional<String> notes;

    /** Optional clinic identifier for where the appointment is held. */
    private Optional<String> clinicId;

    /** Timestamp of when the appointment record was created. */
    private LocalDateTime createdAt;

    /** Status indicating whether the appointment is currently active. */
    private boolean isActive;

    /**
     * Constructs a new Appointment with the given required information.
     * Optional fields are initialized as empty, createdAt is set to now, and isActive is true.
     *
     * @param patient The patient associated with the appointment.
     * @param employeeId The ID of the employee managing the appointment.
     * @param appointmentDate The date and time of the appointment.
     * @param contraceptiveMethod The contraceptive method chosen for the appointment.
     */
    public Appointment(FamilyPlanningPatient patient, String employeeId, LocalDateTime appointmentDate,
                       ContraceptiveMethod contraceptiveMethod) {
        this(patient, employeeId, appointmentDate, contraceptiveMethod, Optional.empty(),
                Optional.empty(), Optional.empty(), LocalDateTime.now(), true);
    }

    /**
     * Calculates and sets the next dose date based on the duration from the appointment date.
     * Also updates the patient's method of choice to reflect the current contraceptive method.
     *
     * @param durationInDays Number of days until the next dose.
     * @throws IllegalArgumentException if the duration is not positive.
     */
    public void calculateNextDose(int durationInDays) {
        if (durationInDays <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.nextDoseDate = Optional.of(this.appointmentDate.plusDays(durationInDays));
        if (patient != null) {
            patient.setMethodOfChoice(this.contraceptiveMethod.getDescription());
        }
    }

    /**
     * Generates an alert message if a follow-up is due within 7 days of the current date.
     *
     * @param currentDate The current date to compare with the next dose date.
     * @return An Optional containing the alert message, or empty if no alert is needed.
     */
    public Optional<String> generateAlert(LocalDateTime currentDate) {
        if (!isActive || !nextDoseDate.isPresent()) {
            return Optional.empty();
        }
        LocalDateTime nextDose = nextDoseDate.get();
        if (nextDose.isAfter(currentDate) && nextDose.isBefore(currentDate.plusDays(7))) {
            return Optional.of(String.format(
                    "Reminder: Patient %s %s needs %s follow-up by %s",
                    patient.getName(), patient.getLastName(),
                    contraceptiveMethod.getDescription(), nextDose.toLocalDate()));
        }
        return Optional.empty();
    }

    /**
     * Cancels the appointment by marking it as inactive.
     */
    public void cancel() {
        this.isActive = false;
    }

}