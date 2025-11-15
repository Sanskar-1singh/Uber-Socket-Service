package com.example.ubersocketserver.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;

@Getter
@SendTo
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {

 private String name;
 private String message;
}
