package ca.airspeed.tsheets.client;

import ca.airspeed.tsheets.TsheetsConfiguration;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

@Client(TsheetsConfiguration.TSHEETS_API_URL)
@Header(name="Authorization", value="Bearer ${tsheet.api-token}")
public interface TsheetsClient {

}
