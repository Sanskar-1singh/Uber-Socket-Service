package com.example.ubersocketserver.Dtos;


import lombok.*;

import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequestDto {

    private String bookingStatus;
    private Optional<Long> driverId;

}
