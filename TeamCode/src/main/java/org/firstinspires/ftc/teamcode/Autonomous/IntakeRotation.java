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
public class IntakeRotation {
    private Servo intakeRotation;
    private Telemetry telemetry;

    public IntakeRotation(HardwareMap hardwareMap, Telemetry telemetryB){
        intakeRotation = hardwareMap.get(Servo.class, "servo5");
        telemetry = telemetryB;
    }

    public class HighBasket implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intakeRotation.setPosition(0.75);
                initialized = true;
            }
            return true;
        }
    }
    public Action highBasket(){
        return new HighBasket();
    }

}