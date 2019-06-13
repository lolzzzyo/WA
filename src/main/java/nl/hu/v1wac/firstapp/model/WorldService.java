package nl.hu.v1wac.firstapp.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.CountryDAO;

public class WorldService {
	private CountryDAO countrydao = new CountryDAO();
	
	
	public List<Country> getAllCountries() {
		return countrydao.findAll();
	}
	
	public List<Country> get10LargestPopulations() {
		Collections.sort(countrydao.findAll(), new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});
		
		return countrydao.findAll().subList(0, 10);
	}

	public List<Country> get10LargestSurfaces() {
		Collections.sort(countrydao.findAll(), new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			};
		});
		
		return countrydao.findAll().subList(0, 10);
	}
	
	public Country getCountryByCode(String code) {
		Country result = null;
		
		for (Country c : countrydao.findAll()) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		
		return result;
	}
}
