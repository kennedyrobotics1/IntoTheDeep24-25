package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Intake {
    private CRServo intake;
    private Telemetry telemetry;

    public Intake(HardwareMap hardwareMap, Telemetry telemetryB){
        intake = hardwareMap.get(CRServo.class, "servo0");
        telemetry = telemetryB;
    }

    public class IntakeIn implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake.setPower(1.0);
                initialized = true;
            }
            return true;
        }
    }
    public Action intakeIn(){
        return new IntakeIn();
    }

    public class IntakeOut implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                intake.setPower(-1.0);
                initialized = true;
            }
            return true;
        }
    }
    public Action intakeOut() {
        return new IntakeOut();
    }
}