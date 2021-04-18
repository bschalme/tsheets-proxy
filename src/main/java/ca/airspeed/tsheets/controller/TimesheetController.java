package ca.airspeed.tsheets.controller;

import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static io.micronaut.http.hateoas.Link.SELF;

import io.micronaut.core.annotation.Nullable;

import ca.airspeed.tsheets.service.TimesheetService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Controller("/timesheets")
@Slf4j
public class TimesheetController {

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        super();
        this.service = service;
    }

    @Get(uri = "/{?id,start_date,end_date}", produces = APPLICATION_JSON)
    public String fetchOneTimesheet(@Nullable @QueryValue("id") Integer id, @Nullable @QueryValue("start_date") String startDate, @Nullable @QueryValue("end_date") String endDate) {
        Single<String> result = service.retrieveTimesheets(id, startDate, endDate);
        return result.map(content -> new String(content)).blockingGet();
    }

    @Error
    public HttpResponse<JsonError> error(HttpRequest<?> request, Throwable e) {
        JsonError error = new JsonError("Bad things happened: "+ e.getMessage())
            .link(SELF, Link.of(request.getUri()));
        log.error(e.getMessage(), e);
        return HttpResponse.<JsonError>serverError()
            .body(error);
    }
}
