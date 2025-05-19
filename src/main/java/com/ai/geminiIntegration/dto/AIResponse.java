package com.ai.geminiIntegration.dto;

import java.util.List;

public class AIResponse {

    public static class CitationSource {
        public int startIndex;
        public int endIndex;
        public String uri;
    }

    public static class CitationMetadata {
        public List<CitationSource> citationSources;
    }

    public static class Part {
        public String text;
    }

    public static class Content {
        public List<Part> parts;
        public String role;
    }

    public static class Candidate {
        public Content content;
        public String finishReason;
        public CitationMetadata citationMetadata;
        public double avgLogprobs;
    }

    public static class PromptTokensDetail {
        public String modality;
        public int tokenCount;
    }

    public static class CandidatesTokensDetail {
        public String modality;
        public int tokenCount;
    }

    public static class UsageMetadata {
        public int promptTokenCount;
        public int candidatesTokenCount;
        public int totalTokenCount;
        public List<PromptTokensDetail> promptTokensDetails;
        public List<CandidatesTokensDetail> candidatesTokensDetails;
    }

    public List<Candidate> candidates;
    public UsageMetadata usageMetadata;
    public String modelVersion;
}
