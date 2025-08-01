package com.remindpay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmsRequest {
    private List<Message> messages;

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

    public static class Message {
        private List<Destination> destinations;
        private String from;
        private String text;

        public List<Destination> getDestinations() { return destinations; }
        public void setDestinations(List<Destination> destinations) { this.destinations = destinations; }

        public String getFrom() { return from; }
        public void setFrom(String from) { this.from = from; }

        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }

    public static class Destination {
        private String to;

        public String getTo() { return to; }
        public void setTo(String to) { this.to = to; }
    }
}