package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTracker;

import com.acmerobotics.roadrunner.Vector2d;

import java.util.Vector;

public class AutonomousTracker {
    public double x;
    public double y;

    public boolean invertY;
    public boolean invertX;

    public AutonomousTracker(double newx, double newy) {
        x = newx;
        y = newy;

        invertY=false;
        invertX=false;
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

}
