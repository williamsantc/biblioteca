/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.biblioteca;

/**
 *
 * @author willi
 */
import com.ceiba.biblioteca.api.LibroResource;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test de servicios de libro")
public class LibroResourceTests extends JerseyTest {

    @Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(LibroResource.class);
    }

    @Test
    public void fetchAllTest() {
        Response response = target("/libro/list").request().get();
        assertEquals("Debería retornar un 200", 200, response.getStatus());
        assertNotNull("No debería retornar nulo", response.getEntity().toString());
    }

}
