package com.example.psychology.Comman;

import lombok.Data;

@Data
public class ChatSession {
    private String fromName;
    private String fromId;
    private String toName;
    private String toId;
}
