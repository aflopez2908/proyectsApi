package org.example.Builder;

public class Main {
    public static void main(String[] args) {
        House house = new HouseBuilder().setWalls("Brick Walls").setRoof("Metal Roof").build();
        System.out.println(house.getDetails());
    }
}