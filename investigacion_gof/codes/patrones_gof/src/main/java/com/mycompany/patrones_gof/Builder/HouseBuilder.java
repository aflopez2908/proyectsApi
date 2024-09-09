package org.example.Builder;

class HouseBuilder {
    private String walls;
    private String roof;

    public HouseBuilder setWalls(String walls) {
        this.walls = walls;
        return this;
    }

    public HouseBuilder setRoof(String roof) {
        this.roof = roof;
        return this;
    }

    public House build() {
        return new House(walls, roof);
    }
}
