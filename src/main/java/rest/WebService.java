package rest;

import ejb.CarService;
import entity.Car;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/parking")
@Stateless
public class WebService {

    @EJB
    private CarService service;

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCount() {
        return Response.status(Response.Status.OK).entity("All: " + service.getCount()).build();
    }

    @GET
    @Path("/free")
    @Produces(MediaType.TEXT_PLAIN)
    public Response free() {
        return Response.status(Response.Status.OK).entity("Free: " + service.getFree()).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getCarById(@QueryParam("id") Long id) {
        try {
            Car car = service.getCarById(id);
            return Response.ok(car).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/check")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getCarByNumber(@QueryParam("number") String number) {
        try {
            Car car = service.getCarByNumber(number);
            return Response.ok(car).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/put")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response putCar(@QueryParam("number") String number, @QueryParam("model") String model) {
        Car car = new Car();
        car.setModel(model);
        car.setNumber(number);
        try {
            if (service.putCar(car) != null) {
                return Response.ok(car).build();
            } else {
                return Response.status(Response.Status.CONFLICT).entity("Duplicated car: " + number).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
    public Response deleteCar(@QueryParam("number") String number) {
        try {
            service.deleteCar(service.getCarByNumber(number));
            return Response.status(Response.Status.OK).entity("Deleted: " + number).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/pricebydate")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPriceForDay(@QueryParam("date") String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return Response.ok().entity("Total price for " + date + ": " + service.getPriceForDay(dateFormat.parse(date)) + " rub.").build();
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

    }
}
