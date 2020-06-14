package com.jam01.camel.petstore;

import javax.annotation.Generated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.CollectionFormat;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

/**
 * Generated from OpenApi specification by Camel REST DSL generator.
 */
@Generated("org.apache.camel.generator.openapi.PathGenerator")
@Component
public final class APIRouter extends RouteBuilder {
    /**
     * Defines Apache Camel routes using REST DSL fluent API.
     */
    public void configure() {
        restConfiguration().component("netty-http")
            .host("0.0.0.0")
            .port(8080);

        rest("/api")
            .get("/pets")
                .id("findPets")
                .description("Returns all pets from the system that the user has access to\n"
                        + "Nam sed condimentum est. Maecenas tempor sagittis sapien, nec rhoncus sem sagittis sit amet. Aenean at gravida augue, ac iaculis sem. Curabitur odio lorem, ornare eget elementum nec, cursus id lectus. Duis mi turpis, pulvinar ac eros ac, tincidunt varius justo. In hac habitasse platea dictumst. Integer at adipiscing ante, a sagittis ligula. Aenean pharetra tempor ante molestie imperdiet. Vivamus id aliquam diam. Cras quis velit non tortor eleifend sagittis. Praesent at enim pharetra urna volutpat venenatis eget eget mauris. In eleifend fermentum facilisis. Praesent enim enim, gravida ac sodales sed, placerat id erat. Suspendisse lacus dolor, consectetur non augue vel, vehicula interdum libero. Morbi euismod sagittis libero sed lacinia.\n"
                        + "\n"
                        + "Sed tempus felis lobortis leo pulvinar rutrum. Nam mattis velit nisl, eu condimentum ligula luctus nec. Phasellus semper velit eget aliquet faucibus. In a mattis elit. Phasellus vel urna viverra, condimentum lorem id, rhoncus nibh. Ut pellentesque posuere elementum. Sed a varius odio. Morbi rhoncus ligula libero, vel eleifend nunc tristique vitae. Fusce et sem dui. Aenean nec scelerisque tortor. Fusce malesuada accumsan magna vel tempus. Quisque mollis felis eu dolor tristique, sit amet auctor felis gravida. Sed libero lorem, molestie sed nisl in, accumsan tempor nisi. Fusce sollicitudin massa ut lacinia mattis. Sed vel eleifend lorem. Pellentesque vitae felis pretium, pulvinar elit eu, euismod sapien.\n")
                .produces("application/json,application/json")
                .param()
                    .name("tags")
                    .type(RestParamType.query)
                    .dataType("array")
                    .collectionFormat(CollectionFormat.multi)
                    .arrayType("string")
                    .required(false)
                    .description("tags to filter by")
                .endParam()
                .param()
                    .name("limit")
                    .type(RestParamType.query)
                    .dataType("integer")
                    .required(false)
                    .description("maximum number of results to return")
                .endParam()
                .to("direct:findPets")
            .post("/pets")
                .id("addPet")
                .description("Creates a new pet in the store. Duplicates are allowed")
                .consumes("application/json")
                .produces("application/json,application/json")
                .param()
                    .name("body")
                    .type(RestParamType.body)
                    .required(true)
                    .description("Pet to add to the store")
                .endParam()
                .to("direct:addPet")
            .get("/pets/{id}")
                .id("find pet by id")
                .description("Returns a user based on a single ID, if the user does not have access to the pet")
                .produces("application/json,application/json")
                .param()
                    .name("id")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet to fetch")
                .endParam()
                .to("direct:find pet by id")
            .delete("/pets/{id}")
                .id("deletePet")
                .description("deletes a single pet based on the ID supplied")
                .produces("application/json")
                .param()
                    .name("id")
                    .type(RestParamType.path)
                    .dataType("integer")
                    .required(true)
                    .description("ID of pet to delete")
                .endParam()
                .to("direct:deletePet");
    }
}
