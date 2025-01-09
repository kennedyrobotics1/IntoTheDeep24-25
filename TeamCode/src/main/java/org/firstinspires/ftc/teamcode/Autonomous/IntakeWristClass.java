package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class IntakeWristClass {
    private Servo intakeWristRotation;
    private Telemetry telemetry;

    public IntakeWristClass(HardwareMap hardwareMap, Telemetry telemetryB){
        intakeWristRotation = hardwareMap.get(Servo.class, "servo1e");
        telemetry = telemetryB;
    }

    public class Out implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(1);
            return false;
        }
    }

    public Action out() {
        return new ParallelAction(
                new Out(),
                new SleepAction(2)
        );
    }


    public class forwardToPlaceSpecimen implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(0.9);
            return false;
        }
    }

    public Action forwardToPlaceSpecimen() {
        return new ParallelAction(
                new forwardToPlaceSpecimen(),
                new SleepAction(2)
        );
    }


    public class Home implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(0.2);
            return false;
        }
    }

    public Action home() {
        return new ParallelAction(
                new Home(),
                new SleepAction(2)
        );
    }

    public class PickUpSpecimenFromHumanPlayer implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeWristRotation.setPosition(0.4);
            return false;
        }
    }

    public Action pickUpSpecimenFromHumanPlayer() {
        return new ParallelAction(
                new PickUpSpecimenFromHumanPlayer(),
                new SleepAction(2)
        );
    }
}