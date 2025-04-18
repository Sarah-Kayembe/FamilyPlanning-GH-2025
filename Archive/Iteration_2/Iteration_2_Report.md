# Individual Contribution Summary

After the meeting between group leaders, it was decided that patient data would be generated and stored in a file. However, by the time that decision was made, I had already started working on a PostgreSQL-backed version of the project. I created several Java classes to support database operations and began testing them.

## Files I Created

### Entity Classes:
- `Client.java`
- `LocationDetails.java`
- `MethodsOfChoice.java`
- `MonthlyVisit.java`

### DAO (Data Access Object) Classes:
- `ClientDao.java`
- `LocationDetailsDao.java`
- `MethodsOfChoiceDao.java`
- `MonthlyVisitDAO.java`

### JUnit Test Classes:
- `ClientDAOTest.java`
- `LocationDetailsDAOTest.java`
- `MethodsOfChoiceDAOTest.java`
- `MonthlyVisitDAOTest.java`

### Database Connector:
- `DatabaseConnector.java`

These classes allowed me to interact with a PostgreSQL database using SQL queries directly from Java, including inserting, updating, retrieving, and deleting records. I used JUnit to test the functionality of each DAO class and verified that the basic database operations were working as expected.

Despite the project's overall shift away from database utilization, I maintained and included my database implementation as an individual contribution. Consequently, to align with this evolving landscape, we adjusted our subsequent direction. The individual contributions of each team pair are as follows:

---

## Pair 2

### Sarah L
Sarah was responsible for generating patient reports and testing their output. She created the core files for this feature:
- `PatientReport.java`
- `PatientGenerator.java`
- `PatientReportHeaderGenerator.java`
- `PatientReportTest.java`

These components work together to gather patient information and format it into a CSV file. Using JUnit, Sarah tested this functionality and successfully generated two example output files:
- `patient_report_04_2025.csv` (single patient)
- `patient_report_04_2025_Multi.csv` (multiple entries)

Her work ensures the patient reporting system can reliably produce structured reports based on real data.

### Michael
Michael contributed to the appointment scheduling features of the system. He implemented:
- `Appointment` class
- `AppointmentService` class
- `ContraceptiveMethod` enum

These components support creating, canceling, and updating appointments. He also created:
- `AppointmentTest.java`
- `AppointmentServiceTest.java`

Additionally, Michael began exploring the design of a comprehensive `Patient` class and interface to be used across the system.

---

## Pair 3

### Ethan
Ethan focused on building the monthly report generation system. He created four dedicated classes:
- `HeaderGenerator`: Handles clinic/district details
- `StockDataGenerator`: Manages rows 1–8 of stock data
- `FundsCEDISGenerator`: Handles financial data in rows 9–12c
- `AcceptorGenerator`: Covers visit data in lines 13–15

This system generates a CSV file and fills in all fields that do not require direct input. Totals and calculations are automated. Ethan also collaborated with Ben on integrating the `InventoryService`.

### Ben
Ben developed the `InventoryService` class to manage stock within the system. This class:
- Tracks unique inventory items and counts
- Allows stock adjustments (transfers, acquisitions, expirations)
- Captures inventory snapshots

He plans to enhance this service by adding logging for state changes. This will enable automated monthly reports by comparing inventory states over time.
Ben has started the refactoring of the domain model diagram to mmirror the changes we have made.

---

