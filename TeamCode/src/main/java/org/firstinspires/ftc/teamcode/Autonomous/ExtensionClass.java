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
public class ExtensionClass {
    private DcMotor extensionMotor;
    private Telemetry telemetry;
    private static final double TICKSPERINCH = 75.71;

    public ExtensionClass(HardwareMap hardwareMap, Telemetry telemetryB) {
        extensionMotor = hardwareMap.get(DcMotor.class, "slideExtensionMotor");
        extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry = telemetryB;
    }






    public class HighBarSpecimen implements Action {
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
            if (pos < 7.5 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0.05);
                return false;
            }
        }
    }

    public Action highBarSpecimen() {
        return new ParallelAction(
                new HighBarSpecimen(),
                new SleepAction(0.05)
        );
    }










    public class HumanPlayerSpecimenPickup implements Action {
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
            if (pos < 6.625 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action humanPlayerSpecimenPickup() {
        return new ParallelAction(
                new HumanPlayerSpecimenPickup(),
                new SleepAction(0.05)
        );
    }













    public class  SampleHighBasket implements Action{
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if (!initialized){
                extensionMotor.setPower(1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }
            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 23.5 *TICKSPERINCH){
                return true;
            } else{
                extensionMotor.setPower(0.08);
                return false;
            }
        }

    }

    public Action highBasketSample(){
        return new ParallelAction(
                new SampleHighBasket(),
                new SleepAction(0.75)
        );
    }







    //test out
    public class  LevelOneAscent implements Action{
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if (!initialized){
                extensionMotor.setPower(0.8);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }
            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 15 *TICKSPERINCH){
                return true;
            } else{
                extensionMotor.setPower(0);
                return false;
            }
        }

    }

    public Action ascentLevelOne(){
        return new ParallelAction(
                new LevelOneAscent(),
                new SleepAction(2)
        );
    }

    //test out
    public class  YellowSamplePickUp implements Action{
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet){
            if (!initialized){
                extensionMotor.setPower(0.8);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }
            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos < 10 *TICKSPERINCH){
                return true;
            } else{
                extensionMotor.setPower(0.08);
                return false;
            }
        }

    }
    public Action sampleYellowPickUp(){
        return new ParallelAction(
                new YellowSamplePickUp(),
                new SleepAction(2)
        );
    }








    public class SpecimenHighBarOuttake implements Action {
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
            if (pos < 13 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action specimenHighBarOuttake() {
        return new ParallelAction(
                new SpecimenHighBarOuttake(),
                new SleepAction(0.5)
        );
    }







    public class Retract implements Action {
        private boolean initialized = false;
        double slidesStartingPosition;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slidesStartingPosition = extensionMotor.getCurrentPosition();
                extensionMotor.setPower(-1);
                extensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
            }

            double pos = extensionMotor.getCurrentPosition();
            packet.put("liftPos", pos);
            if (pos > slidesStartingPosition) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Action retract() {
        return new ParallelAction(
                new Retract(),
                new SleepAction(0.57)
        );
    }










    public class PickupFromHumanPlayer implements Action {
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
            if (pos < 5 * TICKSPERINCH) {
                return true;
            } else {
                extensionMotor.setPower(0);
                return false;
            }
        }
    }

    public Action pickupFromHumanPlayer() {
        return new ParallelAction(
                new PickupFromHumanPlayer(),
                new SleepAction(2)
        );
    }

}