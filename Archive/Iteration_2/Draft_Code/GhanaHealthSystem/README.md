# Ghana Health System

## Overview

The Ghana Health System is a Java-based application designed to manage healthcare operations for medical facilities in Ghana. It provides functionality for patient management, employee access control, inventory tracking, and reporting.

## Features

- **User Authentication**: Secure login for patients and healthcare workers
- **Patient Management**: Maintain patient records and medical history
- **Employee Management**: Different access levels for nurses, supervisors, and administrators
- **Inventory Tracking**: Monitor medical supplies and equipment
- **Reporting**: Generate patient-specific and monthly summary reports

## Technologies

- **Java 17**
- **Maven** - Dependency management and build tool
- **Lombok** - Reduces boilerplate code
- **JUnit** - For unit testing (planned implementation)

## Getting Started

### Prerequisites

- Java JDK 17 or later
- Maven 3.6.0 or later
- IDE of your choice (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repository/ghana-health-system.git

## IDE Setup

### IntelliJ IDEA
1. **Open Project**:
   - Launch IntelliJ
   - Select "Open" from the welcome screen
   - Navigate to your project folder and select the `pom.xml` file
   - Choose "Open as Project"

2. **Configure JDK**:
   - Go to "File" > "Project Structure" > "Project"
   - Set "Project SDK" to Java 17
   - Set "Project language level" to 17

3. **Enable Lombok**:
   - Install the Lombok plugin (Marketplace > search "Lombok")
   - Enable annotation processing:
      - "Settings" > "Build, Execution, Deployment" > "Compiler" > "Annotation Processors"
      - Check "Enable annotation processing"

4. **Build Project**:
   - Right-click on project > "Maven" > "Reload Project"
   - Wait for dependencies to download

### Eclipse
1. **Import Project**:
   - Launch Eclipse
   - Go to "File" > "Import" > "Maven" > "Existing Maven Projects"
   - Browse to your project folder
   - Ensure the `pom.xml` is selected and click "Finish"

2. **Install Lombok**:
   - Download lombok.jar from [projectlombok.org](https://projectlombok.org/download)
   - Double-click the jar and follow installer instructions
   - Restart Eclipse when prompted

3. **Configure Java**:
   - Right-click project > "Properties"
   - "Java Compiler":
      - Enable "Enable project specific settings"
      - Set "Compiler compliance level" to 17
   - "Java Build Path":
      - Ensure JRE System Library is Java 17

4. **Resolve Dependencies**:
   - Right-click project > "Maven" > "Update Project"
   - Check "Force Update of Snapshots/Releases"
   - Click "OK"