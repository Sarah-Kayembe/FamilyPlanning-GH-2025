package edu.usm.healthsystem.service.appointment;

import edu.usm.healthsystem.model.familyplanning.Item;
import edu.usm.healthsystem.model.client.Employee;
import edu.usm.healthsystem.model.client.EmployeeType;
import edu.usm.healthsystem.model.client.Patient;
import edu.usm.healthsystem.model.familyplanning.Appointment;
import edu.usm.healthsystem.model.familyplanning.ContraceptiveMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing contraceptive appointments.
 * Includes methods for creating, updating, canceling, retrieving, and generating alerts
 * for appointments. Assumes in-memory storage (replace with database for production use).
 */
public class AppointmentService {

    /** List of appointments. */
    private List<Appointment> appointments;

    /**
     * Constructs an AppointmentService with an empty appointment list.
     */
    public AppointmentService() {
        this.appointments = new ArrayList<>();
    }

    /**
     * Creates a new appointment for a patient.
     * Only employees with permission (nurses) can create appointments.
     *
     * @param employee The employee attempting to create the appointment.
     * @param patient The patient for whom the appointment is created.
     * @param appointmentDate The scheduled date and time of the appointment.
     * @param method The contraceptive method used in the appointment.
     * @param notes Optional notes associated with the appointment.
     * @return The created Appointment object.
     * @throws SecurityException if the employee is not authorized.
     */
    public Appointment createAppointment(
            Employee employee, Patient patient, LocalDateTime appointmentDate,
            ContraceptiveMethod method, Optional<String> notes) {
        if (!canManageAppointments(employee)) {
            throw new SecurityException("Employee not authorized to manage appointments");
        }
        Appointment appointment = new Appointment(
                patient, employee.getEmployeeId(), appointmentDate, method,
                Optional.empty(), notes, Optional.empty(), LocalDateTime.now(), true);
        appointments.add(appointment);

        // Update patient's monthly usage for reporting
        patient.setMonthlyUsage(
                appointmentDate.getMonth(),
                new Item(method.getDescription(), 1)
        );
        return appointment;
    }

    /**
     * Updates an existing appointment.
     * Allows rescheduling the appointment, changing the method, or updating notes.
     *
     * @param employee The employee requesting the update.
     * @param patient The patient whose appointment is being updated.
     * @param oldAppointmentDate The original date of the appointment.
     * @param newAppointmentDate The new date, if being changed.
     * @param newMethod Optional new contraceptive method.
     * @param newNotes Optional new notes to replace existing ones.
     * @return The updated Appointment object.
     * @throws SecurityException if the employee is not authorized.
     * @throws IllegalArgumentException if the appointment cannot be found.
     */
    public Appointment updateAppointment(
            Employee employee, Patient patient, LocalDateTime oldAppointmentDate,
            LocalDateTime newAppointmentDate, Optional<ContraceptiveMethod> newMethod,
            Optional<String> newNotes) {
        if (!canManageAppointments(employee)) {
            throw new SecurityException("Employee not authorized to manage appointments");
        }
        Optional<Appointment> appointmentOpt = findAppointment(patient, oldAppointmentDate);
        if (appointmentOpt.isEmpty()) {
            throw new IllegalArgumentException("Appointment not found");
        }
        Appointment appointment = appointmentOpt.get();
        if (newAppointmentDate != null) {
            appointment.setAppointmentDate(newAppointmentDate);
        }
        newMethod.ifPresent(appointment::setContraceptiveMethod);
        newNotes.ifPresent(notes -> appointment.setNotes(Optional.of(notes)));
        return appointment;
    }

    /**
     * Cancels an appointment.
     * The appointment is marked inactive rather than removed.
     *
     * @param employee The employee attempting to cancel the appointment.
     * @param patient The patient associated with the appointment.
     * @param appointmentDate The date of the appointment to cancel.
     * @throws SecurityException if the employee is not authorized.
     * @throws IllegalArgumentException if the appointment is not found.
     */
    public void cancelAppointment(Employee employee, Patient patient, LocalDateTime appointmentDate) {
        if (!canManageAppointments(employee)) {
            throw new SecurityException("Employee not authorized to manage appointments");
        }
        Optional<Appointment> appointmentOpt = findAppointment(patient, appointmentDate);
        if (!appointmentOpt.isPresent()) {
            throw new IllegalArgumentException("Appointment not found");
        }
        appointmentOpt.get().cancel();
    }

    /**
     * Retrieves a list of active appointments for a given patient.
     *
     * @param employee The employee requesting to view appointments.
     * @param patient The patient whose appointments are being viewed.
     * @return A list of active Appointment objects.
     * @throws SecurityException if the employee is not authorized.
     */
    public List<Appointment> getPatientAppointments(Employee employee, Patient patient) {
        if (!canManageAppointments(employee)) {
            throw new SecurityException("Employee not authorized to view appointments");
        }
        return appointments.stream()
                .filter(a -> a.getPatient().equals(patient) && a.isActive())
                .collect(Collectors.toList());
    }

    /**
     * Generates alerts for all patients with upcoming doses within 7 days.
     *
     * @param employee The employee requesting the alerts.
     * @param currentDate The current date to base alert generation on.
     * @return A list of alert messages.
     * @throws SecurityException if the employee is not authorized.
     */
    public List<String> getAlerts(Employee employee, LocalDateTime currentDate) {
        if (!canManageAppointments(employee)) {
            throw new SecurityException("Employee not authorized to view alerts");
        }
        return appointments.stream()
                .filter(Appointment::isActive)
                .map(a -> a.generateAlert(currentDate))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * Checks if the employee has permission to manage appointments.
     * Only nurses are allowed to manage appointments.
     *
     * @param employee The employee to validate.
     * @return True if the employee is a nurse, otherwise false.
     */
    private boolean canManageAppointments(Employee employee) {
        return employee != null && employee.getEmployeeType() == EmployeeType.NURSE;
    }

    /**
     * Finds an active appointment for a patient on a specific date.
     *
     * @param patient The patient whose appointment is being searched.
     * @param appointmentDate The appointment date to look for.
     * @return An Optional containing the found appointment, or empty if not found.
     */
    private Optional<Appointment> findAppointment(Patient patient, LocalDateTime appointmentDate) {
        return appointments.stream()
                .filter(a -> a.getPatient().equals(patient)
                        && a.getAppointmentDate().equals(appointmentDate)
                        && a.isActive())
                .findFirst();
    }

}