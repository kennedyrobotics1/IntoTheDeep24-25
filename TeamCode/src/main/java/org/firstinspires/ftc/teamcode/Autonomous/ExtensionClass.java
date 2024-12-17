package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ExtensionClass {
    private DcMotor extensionMotor;
    private Telemetry telemetry;

    public ExtensionClass(HardwareMap hardwareMap, Telemetry telemetryB){
        extensionMotor = hardwareMap.get(DcMotor.class, "slideExtensionMotor");
        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extensionMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry = telemetryB;
    }

    public class ExtensionHigh implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 1700.0) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }
    public Action extensionHigh() {
        return new ExtensionHigh();
    }



    public class SpecimenHigh implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(0.5);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 1000.0) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }
    public Action specimenHigh() {
        return new SpecimenHigh();
    }



    public class SpecimenHighLittle implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 1000.0) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }
    public Action specimenHighLittle() {
        return new SpecimenHigh();
    }







//
//    public class ExtensionHigh implements Action {
//        private boolean initialized = false;
//
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                extensionMotor.setPower(.5);
//                initialized = true;
//            }
//            if (extensionMotor.getCurrentPosition() < 100) {
//                return true;
//            } else {
//                extensionMotor.setPower(0);
//                return false;
//            }
//        }
//    }
//    public Action extensionHigh() {return new ExtensionHigh();}



    public class ExtensionLow implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                extensionMotor.setPower(-.5);
                initialized = true;
            }
            if (extensionMotor.getCurrentPosition() > 10) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }

        }
    }
    public Action extensionLow() {
        return new ExtensionLow();
    }

}