/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.api;

import com.ceiba.biblioteca.facade.FacadeGeneral;
import com.ceiba.biblioteca.models.Libro;
import com.ceiba.biblioteca.util.WsUtil;
import java.io.InputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author willi
 */
@Path("/libro")
public class LibroResource {
    
    private static final FacadeGeneral FACADE_GENERAL = FacadeGeneral.getInstance();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    public Response registerLibro(InputStream incomingData) {
        try {
            
            JSONObject payload = WsUtil.requestToJsonObject(incomingData);
            
            Libro libro = new Libro();
            
            libro.setLibrId(payload.getInt("librId"));
            libro.setLibrNombre(payload.getString("librNombre"));
            libro.setLibrIsbn(payload.getString("librIsbn"));
            libro.setLibrCantejemplares(0);
            
            if (this.FACADE_GENERAL.registerLibro(libro)) {
                return WsUtil.ok("Libro registrado correctamente");
            } else {
                return WsUtil.internalError("Ocurrió un error");
            }
        } catch (Exception e) {
            return WsUtil.internalError(e.getMessage());
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response listLibros() {
        try {
            return WsUtil.ok(new JSONArray(this.FACADE_GENERAL.listLibros()));
        } catch (Exception e) {
            return WsUtil.internalError(e.getMessage());
        }
    }
}
