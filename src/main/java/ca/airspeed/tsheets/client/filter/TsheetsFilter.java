package ca.airspeed.tsheets.client.filter;

import org.reactivestreams.Publisher;

import ca.airspeed.tsheets.TsheetsConfiguration;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import lombok.extern.slf4j.Slf4j;

@Filter("/api/${tsheets.api-version}/**")
@Requires(property=TsheetsConfiguration.PREFIX + "api-token")
@Slf4j
public class TsheetsFilter implements HttpClientFilter {

    private final TsheetsConfiguration configuration;

    public TsheetsFilter(TsheetsConfiguration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        return chain.proceed(request.header("Authorization", "Bearer " + configuration.getApiToken()));
    }

}
