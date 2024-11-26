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
        r = Math.toRadians(newr);

        invertY = false;
        invertX = false;
        invertR = false;
    }

    public Vector2d update2d(double new_incrementx, double new_incrementy) {
        double delta_x = (new_incrementx * Math.cos(r)) - (new_incrementy * Math.sin(r));
        double delta_y = (new_incrementx * Math.sin(r)) + (new_incrementy * Math.cos(r));

        if (invertX) {
            x -= delta_x;
        }
        else {
            x += delta_x;
        }

        if (invertY) {
            y -= delta_y;
        }
        else {
            y += delta_y;
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

    public Vector2d moveForward(double distance) {
        double delta_x = distance * Math.cos(r);
        double delta_y = distance * Math.sin(r);

        if (invertX) {
            x -= delta_x;
        }
        else {
            x += delta_x;
        }

        if (invertY) {
            y -= delta_y;
        }
        else {
            y += delta_y;
        }

        return new Vector2d(x, y);
    }
}
