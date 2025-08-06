package com.company.docreview.chatbot;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatbotController {

    private final SymptomClassifierService symptomClassifierService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String reply = symptomClassifierService.classify(request.getMessage());
        return new ChatResponse(reply);
    }
}
