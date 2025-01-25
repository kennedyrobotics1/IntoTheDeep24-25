package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class TwistClass {
    private Servo twist;

    public TwistClass(HardwareMap hardwareMap) {
        twist = hardwareMap.get(Servo.class, "servo2e");
    }

    public class Horizontal implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            twist.setPosition(0.024);
            return false;
        }
    }
    public Action horizontal() {
        return new ParallelAction(
                new Horizontal(),
                new SleepAction(0.3)
        );
    }

    public class Vertical implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            twist.setPosition(0);
            return false;
        }
    }
    public Action vertical() {
        return new ParallelAction(
                new Vertical(),
                new SleepAction(0.3)
        );
    }
}