package rest;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface RESTInterface {

    public Response getCount();

    public Response free();

    public Response getCarById(@QueryParam("id") Long id);

    public Response getCarByNumber(@QueryParam("number") String number);

    public Response putCar(@QueryParam("number") String number, @QueryParam("model") String model);

    public Response deleteCar(@QueryParam("number") String number);
}
