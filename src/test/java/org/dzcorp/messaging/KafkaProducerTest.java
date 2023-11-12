package org.dzcorp.messaging;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.dzcorp.messaging.dto.MovieDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@QuarkusTest
class KafkaProducerTest {

    @Inject
    KafkaProducer kafkaProducer;

    @Inject
    @Any
    InMemoryConnector inMemoryConnector;


    @Test
    void publishEvent_withValidMovieDto_eventPublished() {
        MovieDto movieDto = new MovieDto("avengers endgame", "avengers");
        kafkaProducer.publish(movieDto);

        InMemorySink<MovieDto> eventQueue = inMemoryConnector.sink("movie");
        MovieDto actual = eventQueue.received().get(0).getPayload();

        assertThat(actual).usingRecursiveComparison().isEqualTo(movieDto);
    }
}