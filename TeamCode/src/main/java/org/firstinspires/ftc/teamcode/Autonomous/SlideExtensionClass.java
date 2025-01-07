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
public class SlideExtensionClass {
    private DcMotor extensionMotor;
    private Telemetry telemetry;
    private static final double TICKSPERINCH = 107.744107744;

    public SlideExtensionClass(HardwareMap hardwareMap, Telemetry telemetryB) {
        extensionMotor = hardwareMap.get(DcMotor.class, "slideExtensionMotor");
        extensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = telemetryB;
    }

    public class HighSpecimenExtend implements Action {
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
            if (pos < 21 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action highSpecimenExtend() {
        return new ParallelAction(
                new HighSpecimenExtend(),
                new SleepAction(1.2)
        );
    }

}
