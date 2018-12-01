package com.reactivestreams.publisher;

import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class GreetingPublisher<T> implements Publisher<T>
{
    private Subscriber subscriber;
    private SubmissionPublisher<T> submissionPublisher;

    public GreetingPublisher(SubmissionPublisher<T> submissionPublisher)
    {
        this.submissionPublisher=submissionPublisher;
    }

    @Override
    public void subscribe(Subscriber<? super T> subscriber)
    {
        this.subscriber=subscriber;
    }

    public void submitItem(T item)
    {
        submissionPublisher.submit(item);
    }

    public void close()
    {
        submissionPublisher.close();
    }

}

