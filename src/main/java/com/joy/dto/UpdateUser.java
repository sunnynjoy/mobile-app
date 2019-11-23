package com.joy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {

    @NotNull(message = "first name is mandatory")
    @NotBlank(message = "first name is mandatory")
    private String firstName;

    @NotNull(message = "last name is mandatory")
    @NotBlank(message = "last name is mandatory")
    private String lastName;

}
