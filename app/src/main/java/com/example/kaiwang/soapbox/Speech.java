package com.example.kaiwang.soapbox;

import java.util.ArrayList;

/**
 * Created by kaiwang on 20.8.2015.
 */
public class Speech {
    public String topic;
    public String speaker;
    public String time;

    public Speech(String topic, String speaker, String time) {
        this.topic = topic;
        this.speaker = speaker;
        this.time = time;
    }

    public static ArrayList<Speech> getSpeeches() {
        ArrayList<Speech> speeches = new ArrayList<Speech>();
        speeches.add(new Speech("How to speak English?", "San Diego", "01.09.2015 12:20"));
        speeches.add(new Speech("Never give up.", "Marla","04.09.2015 12:20"));
        speeches.add(new Speech("How do men and women communicate differently using body language, and why does it matter (in dating, the workplace, social circles)?", "Sarah", "06.09.2015 12:20"));
        return speeches;
    }
}
