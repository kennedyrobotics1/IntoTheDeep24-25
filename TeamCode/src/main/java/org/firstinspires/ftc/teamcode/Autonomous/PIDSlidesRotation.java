package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@Config
public class PIDSlidesRotation {

    private DcMotor mSlidesLeft;
    private DcMotor mSlidesRight;
    PIDController cSlidesLeft = new PIDController();
    PIDController cSlidesRight = new PIDController();

    double slidesSpeed;
    long startTime;
    double time;
    double previousTime;
    double deltaTime;

    public PIDSlidesRotation(HardwareMap hardwareMap){
        mSlidesLeft = hardwareMap.get(DcMotor.class, "slidesMotorLeft");
        mSlidesRight = hardwareMap.get(DcMotor.class, "slidesMotorRight");
        // placeholder values
        cSlidesLeft.setCoefficients(0.007, 0.0, 0.0003);
        cSlidesRight.setCoefficients(0.007, 0.0, 0.0003);
        // placeholder value
        slidesSpeed = 2500 / 3.0;
    }

    public class HighBarSpecimen implements Action {
        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                mSlidesLeft.setPower(cSlidesLeft.getSum());
                mSlidesRight.setPower(cSlidesRight.getSum());
                mSlidesLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                mSlidesRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                initialized = true;
                cSlidesLeft.startPoint(mSlidesLeft.getCurrentPosition());
                cSlidesRight.startPoint(mSlidesRight.getCurrentPosition());
                cSlidesLeft.setPoint(0);
                cSlidesRight.setPoint(0);
                updateTime();
            }

            cSlidesLeft.update(mSlidesLeft.getCurrentPosition(), deltaTime);
            cSlidesRight.update(mSlidesRight.getCurrentPosition(), deltaTime);

            if (cSlidesLeft.setPoint < 0) {
                cSlidesLeft.setPoint(0);
            }
            if (cSlidesRight.setPoint < 0) {
                cSlidesRight.setPoint(0);
            }
            if (cSlidesLeft.setPoint > 200) {
                cSlidesLeft.setPoint(200);
            }
            if (cSlidesRight.setPoint > 200) {
                cSlidesRight.setPoint(200);
            }

            if (mSlidesLeft.getCurrentPosition() < 100) {
                return true;
            } else {
                mSlidesLeft.setPower(0);
                return false;
            }
        }
    }

    public Action highBarSpecimen() {
        return new ParallelAction(
                new PIDSlidesRotation.HighBarSpecimen(),
                new SleepAction(1.2)
        );
    }

    public void updateTime() {
        previousTime = time;
        time = ( (double) (System.nanoTime() - startTime) ) / Math.pow(10,9);
        deltaTime = time - previousTime;
    }
}