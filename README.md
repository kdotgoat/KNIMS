# KNIMS
                                              
                                                  Kenya National ID Management System (KNIMS) — Project Description

Kenya National ID Management System (KNIMS) is an offline Java-based desktop application developed to streamline the process of National ID registration, renewals, and replacements for Kenyan citizens.
Providing a decentralized approach in accessing these services from your locality.
The system is designed for secure internal use by authorized staff and chiefs and ensures all operations are properly verified and documented.
Maintaining reliable communication with the applicant/citizen via sms communication.

NB:This is just a working prototype of the system.

✨ Main Features:

1. ID Registration
   
•	Capture applicant information: Full Name, Date of Birth, Place of Birth, and other details.
•	Store all applicant details securely in a MySQL database.

2. ID Renewal
   
•	Search for and renew expired IDs stored in the system.
•	Automatically update the date of expiry after renewal.
•	Renewal requests are stored in a special Renewal Request Table for record keeping.
•	Verification process before renewal is allowed.

3. ID Replacement (Lost IDs)
   
•	Handle cases of lost or damaged ID cards.
•	Allow users to submit a replacement request.
•	Verify lost ID cases against the database before approving replacement.
•	New IDs are generated upon approval and linked to the previous ID history.

4. Authentication and User Roles
   
•	Secure login system for authorized staff members and chiefs.
•	Different levels of access:
-	Staff Login: Can register, renew, and replace IDs.
-	Chief Login: Can review documents and approve or reject requests.

5. Document Submission and Approval Workflow
   
•	Applicants submit pre-filled forms (offline or manually).
•	Staff verifies details before submitting to the Chief for Approval.
•	Chief reviews the documents and approves or rejects based on verification.

6. SMS Notification System
   
•	The system sends SMS notifications to applicants upon:
-	Successful ID registration.
-	Renewal approval.
-	Replacement readiness.
•	Helps applicants stay informed about their application status even without internet access.
•	SMS is sent using a free or integrated SMS gateway service.

7. Database Integration
   
•	All data is stored securely in a MySQL database.
•	Tables include:
-	Applicant Information
-	Renewal Requests
-	Replacement Requests
-	Staff and Chief Credentials
-	Approval Logs

8. Security Features
   
•	Password-protected login for users.
•	Input validation to prevent SQL injection and data tampering.
•	Brute-force attack prevention through account lockouts after multiple failed attempts.
•	Secure database connections using best practices.
•	Secure image storage if applicant photos are involved.


9. Offline  Availability
    
•	The entire system is offline, meaning it does not rely on an internet connection for operations.
•	Only SMS sending requires minimal network access when needed.

🛠️ Technologies Used:

•	Java (Swing / JavaFX) — for building the desktop GUI.
•	MySQL — for database management.
•	JDBC — for Java-MySQL database connection.
•	Free SMS Gateway API — for sending SMS alerts.

🚀 Summary
KNIMS is a secure, efficient, and offline ID management solution tailored for Kenya's internal administrative needs. It covers all critical operations from ID registration to renewal, replacement, SMS communication, and approval workflows, ensuring reliability, transparency, and ease of management.


