package es.s2o.selenium.services;

import es.s2o.selenium.builders.ClientDataBuilder;
import es.s2o.selenium.builders.ReservationDataBuilder;
import es.s2o.selenium.domain.Client;
import es.s2o.selenium.domain.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by sacrists on 26.02.17.
 * <p>
 * Class to interact with the API of the application and, for example, add reservations to later use them from the UI
 */
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public List<Reservation> addReservations(int numberOfReservations) {
        LOGGER.debug("addReservations starts, creating [{}]", numberOfReservations);

        List<Reservation> reservationDto = IntStream.range(0, numberOfReservations)
                .mapToObj(this::generateReservations)
                .collect(Collectors.toList());

        // TODO interact with the API of the application (in this case this app is an example)

        return reservationDto;
    }

    private Reservation generateReservations(int i) {
        Client client = ClientDataBuilder.defaultClient(i).build();
        return new ReservationDataBuilder().defaultReservation(i, client).build();
    }

    public void clean() {
        LOGGER.debug("clean");

        // TODO interact with the API of the application (in this case this app is an example)
    }
}
