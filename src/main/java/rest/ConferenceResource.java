package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.TalkDTO;
import facades.ConferenceFacade;
import facades.APIFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/conference")
public class ConferenceResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private static final ConferenceFacade facade = ConferenceFacade.getInstance(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Path("alltalks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTalks() {
        List<TalkDTO> talkDTOList = facade.getAllTalks();
        return GSON.toJson(talkDTOList);
    }
}