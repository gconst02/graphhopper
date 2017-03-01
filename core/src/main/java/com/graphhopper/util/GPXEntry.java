/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for 
 *  additional information regarding copyright ownership.
 * 
 *  GraphHopper GmbH licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except in 
 *  compliance with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.util;

import com.graphhopper.util.shapes.GHPoint;
import com.graphhopper.util.shapes.GHPoint3D;

/**
 * @author Peter Karich
 */
public class GPXEntry extends GHPoint3D {
    private long time;
    private double magvar;
    private double speed;
    private double accuracy;
    private double bearing;

    public GPXEntry(GHPoint p, long millis) {
        this(p.lat, p.lon, millis);
    }

    public GPXEntry(double lat, double lon, long millis) {
        super(lat, lon, Double.NaN);
        this.time = millis;
    }

    public GPXEntry(double lat, double lon, double ele, long millis) {
        super(lat, lon, ele);
        this.time = millis;
    }

    public GPXEntry(double lat, double lon, double ele, long millis, double magvar, double speed,
        double accuracy, double bearing) {
        super(lat, lon, ele);
        this.time = millis;
        this.magvar = magvar;
        this.speed = speed;
        this.accuracy = accuracy;
        this.bearing = bearing;
    }

    boolean is3D() {
        return !Double.isNaN(ele);
    }

    /**
     * The time relative to the start time in milli seconds.
     */
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getMagvar() {
      return magvar;
    }

    public void setMagvar(double magvar) {
      this.magvar = magvar;
    }

    public double getSpeed() {
      return speed;
    }

    public void setSpeed(double speed) {
      this.speed = speed;
    }

    public double getAccuracy() {
      return accuracy;
    }

  public void setAccuracy(double accuracy) {
    this.accuracy = accuracy;
  }

  public double getBearing() {
    return bearing;
  }

  public void setBearing(double bearing) {
    this.bearing = bearing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    GPXEntry gpxEntry = (GPXEntry) o;

    if (time != gpxEntry.time) {
      return false;
    }
    if (Double.compare(gpxEntry.magvar, magvar) != 0) {
      return false;
    }
    if (Double.compare(gpxEntry.speed, speed) != 0) {
      return false;
    }
    if (Double.compare(gpxEntry.accuracy, accuracy) != 0) {
      return false;
    }
    return Double.compare(gpxEntry.bearing, bearing) == 0;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    long temp;
    result = 31 * result + (int) (time ^ (time >>> 32));
    temp = Double.doubleToLongBits(magvar);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(speed);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(accuracy);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(bearing);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return super.toString() + ", " +
        "time=" + time +
        ", magvar=" + magvar +
        ", speed=" + speed +
        ", accuracy=" + accuracy +
        ", bearing=" + bearing +
        '}';
  }
}
