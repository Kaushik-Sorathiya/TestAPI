# **API Test Automation Framework** 

A **Test Automation Framework** for **APIs**, built using **Rest Assured Library, Java, TestNG, and Extent Reports**.

---

## ** Features**
 **Rest Assured Library** – Automates API testing  
 **TestNG** – Manages test execution  
 **Extent Reports** – Generates detailed HTML test reports  
 **Maven** – Handles dependencies and build automation  
 **Page Object Model (POM)** – Enhances code maintainability  

---

## ** Prerequisites**
Ensure you have the following installed:
- **Java 17** → [Download Here](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Maven** → [Download Here](https://maven.apache.org/download.cgi)
- **Git** → [Download Here](https://git-scm.com/downloads)

---

## **Setup & Installation**

### **1️⃣ Clone the repository**
```sh
  git clone https://github.com/Kaushik-Sorathiya/TestAPI.git
  cd TestAPI
```

### **2️⃣ Install dependencies**
```sh
  mvn clean install
```

### **3️⃣ Run tests**
```sh
  mvn test
```

---

## ** View Test Report**
After execution, the **Extent Report** is generated at:
Example:
 `API/Test_Execution_Report_2025-03-07_17-24-58.html`

Open the **Test_Execution_Report_2025-03-07_17-24-58.html** file in a browser to view the results.

---

## ** Test Cases Implemented**

### **Test Case : API_Tests**
1. Navigate to Endpoint.
2. Get Category Details.
3. Validate Response, Name = Carbon credits.
4. Validate Response, CanRelist = true.
5. Validate Response, "Promotions" jsonArray has object "Gallery" that contains the description "Good position in category".

---

## ** Technologies Used**
- **Java**
- **RestAssured**
- **TestNG**
- **Extent Reports**
- **Maven**
- **GitHub Actions** (CI/CD Integration)

---

## ** Customize Test Execution**
Modify the **TestNG XML** to include/exclude test cases:  
 `src/test/resources/testng.xml`

---
