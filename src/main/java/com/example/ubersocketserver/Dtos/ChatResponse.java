package com.example.ubersocketserver.Dtos;


import lombok.*;
import org.springframework.messaging.handler.annotation.SendTo;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    public String name;
    public String message;
}
