package org.example.Builder;

class House {
    private String walls;
    private String roof;

    public House(String walls, String roof) {
        this.walls = walls;
        this.roof = roof;
    }

    public String getDetails() {
        return "House with " + walls + " and " + roof;
    }
}