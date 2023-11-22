package com.example.demo.util;

import com.example.demo.dto.CaregiverDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Caregiver;
import com.example.demo.model.User;

public class Merger {
    private Merger() {
    }

    public static void user(User user, UserDto userDto) {
        if (user == null || userDto == null) {
            return;
        }

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getGivenName() != null) {
            user.setGivenName(userDto.getGivenName());
        }

        if (userDto.getSurname() != null) {
            user.setSurname(userDto.getSurname());
        }

        if (userDto.getCity() != null) {
            user.setCity(userDto.getCity());
        }

        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        if (userDto.getProfileDescription() != null) {
            user.setProfileDescription(userDto.getProfileDescription());
        }

        if (userDto.getAllowContact() != null) {
            user.setAllowContact(userDto.getAllowContact());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
    }

    public static void caregiver(Caregiver caregiver, CaregiverDto caregiverDto) {
        if (caregiver == null || caregiverDto == null) {
            return;
        }

        if (caregiverDto.getPhoto() != null) {
            caregiver.setPhoto(caregiverDto.getPhoto());
        }

        if (caregiverDto.getGender() != null) {
            caregiver.setGender(caregiverDto.getGender());
        }

        if (caregiverDto.getCaregivingType() != null) {
            caregiver.setCaregivingType(caregiverDto.getCaregivingType());
        }

        if (caregiverDto.getHourlyRate() != null) {
            caregiver.setHourlyRate(caregiverDto.getHourlyRate());
        }

        if (caregiverDto.getAvailableForContact() != null) {
            caregiver.setAvailableForContact(caregiverDto.getAvailableForContact());
        }
    }
}
