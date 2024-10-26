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
public class ArmRotation {
    private Servo armLeftFront;
    private Servo armRightFront;
    private Telemetry telemetry;
    double armPosition;

    public ArmRotation(HardwareMap hardwareMap, Telemetry telemetryA){
        armLeftFront = hardwareMap.get(Servo.class, "servo1e"); // on expansion hub
        armRightFront = hardwareMap.get(Servo.class, "servo1");
        armPosition = 0;
        armLeftFront.setPosition(armPosition);
        armRightFront.setPosition(1 - armPosition);
        telemetry = telemetryA;
    }

    public class SetLowest implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("armLeftFront: ", armLeftFront.getPosition());
            telemetry.addData("armRightFront: ", armRightFront.getPosition());
            telemetry.update();
            armLeftFront.setPosition(1.0);
            armRightFront.setPosition(1.0);
            if (armLeftFront.getPosition() != 1.0) {
                return false;
            } else {
                return true;
            }
        }
    }
    public Action setLowest(){
        return new SetLowest();
    }
}