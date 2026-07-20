# MotorPH Payroll Management System

**Course:** Computer Programming 2 (Terminal Assessment Submission)  
**Section:** S1101 — Group 20

---

## Team Roster & Roles
* **Glen Romero** — Frontend Lead / Swing UI Designer
* **Dianna Cathlene De Leon** — QA Lead / Data Integration Engineer
* **Raymond Ballaran** — System Tester / Core Logic Validator
* **Ainy Julienne Manuel** — Feature Documenter / Compliance Reviewer
* **Nikko Pangilinan** — System Validator / Maintenance Support

---

## Milestone 2 Project Architecture Upgrades
We have completely shifted the core architecture from temporary, volatile RAM arrays into a permanent, file-based persistence engine. The application fully satisfies features 1, 2, 3, and 4 outlined in the MotorPH Milestone 2 Grading Rubric.

### Backend & Storage Enhancements
* **Persistent CSV Engine (`EmployeeManager.java`):** Engineered a clean file I/O layer utilizing `BufferedReader` and `PrintWriter` to perform absolute disk reads/writes. Stored records securely persist inside `employee_data.csv`.
* **Robust CRUD Lifecycle:**
  * **Create:** Enforces strict **Duplicate ID Validation Check** prior to file writing, automatically intercepting conflicting IDs and alerting the user via `JOptionPane`.
  * **Read:** Features an un-crashable static initializer with a self-healing fallback mechanism that automatically auto-generates the complete database seed structure if missing.
  * **Update:** Integrated a comprehensive `saveAllToCSV()` disk-sync trigger to ensure edits to employee names, departments, or salaries permanently persist through system restarts.
  * **Delete:** Built clean structural row purging that cleanly filters records directly inside the spreadsheet.

### Frontend & Calculation Engine (`PayrollGUI.java` & `MainMenuGUI.java`)
* **Dynamic Roster Integration:** Replaced open-text boxes with an automated, live database-driven `JComboBox` dropdown dropdown menu.
* **Dynamic Rate × Days Pay Calculator:** Upgraded the payroll module from a static flat calculator to a true operational engine. It reads individual basic salary metrics from the database, extracts custom daily rates, and maps them against entering **Days Worked** inputs.
* **Statutory Compliance Math:** Fully processes Gross Pay against graduation brackets for SSS, PhilHealth (3%), fixed Pag-IBIG thresholds, and computes accurate, localized Withholding Tax deductions.

---

## Quality Assurance & Engineering Traceability
To ensure 100% stable input validation and un-crashable performance, all modules were validated against rigorous test specifications:

* **Automated Data Type Defenses:** System actively catches inputs and traps `NumberFormatException` blocks, replacing potential application crashes with friendly alert popups.
* **QA Master Matrix Log:** Complete integration test passes have been captured inside our formal verification sheet: `Group20_GUI_QA_TestReport.xlsx`.

---

## Project Tracking & Documentation Receipts

* ** Live Change Requests Log:** [MotorPH Group 20 Spreadsheet Tracking](https://docs.google.com/spreadsheets/d/1tZTqc68DXY_cBcVGj-VQMnkbGGPXVrsCTr-xuo8ag1M/edit?usp=sharing)
* ** Live System Workflow Screenshots Guide:** [Google Docs Visual Verification Run](https://docs.google.com/document/d/1N_f_M1iS1kX2G-U6vU-gV-lK2W_YjX4Y_ZzR-U6vU-g/edit?usp=sharing) *(Note: Replace with your actual Google Doc link if different)*

---
*Developed as a collective submission for Computer Programming 2. All rights reserved.*
