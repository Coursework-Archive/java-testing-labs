package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarShould {

    @Test
    void allowEntryOfAnAppointment() {

        ClinicCalendar calendar = new ClinicCalendar();
        calendar.addAppointment("Brittany", "Bales", "avery",
                "09/17/2024 2:00 pm");

        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);
        assertEquals("Brittany", enteredAppt.getPatientFirstName());
        assertEquals("Bales", enteredAppt.getPatientLastName());
        assertSame(Doctor.avery, enteredAppt.getDoctor());
        assertEquals("9/17/2024 02:00 PM",
                enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyy hh:mm a")));

    }

    @Test
    void returnTrueForHasAppointmentIfThereAreAppointments() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        calendar.addAppointment("Brittany", "Bales", "avery",
                "09/17/2024 2:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2024, 9, 17)));
    }

    @Test
    void returnFalseForHasAppointmentIfThereAreNoAppointment() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
        assertFalse(calendar.hasAppointment(LocalDate.of(2024, 9, 17)));
    }

}