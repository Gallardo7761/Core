package net.miarma.api.common.vertx;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vertx.core.json.jackson.DatabindCodec;

/**
 * Configura el ObjectMapper de Vert.x para manejar correctamente
 * fechas y tiempos. Esto es necesario para que las fechas se serialicen
 * y deserialicen correctamente.
 *
 * @author José Manuel Amador Gallardo
 */
public class VertxJacksonConfig {
	@SuppressWarnings("deprecation")
	public static void configure() {
		ObjectMapper mapper = DatabindCodec.mapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        ObjectMapper prettyBase = DatabindCodec.prettyMapper();
        prettyBase.registerModule(new JavaTimeModule());
        prettyBase.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
}
