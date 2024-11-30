package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;
import org.firstinspires.ftc.teamcode.Teleop.ServoController;

@TeleOp(name = "IntoTheDeepTeleOp", group = "Iterative OpMode")

public class IntoTheDeepTeleOp extends BasicOpMode_Iterative {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;

    private DcMotor slideExtensionMotor = null;

    private Servo intakeRotation;
    private ServoController intakeRotationPosition;
    private Servo clawRotation;
    private CRServo clawServoLeft;
    private CRServo clawServoRight;

    private Servo armLeftFront;
    private Servo armRightFront;
    private ServoController armPosition;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;

    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slideExtensionMotor = hardwareMap.get(DcMotorEx.class, "slideExtensionMotor");
        slideExtensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeRotation = hardwareMap.get(Servo.class, "servo5");

        clawServoLeft = hardwareMap.get(CRServo.class, "servo3");
        clawServoRight  = hardwareMap.get(CRServo.class, "servo4");

        armLeftFront = hardwareMap.get(Servo.class, "servo1");
        armRightFront = hardwareMap.get(Servo.class, "servo2");

        armPosition = new ServoController(0);
        armLeftFront.setPosition(armPosition.position);
        armRightFront.setPosition(1 - armPosition.position);

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

        // rotating entire intake
        // forward rotation
        if (gamepad1.y) {
            intakeRotation.setPosition(0.75);
        }
        if (gamepad1.x) {
            intakeRotation.setPosition(0.25);
        }

        double ClawPower = 0.5;
//      Intake Claw
        if (gamepad1.left_trigger > 0.7) {
            clawServoLeft.setPower(-ClawPower);
            clawServoRight.setPower(ClawPower);
//      Outake Claw
        } else if (gamepad1.right_trigger > 0.7) {
            clawServoLeft.setPower(ClawPower/2);
            clawServoRight.setPower(-ClawPower/2);
        }
        else {
            clawServoLeft.setPower(0);
            clawServoRight.setPower(0);
        }

        // forward arm rotation (toward floor)
        if (gamepad1.dpad_up) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(0.005);
        // backward arm rotation
        } else if (gamepad1.dpad_down) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(-0.005);
        }

        // slides go up (must hold button to hold slide position)
        if (gamepad1.b) {
            slideExtensionMotor.setPower(0.5);
        // slides go down (must hold button to hold slide position)
        } else if (gamepad1.a) {
            slideExtensionMotor.setPower(-0.5);
        } else {
            slideExtensionMotor.setPower(0);
        }

        double SlowSpeed = 0.35;
        double NormalSpeed = .6;
        double FastSpeed = 1;

        // half power on drivetrain
        if(gamepad1.left_bumper){
            leftFront.setPower(SlowSpeed * frontLeftPower);
            rightFront.setPower(SlowSpeed * frontRightPower);
            leftBack.setPower(SlowSpeed * backLeftPower);
            rightBack.setPower(SlowSpeed * backRightPower);
        } else if (gamepad1.right_bumper){
            leftFront.setPower(FastSpeed * frontLeftPower);
            rightFront.setPower(FastSpeed * frontRightPower);
            leftBack.setPower(FastSpeed * backLeftPower);
            rightBack.setPower(FastSpeed * backRightPower);
        }
        else {
            leftFront.setPower(NormalSpeed * frontLeftPower);
            rightFront.setPower(NormalSpeed * frontRightPower);
            leftBack.setPower(NormalSpeed * backLeftPower);
            rightBack.setPower(NormalSpeed * backRightPower);
        }

    }
}