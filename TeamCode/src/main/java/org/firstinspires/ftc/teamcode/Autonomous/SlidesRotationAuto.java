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
public class SlidesRotationAuto {
    private Servo slideLeft;
    private Servo slideRight;


    public SlidesRotationAuto(HardwareMap hardwareMap){
        slideLeft = hardwareMap.get(Servo.class, "servo1");
        slideRight = hardwareMap.get(Servo.class, "servo2");
        setArmRotationPosition(0);
    }

    public class HighBasket implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setArmRotationPosition(0.2);
            return false;
        }
    }

    public class SubPickUp implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setArmRotationPosition(1.0);
            return false;
        }
    }

    public class Home implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setArmRotationPosition(0.0);
            return false;
        }
    }

    public Action highBasket(){return new HighBasket();}
    public Action subPickUp(){
        return new SubPickUp();
    }
    public Action home() {
        return new Home();
    }

    public void setArmRotationPosition (double position) {
        slideLeft.setPosition(position);
        slideRight.setPosition(1 - position);
    }
}