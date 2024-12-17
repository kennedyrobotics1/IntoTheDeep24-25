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
public class ClawClass {
    private Servo servo;

    public ClawClass(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo3");
    }

    public class Open implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0.3);
            return true;
        }
    }

    public Action open() {
        return new Open();
    }

    public class Close implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0.25);
            return true;
        }
    }

    public Action close() {
        return new Close();
    }


}











//
//
//public class Close implements Action {
//        private boolean initialized = false;
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            intake.setPosition(0.25);
//            return false;
//        }
//    }
//
//    public Action close() {
//        return new Close();
//    }
//
//}