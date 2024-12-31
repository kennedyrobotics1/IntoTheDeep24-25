package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
// RR-specific imports

// Non-RR imports


public class PIDController {

    // old pid stuff for reference/use if needed
    double position = 0;
    double velocity = 0;
    double integral = 0;
    double error = 0;
    double startPoint = 0;
    double setPoint = 0;
    double p = 0;
    double i = 0;
    double d = 0;

    public void setPoint(double position) {
        setPoint = position;
    }

    public void increaseSetPoint(double distance) {
        setPoint += distance;
    }

    public void startPoint(double position) {
        startPoint = position;
    }

    public void setCoefficients(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public void update(double positionNew, double dt) {
        positionNew = positionNew - startPoint;
        integral =+ dt * (setPoint - position + setPoint - positionNew) * 0.5;
        velocity = (positionNew - position) / dt;
        position = positionNew;
        error = setPoint - position;
    }

    public double getSum() {
        return p * error - d * velocity + i * integral;
    }

    public double getSetPoint() {
        return setPoint;
    }

    public double getStartPoint() {
        return startPoint;
    }

    public double getError() {
        return error;
    }

    public double getVelocity() {
        return  velocity;
    }

    public double getPosition() {
        return position;
    }

}
