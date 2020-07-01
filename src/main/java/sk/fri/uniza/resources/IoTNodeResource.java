package sk.fri.uniza.resources;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sk.fri.uniza.api.FilterIotNodeEnum;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.Field;
import sk.fri.uniza.model.HouseHold;
import sk.fri.uniza.model.IotNode;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Api("/iotnode") // Swagger
@Path("/iotnode") // Koreňová adresa kolekcie koncových bodov
// pre prístup k zdrojom domácností // Súčasť JAX-RS
@Produces(MediaType.APPLICATION_JSON)// Výstupné dáta sú vo forme JSON //JAX-RS
@Consumes(MediaType.APPLICATION_JSON) //Vstupné dáta sú vo forme JSON //JAX-RS
public class IoTNodeResource {
    private IotNodeDAO iotNodeDAO;
    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Pridá nový IoTNode")
    public IotNode createIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.create(iotNode);
    }

    @PUT
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Upraví existujúci IoTNode")
    public IotNode updateIotNode(@Valid IotNode iotNode) {
        return iotNodeDAO.update(iotNode);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{id}")
    @ApiOperation(value = "Zobrazí IoTNode")
    public IotNode findIotNode(@PathParam("id") Long id) {
        return iotNodeDAO.findById(id);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zobrazí všetky IoTNode")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

    /*
    // pri spustení spolu s allIotNodes chyba ->
    // ! [[FATAL] A resource model has ambiguous (sub-)resource method for HTTP method GET and input mime-types as defined by"@Consumes" and "@Produces" annotations at Java methods public sk.fri.uniza.model.IotNode sk.fri.uniza.resources.IoTNodeResource.findIotNode(java.lang.Long) and public java.util.List sk.fri.uniza.resources.IoTNodeResource.findByHouseHold(java.lang.Long) at matching regular expression /([^/]+). These two methods produces and consumes exactly the same mime-types and therefore their invocation as a resource methods will always fail.; source='org.glassfish.jersey.server.model.RuntimeResource@415419a4']
    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @Path("{household_id}")
    @ApiOperation(value = "Zobrazí IoTNode podľa HouseHold ID")
    public List<IotNode> findByHouseHold(@PathParam("household_id") Long houseHold) {
        return iotNodeDAO.findByHouseHold(houseHold);
    }
    */

    /*
    // spojenie allIotNodes() a findByHouseHold
    @GET
    @Path("filter")
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zobrazí všetky IotNode alebo podľa HousHoldID")
    public List<IotNode> filterIotNode(
            @QueryParam("filter") FilterIotNodeEnum filter,
            @QueryParam("id") Long id) {
        switch (filter) {
            case allIotNodes:
                return iotNodeDAO.allIotNodes();
            case houseHoldID:
                return iotNodeDAO.findByHouseHold(id);
        }
        return null;
    }
    */
}
