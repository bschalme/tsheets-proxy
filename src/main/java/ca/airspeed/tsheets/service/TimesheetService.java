package ca.airspeed.tsheets.service;

import static io.micronaut.http.HttpRequest.GET;

import java.net.URI;

import javax.inject.Singleton;

import ca.airspeed.tsheets.TsheetsConfiguration;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Singleton
public class TimesheetService {

    private final RxHttpClient httpClient;
    private final URI uri;
    private final TsheetsConfiguration configuration;

    public TimesheetService(@Client(TsheetsConfiguration.TSHEETS_API_URL) RxHttpClient httpClient, TsheetsConfiguration configuration) {
        super();
        this.httpClient = httpClient;
        this.uri = UriBuilder.of("/api")
            .path(configuration.getApiVersion())
            .path("timesheets")
            .build();
        this.configuration = configuration;
    }

    public Single<String> fetchOneTimesheet(Integer id) {
        URI timesheetUri = UriBuilder.of(uri)
            .queryParam("ids", id)
            .build();
        Flowable<String> response =  httpClient.retrieve(GET(timesheetUri).header("Authorization", "Bearer " + configuration.getApiToken()), String.class);
        return response.first("");
    }
                
}
