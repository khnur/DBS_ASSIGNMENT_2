package com.example.demo.dto;

import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDto {
    @JsonProperty("message_id")
    private Long messageId;
    @JsonProperty("message_text")
    private String messageText;
    private Timestamp timestamp;
    @JsonProperty("sender_user")
    private User senderUser;
    @JsonProperty("receiver_user")
    private User receiverUser;
}
