package ca.airspeed.tsheets.controller;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

import javax.annotation.Nullable;

import ca.airspeed.tsheets.service.TimesheetService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller("/timesheets")
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        super();
        this.service = service;
    }

    @Get(uri = "/{?id}", produces = APPLICATION_JSON)
    public String fetchOneTimesheet(@Nullable Integer id) {
        Single<String> result = service.fetchOneTimesheet(id);
        return result.map(content -> new String(content)).blockingGet();
    }
}
