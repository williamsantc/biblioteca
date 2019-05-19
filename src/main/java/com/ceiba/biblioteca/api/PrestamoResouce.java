/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.api;

import com.ceiba.biblioteca.facade.FacadeGeneral;
import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.models.Prestamo;
import com.ceiba.biblioteca.models.Usuario;
import com.ceiba.biblioteca.util.StringUtils;
import com.ceiba.biblioteca.util.WsUtil;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 *
 * @author willi
 */
@Path("/prestamo")
public class PrestamoResouce {

    private static final FacadeGeneral FACADE_GENERAL = FacadeGeneral.getInstance();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerLibro(InputStream incomingData) {
        try {

            JSONObject payload = WsUtil.requestToJsonObject(incomingData);

            Libro libro = FACADE_GENERAL.findLibro(payload.getString("librId"));

            if (libro == null) {
                return WsUtil.badRequest("El libro no existe.");
            }

            Usuario usuario = FACADE_GENERAL.findUsuario(payload.getString("usuaId"));

            if (usuario == null) {
                return WsUtil.badRequest("El usuario no existe.");
            }
            
            Prestamo prestamo = new Prestamo();

            prestamo.setLibrId(libro);

            if (FACADE_GENERAL.registerPrestamo(prestamo)) {
                return WsUtil.ok("Prestamo registrado correctamente");
            } else {
                return WsUtil.internalError(StringUtils.printString(libro.getError(), "Ocurrió un error al registrar el prestamo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return WsUtil.internalError(e.getMessage());
        }
    }
}
