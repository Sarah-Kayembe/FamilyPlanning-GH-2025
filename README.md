# Digital Family Planning Register for Ghana Health Service

## Project Description

This project aims to replace Ghana’s paper-based family planning register with a digital system. The platform is designed to:

- Help nurses efficiently track family planning services.
- Simplify the transition to new yearly forms.
- Automatically generate Monthly Family Planning reports.

The system improves accuracy, speeds up record-keeping, and enhances data accessibility across the Ghana Health Service.

---

## Table of Contents

- [Project Description](#-project-description)
- [Demo](#-demo)
- [Technologies Used](#-technologies-used)
- [Team Responsibility](#-team-responsibility)
- [AI Use During Project](#-ai-use-during-project)
- [Iterations](#-iterations)
  - [Iteration 1: Planning & Prototype](#-iteration-1-planning--prototype)
  - [Iteration 2: Feature Expansion & Form Automation](#-iteration-2-feature-expansion--form-automation)
  - [Iteration 3: Finalization & Deployment](#-iteration-3-finalization--deployment)
- [Installation & Usage](#-installation--usage)

---

## Technologies Used

- **Java 17**
- **Maven** - Dependency management and build tool
- **Lombok** - Reduces boilerplate code
- **JUnit** - For unit testing (planned implementation)
- **Java Swing** - For user interface

---

## Team Responsibility

| Team Member      | Responsibilities                                         |
|------------------|----------------------------------------------------------|
| Sarah Kayembe    | Group Leader: Handle iteration reports and make sure group is prioritizing deliverables each iteration |
| Ethan Gilles     | Team B: Inventory service and monthly report generation  |
| Ben Gaudreau     | Team B: Inventory service and monthly report generation  |
| Sarah Lawrence   | Team A: Patient service and patient report generation    |
| Michael Yattaw   | Team A: Patient service and patient report generation    |

The team collaborated using GitHub for version control, weekly sprints to prioritize tasks, and asynchronous messaging to stay in sync across different schedules.

---

## AI Use During Project

We used AI tools responsibly to accelerate development while maintaining human oversight:

- **ChatGPT**: Assisted with code generation, debugging and writing documentation drafts.
- **DeepSeek**: Assisted with code generation, and debugging Java Swing, used for UI.

All AI-generated suggestions were reviewed and modified by team members to ensure they aligned with the use cases.

---

## Iteration 1: Planning & Prototype

**Goals:**
- Understand the structure and workflow of the paper register.
- Identify essential data fields and relationships.
- Create draft code and documentation for our domain.

**Progress:**
- Developed prototype monthly report.
- Built initial class structure using Java, Maven and lombok.
- Developed the first iteration of the Domain model.

**Challenges:**
- Mapping paper workflows into intuitive digital forms.
- Creating the correct use cases and slimming down the domain model.

---

## Iteration 2: Feature Expansion & Form Automation

**Goals:**
- Implement logic for patient entry
- Add report generation for monthly summaries.
- Introduce the inventory service to keep track of contraceptives.

**Progress:**
- Created first version of the Monthly Family Planning report.
- Created first version of the Patient Report.
- Tested form submission logic and edge cases.

**Challenges:**
- Matching Ministry of Health reporting standards.
- Finding time for pair programming.

---

## Iteration 3: Finalization & Deployment

**Goals:**
- Generate UI
- Connect UI to the back-end logic
- Create presentation for our final report.

**Progress:**
- Final UI with buttons for every use case decided on.
- Automated monthly reports with export options.
- Presentation that shared all of our roles and responsibilities

**Challenges:**
- Coordinating with everyone to make sure deliverables were on time.
- Simulating realistic data for demo/testing without PHI.

---

## Installation & IDE Setup

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

