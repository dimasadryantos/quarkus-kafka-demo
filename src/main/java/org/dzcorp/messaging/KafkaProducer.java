package org.dzcorp.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.dzcorp.messaging.dto.MovieDto;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
public class KafkaProducer {

    @Inject
    @Channel("movie")
    Emitter<MovieDto> emitter;
    public void publish(MovieDto movieDto) {
        emitter.send(movieDto);
    }
}
