package nl.hu.v1wac.firstapp.webservices;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.firstapp.model.Country;
import nl.hu.v1wac.firstapp.model.ServiceProvider;
import nl.hu.v1wac.firstapp.model.WorldService;
import nl.hu.v1wac.firstapp.persistence.CountryDAO;

@Path("/countries")
public class WorldResource {
	CountryDAO dao = new CountryDAO();

	@GET
	@Produces("application/json")
	public String getCountries() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.getAllCountries()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("iso3", c.getIso3Code());
			job.add("name", c.getName());
			job.add("continent", c.getContinent());
			job.add("capital", c.getCapital());
			job.add("region", c.getRegion());
			job.add("surface", c.getSurface());
			job.add("population", c.getPopulation());
			job.add("government", c.getGovernment());
			job.add("lat", c.getLatitude());
			job.add("lon", c.getLongitude());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{code}")
	@Produces("application/json")
	public String getCountryInfo(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		Country c = service.getCountryByCode(code);

		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("code", c.getCode());
		job.add("iso3", c.getIso3Code());
		job.add("name", c.getName());
		job.add("continent", c.getContinent());
		job.add("capital", c.getCapital());
		job.add("region", c.getRegion());
		job.add("surface", c.getSurface());
		job.add("population", c.getPopulation());
		job.add("government", c.getGovernment());
		job.add("lat", c.getLatitude());
		job.add("lon", c.getLongitude());

		return job.build().toString();
	}

	@GET
	@Path("/largestsurfaces")
	@Produces("application/json")
	public String getLargestSurfaces() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Country c : service.get10LargestSurfaces()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("surface", c.getSurface());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("/largestpopulations")
	@Produces("application/json")
	public String getLargestPopulations() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Country c : service.get10LargestPopulations()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", c.getCode());
			job.add("name", c.getName());
			job.add("population", c.getPopulation());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	
	@DELETE
	@RolesAllowed("user")
	@Path("{code}")
	public String deleteCountry(@PathParam("code") String code) {
		WorldService service = ServiceProvider.getWorldService();
		Country country = service.getCountryByCode(code);
		dao.deleteByCode(country);
		return "success";
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	public String updateCountry(@PathParam("code") String code,
								@FormParam("name") String name,
								@FormParam("capital") String capital,
								@FormParam("region") String region,
								@FormParam("surface") double surface,
								@FormParam("population") int population) {
		WorldService service = ServiceProvider.getWorldService();
		Country country = service.getCountryByCode(code);
		Country updateCountry = new Country(code,country.getIso3Code(),name,capital,country.getContinent(),region,surface,population,country.getGovernment(),country.getLatitude(),country.getLongitude());
		dao.updateCountry(updateCountry);
		return "success";
	}
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public String createCustomer(@FormParam("code") String code,
								 @FormParam("iso3code") String iso3,
								 @FormParam("name") String name,
								 @FormParam("continent") String continent,
								 @FormParam("capital") String capital,
								 @FormParam("region") String region,
								 @FormParam("surface") double surface,
								 @FormParam("population") int population,
								 @FormParam("government") String government,
								 @FormParam("latitude") double lat,
								 @FormParam("longitude") double lon) {
		Country newCountry = new Country(code,iso3,name,continent,capital,region,surface,population,government,lat,lon);
		dao.save(newCountry);
		return "success";
	}
}
