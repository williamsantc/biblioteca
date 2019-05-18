/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author willi
 */
public class WsUtil {

    public static String inputToString(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return br.readLine();
        }
    }

    public static String[] toStringArray(JSONArray array) throws JSONException {
        String[] res = new String[array.length()];
        for (int i = 0; i < res.length; i++) {
            res[i] = array.getString(i);
        }
        return res;
    }

    public static JSONObject requestToJsonObject(InputStream is) throws BadRequestException {
        try {
            return new JSONObject(inputToString(is));

        } catch (JSONException | IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    public static JSONArray requestToJsonArray(InputStream is) throws BadRequestException {
        try {
            return new JSONArray(inputToString(is));

        } catch (JSONException | IOException e) {
            throw new BadRequestException(e.getMessage(), e);
        }
    }

    public static Response ok() {
        return Response.ok().build();
    }

    public static Response ok(String mensaje) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("mensaje", mensaje);
            return Response.ok(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static Response ok(JSONObject resp) {
        return Response.ok(resp.toString()).build();
    }

    public static Response ok(JSONArray resp) {
        return Response.ok(resp.toString()).build();
    }

    public static Response internalError() {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    public static Response internalError(String error) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("error", error);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public static Response badRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public static Response badRequest(String error) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("error", error);
            return Response.status(Response.Status.BAD_REQUEST).entity(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public static Response notFound() {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public static Response notFound(String error) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("error", error);
            return Response.status(Response.Status.NOT_FOUND).entity(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public static Response forbidden() {
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    public static Response forbidden(String error) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("error", error);
            return Response.status(Response.Status.FORBIDDEN).entity(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    public static Response unauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    public static Response unauthorized(String error) {
        try {
            JSONObject resp = new JSONObject();
            resp.put("error", error);
            return Response.status(Response.Status.UNAUTHORIZED).entity(resp.toString()).build();

        } catch (JSONException je) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
