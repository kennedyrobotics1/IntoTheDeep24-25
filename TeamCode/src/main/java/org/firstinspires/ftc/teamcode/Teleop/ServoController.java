package org.firstinspires.ftc.teamcode.Teleop;

public class ServoController {
    public double position;

    public ServoController(double initialPosition){
        position = initialPosition;
    }

    public double update(double increment){
        double newPosition = position + increment;
        if (1 < newPosition){
            newPosition = 1;
        } else if (newPosition < 0) {
            newPosition = 0;
        }
        position = newPosition;
        return  position;
    }
}
