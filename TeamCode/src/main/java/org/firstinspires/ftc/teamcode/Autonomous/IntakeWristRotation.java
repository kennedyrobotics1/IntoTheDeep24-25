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
public class IntakeWristRotation {
    private Servo intakeWristRotation;
    private Telemetry telemetry;

    public IntakeWristRotation(HardwareMap hardwareMap, Telemetry telemetryB){
       intakeWristRotation = hardwareMap.get(Servo.class, "servo5");
        telemetry = telemetryB;
    }

    public class HighBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intakeWristRotation.setPosition(0.80);
                initialized = true;
            }
            return true;
        }
    }

    public class SamplesFromSub implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intakeWristRotation.setPosition(0.20);
                initialized = true;
            }
            return true;
        }
    }

    public Action highBasket(){
        return new HighBasket();
    }

    public Action samplesFromSub(){
        return new SamplesFromSub();
    }

}