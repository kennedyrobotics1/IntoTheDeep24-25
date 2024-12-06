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

    public class Out implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(0.2);
            return false;
        }
    }
    public Action out(){
        return new Out();
    }


    public class Home implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(0.9);
            return false;
        }
    }

    public Action home(){return new Home();}

}