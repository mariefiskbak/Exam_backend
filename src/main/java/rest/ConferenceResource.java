package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dtos.TalkDTO;
import entities.Conference;
import entities.Talk;
import facades.ConferenceFacade;
import facades.APIFacade;
import facades.Populator;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.sql.Date;
import java.sql.Time;
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

    @Path("talks/{conferenceId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTalksByConferenceId(@PathParam("conferenceId") Long conferenceId) {
        List<TalkDTO> talkDTOList = facade.getTalksBeConferenceId(conferenceId);
        return GSON.toJson(talkDTOList);
    }

    @Path("talkid/{talkId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTalkByTalkId(@PathParam("talkId") Long talkId) {
        TalkDTO talkDTO = facade.getTalkByTalkId(talkId);
        return GSON.toJson(talkDTO);
    }

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllConferences() {
        List<TalkDTO.ConferenceInnerDTO> conferenceDTOList = facade.getAllConferences();
        return GSON.toJson(conferenceDTOList);
    }

    @Path("populate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String populate() {
        Populator.populate();
        List<TalkDTO> talkDTOList = facade.getAllTalks();
        return GSON.toJson(talkDTOList);
    }

    @POST
    @Path("newconference")
    @Consumes("application/json")
    @Produces("application/json")
    public String createConference(String jdata) {
        JsonObject json = JsonParser.parseString(jdata).getAsJsonObject();

        String name = json.get("name").getAsString();
        String location = json.get("location").getAsString();
        int capacity = json.get("capacity").getAsInt();
        Date date = Date.valueOf(json.get("date").getAsString());
        Time time = Time.valueOf(json.get("time").getAsString() + ":00");

        Conference conference = new Conference(name, location, capacity, date, time);
        return GSON.toJson(facade.createConference(conference));
    }

    @PUT
    @Path("updatetalk/{talkId}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updateTalk(@PathParam("talkId") Long talkId, String jdata) {
        JsonObject json = JsonParser.parseString(jdata).getAsJsonObject();

        String topic = json.get("topic").getAsString();
        int duration = json.get("duration").getAsInt();
        String propsList = json.get("propsList").getAsString();

        //TODO conference and speakers

        Talk talk = new Talk(talkId, topic, duration, propsList);
        return GSON.toJson(facade.updateTalk(talk));

    }

}