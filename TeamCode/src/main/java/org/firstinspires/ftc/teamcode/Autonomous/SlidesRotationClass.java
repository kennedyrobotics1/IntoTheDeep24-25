package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Teleop.ServoController;


@Config
public class SlidesRotationClass{
    private Servo slideLeft;
    private Servo slideRight;

    public SlidesRotationClass(HardwareMap hardwareMap){
        slideLeft = hardwareMap.get(Servo.class, "servo4");
        slideRight = hardwareMap.get(Servo.class, "servo5");
    }

    public class highBarSpecimen implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setArmRotationPosition(0);
            return false;
        }
    }

    public Action highBarSpecimen() {
        return new ParallelAction(
                new highBarSpecimen(),
                new SleepAction(2)
        );
    }









    public class PickUpSpecimenFromHumanPlayer implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            setArmRotationPosition(.7678);
            return false;
        }
    }

    public Action pickUpSpecimenFromHumanPlayer() {
        return new ParallelAction(
                new PickUpSpecimenFromHumanPlayer(),
                new SleepAction(2)
        );
    }






    public void setArmRotationPosition (double position) {
        slideLeft.setPosition(position);
        slideRight.setPosition(1 - position);
    }
}