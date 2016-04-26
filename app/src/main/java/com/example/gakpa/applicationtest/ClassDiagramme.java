package com.example.gakpa.applicationtest;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gakpa on 05/04/2016.
 */
public class ClassDiagramme {
    List<DataPoint> points;

    public ClassDiagramme() {
        this.points = new ArrayList<>();
    }

    public List<DataPoint> getPoints() {
        return points;
    }

    public void setPoints(List<DataPoint> points) {
        this.points = points;
    }

    public void addPoint(int x, int y){
        this.points.add(new DataPoint(x,y));
    }

    public void addPoint(DataPoint d){
        this.points.add(d);
    }

    public DataPoint[] generateTabDataPoint(){
        DataPoint[] d = new DataPoint[points.size()];

        for(int i = 0 ; i< points.size(); i++){
            d[i]= points.get(i);
        }

        return d;
    }

}
