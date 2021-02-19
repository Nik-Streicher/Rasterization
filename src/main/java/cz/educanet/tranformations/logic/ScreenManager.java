package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class ScreenManager {

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    private int fun(Coordinate x, Coordinate y, Coordinate z) {
        return (x.getX() - z.getX()) - (z.getX() - y.getX()) * (y.getY() - z.getY()) * (z.getY() - x.getY());
    }

    public boolean isFilledIn(Coordinate coordinate) {

        Coordinate[] list = selectedPoints.toArray(new Coordinate[]{});

        Coordinate x = list[0];
        Coordinate y = list[1];
        Coordinate z = list[2];

        int thirdDimX = ((coordinate.getX() - z.getX()) * (y.getY() - z.getY())) + ((coordinate.getY()) - z.getY()) * (z.getX() - y.getX());
        int thirdDimY = ((coordinate.getX() - z.getX()) * (z.getY() - x.getY())) + ((coordinate.getY()) - z.getY()) * (x.getX() - z.getX());
        int thirdDimZ = fun(x, y, z) - thirdDimX - thirdDimY;

        int min = Math.min(fun(x, y, z), 0);
        int max = Math.max(fun(x, y, z), 0);

        if ((thirdDimX < min || thirdDimX > max) || (thirdDimY < min || thirdDimY > max)) {
            return true;
        } else {
            return thirdDimZ >= min && thirdDimZ <= max;
        }
    }
}
