package strategy;

import players.Children;
import players.City;

import java.util.ArrayList;

import java.util.Map;

public class CityNiceScore implements GiftingStretegy {

    private ArrayList<City> calculateCityScore(ArrayList<Children> childList) {
        ArrayList<City> cities = new ArrayList<>();
        String cityName;
        Double average;
        for (Children child : childList) {
            cityName = child.getCity();
            int exists = 0;
            for (City city: cities ) {
                if(city.getCityName().equals(cityName)) {
                    exists = 1;
                    average = city.getCityAverageScore();
                    average = average + child.getAverageScore();
                    city.setCityAverageScore(average);
                    city.setNumberOfResidents(city.getNumberOfResidents() + 1.0);
                }
            }
            if (exists == 0) {
                City aux = new City();
                aux.setCityName(cityName);
                aux.setCityAverageScore(child.getAverageScore());
                aux.setNumberOfResidents(1.0);
                cities.add(aux);
            }
        }
        for (City city : cities) {
            average = city.getCityAverageScore();
            average = average / city.getNumberOfResidents();
            city.setCityAverageScore(average);
        }
        cities.sort((o1, o2) -> {
            if (o1.getCityAverageScore().equals(o2.getCityAverageScore())) {
                return o1.getCityName().compareTo(o2.getCityName());
            } else {
                return o2.getCityAverageScore().compareTo(o1.getCityAverageScore());
            }
        });
        return cities;
    }
    @Override
    public ArrayList<Children> calculatingStrategy(ArrayList<Children> childList) {
        ArrayList<City> cities = calculateCityScore(childList);
        ArrayList<Children> cityOrderedChids = new ArrayList<>();
        for (City city : cities) {
            for (Children child : childList) {
                if (child.getCity().equals(city.getCityName())) {
                    cityOrderedChids.add(child);
                }
            }
        }
        return cityOrderedChids;
    }
}
