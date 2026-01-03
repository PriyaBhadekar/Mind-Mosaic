package com.CapstoneProject.MindMosaic.service;

import com.CapstoneProject.MindMosaic.entity.SentimentType;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public SentimentType analyzeSentiment(String text) {

        if (text == null || text.isBlank()) {
            return SentimentType.NEUTRAL;
        }

        String lower = text.toLowerCase();

        if (lower.contains("sad") || lower.contains("tired") || lower.contains("angry")) {
            return SentimentType.NEGATIVE;
        }

        if (lower.contains("happy") || lower.contains("good") || lower.contains("fine")) {
            return SentimentType.POSITIVE;
        }

        return SentimentType.NEUTRAL;
    }
}
