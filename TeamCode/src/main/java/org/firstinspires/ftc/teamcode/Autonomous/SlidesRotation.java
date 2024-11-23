package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class SlidesRotation {
    private Servo armLeftFront;
    private Servo armRightFront;
    private Telemetry telemetry;
    double armPosition;

    public SlidesRotation(HardwareMap hardwareMap, Telemetry telemetryA){
        armLeftFront = hardwareMap.get(Servo.class, "servo1"); // on expansion hub
        armRightFront = hardwareMap.get(Servo.class, "servo2");
        armPosition = 0;
        armLeftFront.setPosition(armPosition);
        armRightFront.setPosition(1 - armPosition);
        telemetry = telemetryA;
    }

    public class HighBasket implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("armLeftFront: ", armLeftFront.getPosition());
            telemetry.addData("armRightFront: ", armRightFront.getPosition());
            telemetry.update();
            armLeftFront.setPosition(0.8);
            armRightFront.setPosition(0.2);
            return false;
        }
    }
    public Action highBasket(){
        return new HighBasket();
    }
}