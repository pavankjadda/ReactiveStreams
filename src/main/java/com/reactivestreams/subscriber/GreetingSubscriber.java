package com.reactivestreams.subscriber;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.*;


public class GreetingSubscriber<T> implements Subscriber<T>
{
    private Subscription subscription;
    private List<T> consumedMessages=new LinkedList<>();

    @Override
    public void onSubscribe(Subscription subscription)
    {
        this.subscription=subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item)
    {
        consumedMessages.add(item);
        System.out.println("Greeting Received: "+item.toString());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable)
    {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete()
    {
        System.out.println("All Messages received");
    }

    public int getConsumedMessages()
    {
        return consumedMessages.size();
    }
}