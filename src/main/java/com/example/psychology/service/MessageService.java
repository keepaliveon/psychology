package com.example.psychology.service;

import com.example.psychology.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageService {

    @Resource
    private MessageRepository messageRepository;

}
