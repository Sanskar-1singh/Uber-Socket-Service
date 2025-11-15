package com.example.ubersocketserver.controller;

import com.example.ubersocketserver.Dtos.ChatRequest;
import com.example.ubersocketserver.Dtos.ChatResponse;
import com.example.ubersocketserver.Dtos.TestRequest;
import com.example.ubersocketserver.Dtos.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    private SimpMessagingTemplate template;

    public TestController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse pingCheck(TestRequest testRequest) {
        TestResponse testResponse =TestResponse.builder().data(testRequest.data).build();
        return testResponse;
    }


//     @Scheduled(fixedRate = 1000)
//     public void scheduled() {
//        System.out.println("Scheduled");
//         template.convertAndSend("/topic/ping", "hello");
//     }

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public ChatResponse chatMessage(ChatRequest  chatRequest) {
        System.out.println("helloo");
      ChatResponse chatResponse=ChatResponse.builder()
              .name(chatRequest.getName())
              .message(chatRequest.getMessage())
              .build();
        return chatResponse;
    }
}
