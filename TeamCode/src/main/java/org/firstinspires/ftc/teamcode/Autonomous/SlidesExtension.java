package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class SlidesExtension {
    private DcMotorEx slideExtensionMotor;
    private Telemetry telemetry;

    public static final DcMotor.ZeroPowerBehavior BRAKE = null;

    public SlidesExtension(HardwareMap hardwareMap, Telemetry telemetryA) {
        slideExtensionMotor = hardwareMap.get(DcMotorEx.class, "slideExtensionMotor");
        telemetry = telemetryA;
    }

    public class HighBasketExtension implements Action {

        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideExtensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = slideExtensionMotor.getCurrentPosition();

            telemetry.addData("slideExtensionMotor pos: ", pos);
            telemetry.update();
            if (pos > 900.0) {
                return true;
            } else {
                slideExtensionMotor.setPower(0);
                return false;
            }
        }
    }

    public class LowBasketExtension implements Action {

        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideExtensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = slideExtensionMotor.getCurrentPosition();

            telemetry.addData("slideExtensionMotor pos: ", pos);
            telemetry.update();
            if (pos > 500.0) {
                return true;
            } else {
                slideExtensionMotor.setPower(0);
                return false;
            }
        }
    }


    public class SubmersibleExtension implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideExtensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = slideExtensionMotor.getCurrentPosition();

            telemetry.addData("slideExtensionMotor pos: ", pos);
            telemetry.update();
            if (pos > 350.0) {
                return true;
            } else {
                slideExtensionMotor.setPower(0.1);
                return false;
            }

        }
    }

    public class RetractSlideExtension implements Action {

        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideExtensionMotor.setPower(0.5);
                initialized = true;
            }

            double pos = slideExtensionMotor.getCurrentPosition();

            telemetry.addData("slideExtensionMotor pos: ", pos);
            telemetry.update();
            if (pos > 500.0) {
                return true;
            } else {
                slideExtensionMotor.setPower(0);
                return false;
            }

        }
    }
    public Action highBasketExtension() {
        return new HighBasketExtension();
    }
    public Action lowBasketExtension() {
        return new LowBasketExtension();
    }
    public Action submersibleExtension() {
        return new SubmersibleExtension();
    }
    public Action retractSlideExtension() {
        return new RetractSlideExtension();
    }
}