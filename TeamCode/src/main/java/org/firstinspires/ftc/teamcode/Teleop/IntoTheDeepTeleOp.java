package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name = "IntoTheDeepTeleOp", group = "Iterative OpMode")

public class IntoTheDeepTeleOp extends BasicOpMode_Iterative {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;

    private DcMotor slideLeftMotor = null;
    private DcMotor slideRightMotor = null;

    private CRServo intake;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower, slideLeftPower, slideRightPower;

    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slideLeftMotor = hardwareMap.get(DcMotorEx.class, "slideLeftMotor");
        slideRightMotor = hardwareMap.get(DcMotorEx.class, "slideRightMotor");

        intake = hardwareMap.get(CRServo.class, "servo0");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
    }

    public void start(){

    }

    public void loop(){

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;
        double denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(r), 1);

        frontLeftPower  = (y + x + r) / denominator;
        frontRightPower = (y - x - r) / denominator;
        backLeftPower   = (y - x + r) / denominator;
        backRightPower  = (y + x - r) / denominator;

        // intake in
        if (gamepad2.y) {
            intake.setPower(1.0);
        // intake out
        } else if (gamepad2.a) {
            intake.setPower(-1.0);
        } else {
            intake.setPower(0.0);
        }

        // slides go up (must hold button to hold slide position)
        if (gamepad2.dpad_up) {
            slideLeftMotor.setPower(-0.75);
            slideRightMotor.setPower(0.75);
        // slides go down (must hold button to hold slide position)
        } else if (gamepad2.dpad_down) {
            slideLeftMotor.setPower(0.75);
            slideRightMotor.setPower(-0.75);
        } else {
            slideLeftMotor.setPower(0);
            slideRightMotor.setPower(0);
        }

        //half power on drivetrain
        if(gamepad1.left_bumper){
            leftFront.setPower(0.5 * frontLeftPower);
            rightFront.setPower(0.5 * frontRightPower);
            leftBack.setPower(0.5 * backLeftPower);
            rightBack.setPower(0.5 * backRightPower);
        } else {
            leftFront.setPower(frontLeftPower);
            rightFront.setPower(frontRightPower);
            leftBack.setPower(backLeftPower);
            rightBack.setPower(backRightPower);

            telemetry.addData("frontLeftPower ", frontLeftPower);
            telemetry.addData("frontRightPower ", frontRightPower);
            telemetry.addData("backLeftPower ", backLeftPower);
            telemetry.addData("backRightPower ", backRightPower);
            telemetry.addData("slideLeftPower", slideLeftPower);
            telemetry.addData("slideRightPower", slideRightPower);
            telemetry.update();
        }
    }
}