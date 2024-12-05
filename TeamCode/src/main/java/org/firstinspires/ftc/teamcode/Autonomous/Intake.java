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
public class Intake {
    private Servo intake;
    private Telemetry telemetry;
    public Intake(HardwareMap hardwareMap, Telemetry telemetryB){
        intake = hardwareMap.get(Servo.class, "servo3");
        telemetry = telemetryB;

    }


    public class Open implements Action {
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake.setPosition(0.3);
                initialized = true;
            }
            return true;
        }
    }
    public Action open() {
        return new Open();
    }


    public class Close implements Action {
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake.setPosition(0.25);
                initialized = true;
            }
            return true;
        }
    }


//    public Action open() {
//        return new Open();
//    }

    public Action close() {
        return new Close();
    }

}