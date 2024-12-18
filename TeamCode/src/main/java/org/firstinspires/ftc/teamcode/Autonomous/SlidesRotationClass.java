package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Config
public class SlidesRotationClass{
    private Servo slideLeft;
    private Servo slideRight;
    private AnalogInput armLeftFrontEncoder;
    public ElapsedTime time = new ElapsedTime();


    public SlidesRotationClass(HardwareMap hardwareMap){
        slideLeft = hardwareMap.get(Servo.class, "servo1");
        slideRight = hardwareMap.get(Servo.class, "servo2");
        armLeftFrontEncoder = hardwareMap.get(AnalogInput.class, "armLeftFrontEncoder");
        setArmRotationPosition(0);
        time.reset();
    }


    public class HighBasket implements Action {
        private boolean initialized = false;
        public double initialTime = time.milliseconds();

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setArmRotationPosition(0.3);
                initialized = true;
            }

            // checks lift's current position
            double position = armLeftFrontEncoder.getVoltage() / 3.3 * 360;
            packet.put("liftPos", position);
            if (position > 255) {
                // true causes the action to rerun
                return true;
            } else if ((time.milliseconds() - initialTime) >= 3000) {
                return false;
            } else {
                // false stops action rerun
                return false;
            }
        }
    }


    public class HighSpecimen implements Action {
        private boolean initialized = false;
        private double initialTime = time.milliseconds();

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                setArmRotationPosition(0.175);
                initialized = true;
            }

            // checks lift's current position
            double position = armLeftFrontEncoder.getVoltage() / 3.3 * 360;
            packet.put("liftPos", position);
            if (position > 150) {
                // true causes the action to rerun
                return true;
            }  else if ((time.milliseconds() - initialTime) >= 3000) {
                return false;
            } else {
                // false stops action rerun
                return false;
            }
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
    public Action highSpecimen(){
        return new HighSpecimen();
    }
    public Action home() {
        return new Home();
    }

    public void setArmRotationPosition (double position) {
        slideLeft.setPosition(position);
        slideRight.setPosition(1 - position);
    }
}