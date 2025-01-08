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
public class ClawClass {
    private Servo claw;

    public ClawClass(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "servo0e");
    }

    public class Open implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(0.3);
            return false;
        }
    }
    public Action open() {
        return new ParallelAction(
                new Open(),
                new SleepAction(0.8)
        );
    }

    public class Close implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            claw.setPosition(0.25);
            return false;
        }
    }
    public Action close() {
        return new ParallelAction(
                new Close(),
                new SleepAction(2)
        );
    }
}