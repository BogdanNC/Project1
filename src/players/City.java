package players;

public final class City {
    private String cityName;
    private Double cityAverageScore;
    private Double numberOfResidents;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public Double getCityAverageScore() {
        return cityAverageScore;
    }

    public void setCityAverageScore(final Double cityAverageScore) {
        this.cityAverageScore = cityAverageScore;
    }

    public Double getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(final Double numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }
}
