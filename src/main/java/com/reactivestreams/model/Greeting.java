package com.reactivestreams.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Greeting
{
    private String message;

    private String sentBy;

    private LocalDateTime sentDataTime;


    public Greeting()
    {
    }

    public Greeting(String message, String sentBy, LocalDateTime sentDataTime)
    {
        this.message = message;
        this.sentBy = sentBy;
        this.sentDataTime = sentDataTime;
    }
}
