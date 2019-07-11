package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Country;

public class CountryDAO extends BaseDAO {
	private List<Country> selectCountries(String query) {
		List<Country> results = new ArrayList<Country>();
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String code2 = dbResultSet.getString("iso3");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				double surface = dbResultSet.getDouble("surfacearea");
				int population  = dbResultSet.getInt("population");
				String government = dbResultSet.getString("governmentform");
				double latitude = dbResultSet.getDouble("latitude");
				double longitude = dbResultSet.getDouble("longitude");
				Country newCountry = new Country(code, code2, name, capital, continent, region, surface, population, government, latitude, longitude);
				results.add(newCountry);
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		return results;
	}
	
	public Country save(Country country) {
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO country (code, iso3, name, continent, region, surfacearea, population, governmentform, latitude, longitude, capital) VALUES "
					+ "('" + country.getCode() + "', '" + country.getIso3Code() + "', '" + country.getName() + "', '" + country.getContinent() + "', '" + country.getRegion() + "', " + country.getSurface() + ", " + country.getPopulation() + ", '" + country.getGovernment() + "', " + country.getLatitude() + ", " + country.getLongitude() + ", '" + country.getCapital() + "')");
			System.out.println("Land is toegevoegd!");

		} catch (SQLException sqle) { sqle.printStackTrace(); System.out.println("Er is iets mis gegaan!"); }
		return country;
	}
	
	public List<Country> findAll() {
		return selectCountries("SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country");
	}
	
	public Country findByCode(String code){
		return selectCountries("SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude FROM country where code = " + code).get(0);
	}

	public List<Country> find10LargestPopulations() {
		return selectCountries("select code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude from country order by population DESC limit 10");
	}
	
	public List<Country> find10LargestSurfaces() {
		return selectCountries("SELECT code, iso3, name, capital, continent, region, surfacearea, population, governmentform, latitude, longitude from country order by surfacearea DESC limit 10");
	}
	
	public Country updateCountry(Country country) {
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			String query = "UPDATE country SET name = '"+ country.getName() +"', region = '" + country.getRegion() + "', surfacearea = "+ country.getSurface() +", population = " + country.getPopulation() + ", capital = '" + country.getCapital() + "' WHERE code = '" + country.getCode() + "'";
			stmt.executeUpdate(query);
			
			System.out.println(country.getCode());
			
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		return country;
	}

	public Country deleteByCode(Country country) {
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			String query = "DELETE FROM country where code = '"+ country.getCode() +"'";
			stmt.executeUpdate(query);
			
			System.out.println(country.getCode());
			
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		return country;
	}	
}
