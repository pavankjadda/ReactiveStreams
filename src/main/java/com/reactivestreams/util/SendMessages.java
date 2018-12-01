package com.reactivestreams.util;

import com.reactivestreams.model.Greeting;
import com.reactivestreams.subscriber.GreetingSubscriber;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

/*
This class helps to send messages on Spring boot start. You can also use ReactiveStreamsApplicationTests test class
See https://www.baeldung.com/running-setup-logic-on-startup-in-spring for more information
*/


@Component
public class SendMessages implements ApplicationRunner
{
    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        SubmissionPublisher<Greeting> greetingPublisher = new SubmissionPublisher<>();
        GreetingSubscriber<Greeting> greetingSubscriber = new GreetingSubscriber<>();
        greetingPublisher.subscribe(greetingSubscriber);

        List<Greeting> greetingList = new ArrayList<>();
        createGreetings(greetingList,10);
        greetingList.forEach(greetingPublisher::submit);

        //Wait until messages sent
        while (greetingList.size() != greetingSubscriber.getConsumedMessages())
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void createGreetings(List<Greeting> greetingList,int numberOfMessages)
    {
        for (int i=0;i<numberOfMessages;i++)
        {
            greetingList.add(new Greeting("Message Number: "+i, "Pavan Jadda", LocalDateTime.now()));
        }
    }
}
