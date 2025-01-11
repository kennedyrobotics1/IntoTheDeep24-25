package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ExtensionClass {
    private DcMotor extensionMotor;
    private Telemetry telemetry;
    private static final double TICKSPERINCH = 75.71;

    public ExtensionClass(HardwareMap hardwareMap, Telemetry telemetryB) {
        extensionMotor = hardwareMap.get(DcMotor.class, "slideExtensionMotor");
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = telemetryB;
    }








    public class SpecimenHighBar implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(-1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 7 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action highBarSpecimen() {
        return new ParallelAction(
                new SpecimenHighBar()
//                new SleepAction(2)
        );
    }










    public class SpecimenHighBarOuttake implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(-1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 15 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action specimenHighBarOuttake() {
        return new ParallelAction(
                new SpecimenHighBarOuttake(),
                new SleepAction(2)
        );
    }







    public class retraction implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > 0 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action retraction() {
        return new ParallelAction(
                new retraction(),
                new SleepAction(2)
        );
    }










    public class PickupFromHumanPlayer implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 5 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action pickupFromHumanPlayer() {
        return new ParallelAction(
                new PickupFromHumanPlayer(),
                new SleepAction(2)
        );
    }
}