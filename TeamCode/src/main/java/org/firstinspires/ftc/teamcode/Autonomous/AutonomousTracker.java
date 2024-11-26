package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTracker;

import com.acmerobotics.roadrunner.Vector2d;

import java.util.Vector;

public class AutonomousTracker {
    public double x;
    public double y;
    public double r;

    public boolean invertY;
    public boolean invertX;
    public boolean invertR;

    public AutonomousTracker(double newx, double newy, double newr) {
        x = newx;
        y = newy;
        r = newr;

        invertY=false;
        invertX=false;
        invertR=false;
    }
    public Vector2d update2d(double new_incrementx, double new_incrementy) {
        if (invertX) {
            x -= new_incrementx;
        }
        else {
            x += new_incrementx;
        }

        if (invertY) {
            y -= new_incrementy;
        }
        else {
            y += new_incrementy;
        }

        return new Vector2d(x, y);
    }

    public double updateRotation(double new_incrementr) {
        double rotation = Math.toRadians(new_incrementr);
        if (invertR) {
            r += rotation + Math.PI;
        } else {
            r += rotation;
        }
        return r;

    }
}
