CREATE DATABASE IF NOT EXISTS db_ass2;
USE db_ass2;

DROP VIEW IF EXISTS job_applications_view;
DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS job_application;
DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS caregiver;
DROP TABLE IF EXISTS user;

CREATE TABLE USER (
    user_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL unique,
	given_name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    profile_description TEXT,
    allow_contact BOOLEAN DEFAULT FALSE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE CAREGIVER (
    caregiver_user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    photo BLOB,
    gender enum('MALE', 'FEMALE') NOT NULL,
    caregiving_type enum('BABYSITTER', 'CAREGIVER_FOR_ELDERLY', 'PLAYMATE_FOR_CHILDREN') NOT NULL,
    hourly_rate DECIMAL(10, 2),
    available_for_contact BOOLEAN DEFAULT TRUE,
    foreign key(user_id) REFERENCES USER(user_id)
);

CREATE TABLE MEMBER (
    member_user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    house_rules TEXT,
    foreign key(user_id) REFERENCES USER(user_id)
);

CREATE TABLE ADDRESS (
	address_id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_user_id BIGINT NOT NULL,
    house_number VARCHAR(50),
    street VARCHAR(255),
    town VARCHAR(50),
    foreign key(member_user_id) REFERENCES MEMBER(member_user_id)
);

CREATE TABLE JOB (
    job_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_user_id BIGINT,
    required_caregiving_type enum('BABYSITTER', 'CAREGIVER_FOR_ELDERLY', 'PLAYMATE_FOR_CHILDREN') NOT NULL,
    careneed_person_age INT,
    preferred_time_interval VARCHAR(100),
    caregiving_frequency enum('WEEKLY', 'DAILY', 'WEEKENDS_ONLY'),
    other_requirements TEXT,
    date_posted DATE,
    foreign key(member_user_id) REFERENCES MEMBER(member_user_id)
);

CREATE TABLE JOB_APPLICATION (
	job_application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    caregiver_user_id BIGINT,
    job_id BIGINT,
    date_applied DATE,
    foreign key(job_id) REFERENCES JOB(job_id),
    foreign key(caregiver_user_id) REFERENCES CAREGIVER(caregiver_user_id)
);

CREATE TABLE APPOINTMENT (
    appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    caregiver_user_id BIGINT,
    member_user_id BIGINT,
    appointment_date DATE,
    appointment_time TIME,
    work_hours INT,
    status enum('PENDING', 'CONFIRMED', 'DECLINED') default 'PENDING',
    member_contact VARCHAR(50),
    caregiver_contact VARCHAR(50),
    foreign key (caregiver_user_id) REFERENCES CAREGIVER(caregiver_user_id),
    foreign key (member_user_id) REFERENCES MEMBER(member_user_id)
);

CREATE TABLE MESSAGE (
    message_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_user_id BIGINT,
    receiver_user_id BIGINT,
    message_text TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    foreign key(sender_user_id) REFERENCES USER(user_id),
    foreign key(receiver_user_id) REFERENCES USER(user_id)
);

DELIMITER //

CREATE TRIGGER update_contact_trigger AFTER UPDATE ON APPOINTMENT
FOR EACH ROW
BEGIN
    IF NEW.status = 'CONFIRMED' THEN
        UPDATE APPOINTMENT
        SET member_contact = (SELECT phone_number FROM USER WHERE user_id = NEW.member_user_id),
            caregiver_contact = (SELECT phone_number FROM USER WHERE user_id = NEW.caregiver_user_id)
        WHERE appointment_id = NEW.appointment_id;
    END IF;
END;

DELIMITER //