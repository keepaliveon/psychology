package com.example.psychology.web;

import com.example.psychology.service.SubscribeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {

    @Resource
    private SubscribeService subscribeService;
}
