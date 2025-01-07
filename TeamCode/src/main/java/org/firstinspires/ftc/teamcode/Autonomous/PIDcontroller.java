package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDcontroller extends LinearOpMode {

    DcMotorEx motor;

    double integralSum = 0;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;

    ElapsedTime timer = new ElapsedTime();
    private double lastError = 0;

    public PIDcontroller(double p, double i, double d) {
    }

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        waitForStart();
        while (opModeIsActive()) {

        }
    }


    public double PIDControl (double reference, double state) {
        double error = reference - state;
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) * timer.seconds();
        lastError = error;

        timer.reset();


        double output = (error * Kp);
    }

    public double calculate(int armPos, int target) {
    }

    public void setPID(double p, double i, double d) {
    }

}