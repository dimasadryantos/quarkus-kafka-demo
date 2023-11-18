package org.dzcorp.messaging.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dzcorp.messaging.KafkaProducer;
import org.dzcorp.messaging.dto.MovieDto;

@ApplicationScoped
public class MovieService {
    @Inject
    KafkaProducer producer;

    public void createNewMovie() {
        producer.publish(new MovieDto("avengers endgame", "avengers"));
    }
}