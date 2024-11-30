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
public class SlidesRotationAuto {
    private Servo armLeftFront;
    private Servo armRightFront;
    double armPosition;

    public SlidesRotationAuto(HardwareMap hardwareMap){
        armLeftFront = hardwareMap.get(Servo.class, "servo1"); // on expansion hub
        armRightFront = hardwareMap.get(Servo.class, "servo2");
        armPosition = 0;
        armLeftFront.setPosition(armPosition);
        armRightFront.setPosition(1 - armPosition);
    }

    public class HighBasket implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            armLeftFront.setPosition(0.8);
            armRightFront.setPosition(0.2);
            return false;
        }
    }

    public class SubPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            armLeftFront.setPosition(0.0);
            armRightFront.setPosition(1.0);
            return false;
        }
    }

    public Action highBasket(){
        return new HighBasket();
    }
    public Action subPickUp(){
        return new SubPickUp();
    }
}