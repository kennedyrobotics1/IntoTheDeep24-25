package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    private CRServo intake;
    private Servo armLeftFrontServo;
    private Servo armLeftBackServo;
    private Servo armRightFrontServo;
    private Servo armRightBackServo;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;

    double armPosition = 0;
    double armPositionFive = 0;

    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        intake = hardwareMap.get(CRServo.class, "servo1");
        armLeftFrontServo = hardwareMap.get(Servo.class, "servo2");
        armLeftBackServo = hardwareMap.get(Servo.class, "servo3");
        armRightFrontServo = hardwareMap.get(Servo.class, "servo4");
        armRightFrontServo.setDirection(Servo.Direction.REVERSE);
        armRightBackServo = hardwareMap.get(Servo.class, "servo5");
        armRightBackServo.setDirection(Servo.Direction.REVERSE);

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

        if (gamepad1.x) {
            intake.setPower(1.0);
        } else if (gamepad1.b) {
            intake.setPower(-1.0);
        } else {
            intake.setPower(0.0);
        }

        if (gamepad1.dpad_up) {
            armPosition += 0.01;
            //  += 0.01/5;
            if (armRightFrontServo.getPosition() > 1.0/5) {
                armPosition = 1.0;
                // armPositionFive = 1.0/5;
            }
            armRightBackServo.setPosition(armPosition);
            armRightFrontServo.setPosition(armPosition / 5);
            // armRightFrontServo.setPosition(armPositionFive);
        } else if (gamepad1.dpad_down) {
            armPosition -= 0.01;
            // armPositionFive -= 0.01/5;
            if (armRightFrontServo.getPosition() < 0.0) {
                armPosition = 0.0;
                // armPositionFive = 0.0;
            }
            armRightBackServo.setPosition(armPosition);
            armRightFrontServo.setPosition(armPosition / 5);
        } /* else {
            double armCurrentPosition = armLeftFrontServo.getPosition();
            armPosition = armCurrentPosition;
            armLeftFrontServo.setPosition(armPosition);
        } */

        /*
        if (armLeftServo.getPosition() < 0) {
                armPosition = 0.0;
            } else if (armLeftServo.getPosition() > 1) {
                armPosition = 1.0;
            }
         */

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
            telemetry.addData("servo position: ", armRightFrontServo.getPosition());
            telemetry.update();
        }
    }
}