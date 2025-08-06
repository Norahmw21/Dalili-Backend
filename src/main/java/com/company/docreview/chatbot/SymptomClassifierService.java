package com.company.docreview.chatbot;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SymptomClassifierService {

    private final Map<String, String> symptomMap = Map.ofEntries(
            Map.entry("throat", "ENT"),
            Map.entry("ear", "ENT"),
            Map.entry("fever", "General Medicine"),
            Map.entry("cough", "Pulmonology"),
            Map.entry("chest", "Cardiology"),
            Map.entry("rash", "Dermatology"),
            Map.entry("stomach", "Gastroenterology"),
            Map.entry("anxiety", "Psychiatry"),
            Map.entry("joint", "Orthopedics")
    );

    public String classify(String message) {
        String lower = message.toLowerCase();
        for (Map.Entry<String, String> entry : symptomMap.entrySet()) {
            if (lower.contains(entry.getKey())) {
                return "It sounds like you might need to visit the " + entry.getValue() + " clinic for \"" + entry.getKey() + "\" symptoms.";
            }
        }
        return "Sorry, we couldn't determine your issue. Please consult a General Physician.";
    }
}
