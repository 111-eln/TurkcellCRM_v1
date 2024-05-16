package com.turkcell.TurkcellCRM.customerService.dtos.response.create;
import com.turkcell.TurkcellCRM.commonPackage.Gender;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndividualCustomerCreatedEvent {
    private String firstName;
    private String secondName;
    private String lastName;
    private LocalDateTime birthDate;
    private Gender gender;
    private String fatherName;
    private String motherName;
    private String nationalityNumber;
}