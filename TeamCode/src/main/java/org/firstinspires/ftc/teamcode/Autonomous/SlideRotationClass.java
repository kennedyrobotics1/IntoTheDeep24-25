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

public class SlideRotationClass {
    private DcMotor rotationMotor;
    private Telemetry telemetry;

    public SlideRotationClass(HardwareMap hardwareMap, Telemetry telemetryB) {
        rotationMotor = hardwareMap.get(DcMotor.class, "slideRotationMotor");
//        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = telemetryB;
    }

    public class HighSpecimenPosition implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                rotationMotor.setPower(-0.8);
                rotationMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = rotationMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 100) {
                return true;
            } else {
                rotationMotor.setPower(0);
                return false;
            }
        }
    }

    public Action highSpecimenPosition() {
        return new ParallelAction(
                new HighSpecimenPosition(),
                new SleepAction(1.0)
        );
    }

}
