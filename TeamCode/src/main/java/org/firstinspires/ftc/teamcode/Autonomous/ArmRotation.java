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
    //private Servo armLeft;
    //private Servo armRight;
    private Servo armTest;
    private Telemetry telemetry;

    public ArmRotation(HardwareMap hardwareMap, Telemetry telemetryA){
        //armLeft = hardwareMap.get(Servo.class, "servo0e");
        //armLeft.setDirection(Servo.Direction.REVERSE);
        //armRight = hardwareMap.get(Servo.class, "servo1");
        armTest = hardwareMap.get(Servo.class, "servo4");
        armTest.setDirection(Servo.Direction.REVERSE);
        telemetry = telemetryA;
    }

    public class SetLowest implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            telemetry.addData("arm pos (degs): ", armTest.getPosition() * 355);
            telemetry.update();
            armTest.setPosition(1.0);
            if (armTest.getPosition() != 1.0) {
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