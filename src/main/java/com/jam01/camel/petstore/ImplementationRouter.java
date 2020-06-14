package com.jam01.camel.petstore;

import com.modus.camel.datasonnet.DatasonnetRouteBuilder;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ImplementationRouter extends DatasonnetRouteBuilder {
    private static final String SELECT_FROM_PETS_WHERE_TYPE_TYPE = "select * from pets where type = :?type";

    @Override
    public void configure() throws Exception {
        from("direct:findPets")
                .setBody(datasonnet("{beep:'boop', boop: 'beep'}"));

        from("direct:addPet")
                .streamCaching()
                .choice()
                    .when(datasonnet("payload.name == 'rabbit'"))
                        .log("it's a rabbit")
                        .to("seda:toContainerRabbit?exchangePattern=InOnly")
                        .log("sent async")
                        .transform(datasonnet("{'status': 'sending to rabbitmq'}"))
                    .when(datasonnet("payload.name == 'seal'"))
                        .log("it's a seal")
                        .to("seda:toMariaDb?exchangePattern=InOnly")
                        .log("sent async")
                        .transform(datasonnet("{'status': 'sending to mariadb'}"))
                    .otherwise()
                        .transform(datasonnet("\"dunno what we're doing!\""))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"));

        from("seda:toContainerRabbit")
                .to("rabbitmq:esb-test");

        from("rabbitmq:esb-test?connectionFactory=#containerRabbit")
                .log("From esb-test ${body}")
                .transform(datasonnet("payload.beep"))
                .log("${body}");

        from("seda:toMariaDb")
                .setHeader("type", datasonnet("payload.name", "application/json", "text/plain"))
                .setHeader("other", datasonnet("payload.beep", "application/json", "text/plain"))
                .setBody(constant(SELECT_FROM_PETS_WHERE_TYPE_TYPE))
                .log("${header.type}")
                .to("jdbc:inMemDatabase?useHeadersAsParameters=true")
                .log("${body}");
    }
}
