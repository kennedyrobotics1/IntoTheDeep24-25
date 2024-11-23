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
public class Claw {
    private Servo claw;
    private Telemetry telemetry;

    public Claw(HardwareMap hardwareMap, Telemetry telemetryB){
        claw = hardwareMap.get(Servo.class, "servo3");
        telemetry = telemetryB;
    }

    public class Open implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                claw.setPosition(0.75);
                initialized = true;
            }
            return true;
        }
    }
    public Action open(){
        return new Open();
    }

}