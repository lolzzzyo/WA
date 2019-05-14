package nl.hu.v1wac.firstapp.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;

@Path("/countries")
public class WorldResource {
	
	@GET
	@Produces("application/json")
	public String getWorld() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountry(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		JsonObjectBuilder job = Json.createObjectBuilder();
		Country c = service.getCountryByCode(code);
		
		if(c == null) {
			throw new WebApplicationException("No such country!");
		}
		
		job.add("code", c.getCode());
		job.add("iso3", c.getIso3());
		job.add("name", c.getName());
		job.add("continent", c.getContinent());
		job.add("capital", c.getCapital());
		job.add("region", c.getRegion());
		job.add("surface", c.getSurface());
		job.add("population", c.getPopulation());
		job.add("goverment", c.getGovernment());
		job.add("lat", c.getLatitude());
		job.add("lng", c.getLongitude());
		
		return job.build().toString();
	}
	
	@GET
	@Path("/largestsurfaces")
	@Produces("application/JSON")
	public String getLargestSurfaces() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
	
	@GET
	@Path("/largestpopulations")
	@Produces("application/JSON")
	public String getLargestPopulations() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for(Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("goverment", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lng", c.getLongitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
		
	}
}
