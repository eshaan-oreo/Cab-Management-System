# cab-management-system

## Overview
The Inter-City Cab Management Portal is a backend system designed to help administrators efficiently manage inter-city cab services. This system includes features for cab registration, city onboarding, cab state management, booking, and insights into cab activity.

## Postman Collection
[Postman Collection Link](https://www.postman.com/descent-module-geologist-12692523/public-wspc/collection/80l8dkw/cab-management?action=share&creator=28324709)

## Features

### 1. Cab Registration
- Register cabs into the system with unique IDs and initial metadata.

### 2. City Onboarding
- Add cities where cab services will be available.

### 3. Cab Management
- **Update Location**: Change the current city (location) of any cab.
- **State Management**: Update the state of a cab based on a defined state machine:
  - Basic states include **IDLE** and **ON_TRIP**.

### 4. Cab Booking
- Book cabs based on their availability in a specific location.
- **Assignment Strategy**:
  - Assign the cab that has been idle the longest.
  - If multiple cabs are equally idle, assign one randomly.
- Assumptions:
  - A cab cannot cancel or reject a booking once assigned.

### 5. Insights
- Track how long a cab has been idle over a specified duration.
- Maintain a detailed history of each cab, including all states it has gone through.

### 6. Input
- A snapshot of all cabs, including metadata and location, in the format:
  ```
  [<Cab_Id>, <Cab_State>, <City_Id>]
  ```
- If a cab's state is **ON_TRIP**, the `City_Id` is marked as indeterminate.

## Technology Stack
- **Language**: Java 17
- **Framework**: Spring Boot 
- **Database**: H2 Database
- **Build Tool**: Maven


## Usage

1. **Initialize the System**:
   - Register cabs and onboard cities.

2. **Manage Cabs**:
   - Update cab locations and states as needed.

3. **Book Cabs**:
   - Use the booking API to assign cabs based on availability and defined rules.

4. **Generate Insights**:
   - Query the system for cab idle times and history.
