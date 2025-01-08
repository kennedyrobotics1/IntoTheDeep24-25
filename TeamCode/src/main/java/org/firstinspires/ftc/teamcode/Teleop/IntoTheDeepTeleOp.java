package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

    private DcMotor slideExtensionMotor = null;

    private Servo intakeRotation;
    private ServoController intakeRotationPosition;
    private Servo clawRotation;
    private Servo claw;

    private Servo armLeftFront;
    private Servo armRightFront;

    private ServoController armPosition;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;
    double clawRotationPosition;


    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slideExtensionMotor = hardwareMap.get(DcMotorEx.class, "slideExtensionMotor");
        slideExtensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeRotation = hardwareMap.get(Servo.class, "servo1e");
        clawRotation = hardwareMap.get(Servo.class, "servo2e");
        claw = hardwareMap.get(Servo.class, "servo0e");
        intakeRotationPosition = new ServoController(0);
        clawRotationPosition = 0;

        armLeftFront = hardwareMap.get(Servo.class, "servo4");
        armRightFront = hardwareMap.get(Servo.class, "servo5");

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

        // wrist rotation
        // forward rotation
        if (gamepad2.left_bumper) {
            intakeRotationPosition.update(-0.008);
            intakeRotation.setPosition(intakeRotationPosition.position);
        // backward rotation
        } else if (gamepad2.right_bumper) {
            intakeRotationPosition.update(0.008);
            intakeRotation.setPosition(intakeRotationPosition.position);
        }


        //Claw
        if (gamepad2.a) {
            //CLAW OPEN
            claw.setPosition(0.30);
        } else if (gamepad2.x) {
            //CLAW CLOSE
            claw.setPosition(0.25);
        }


        //twist rotation
        if (gamepad2.right_trigger > 0.5) {
            //horizontal
            clawRotation.setPosition(0.05);
        } else if (gamepad2.left_trigger > 0.5) {
            //vertical
            clawRotation.setPosition(0);
        }



        // forward slide rotation (toward floor)
        if (gamepad2.dpad_right) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(0.008);
        // backward slide rotation (Up position)
        } else if (gamepad2.dpad_left) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(-0.008);
        }

        // slides extend up (must hold button to hold slide position)
        if (gamepad2.dpad_up) {
            slideExtensionMotor.setPower(1.0);
        // slides extend down (must hold button to hold slide position)
        } else if (gamepad2.dpad_down) {
            slideExtensionMotor.setPower(-0.5);
        } else {
            slideExtensionMotor.setPower(0);
        }


        //MACROS:

        //Pick up SPECIMEN from HUMAN PLAYER

        if (gamepad2.back) {

            //slides rotate down
            armPosition = new ServoController(0.6);
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            //intake wrist up
            intakeRotation.setPosition(0.4);

            //claw open
            claw.setPosition(0.30);
        }



        // half power on drivetrain
        if(gamepad1.left_bumper) {
            leftFront.setPower(0.4 * frontLeftPower);
            rightFront.setPower(0.4 * frontRightPower);
            leftBack.setPower(0.4 * backLeftPower);
            rightBack.setPower(0.4 * backRightPower);
        } else {
            leftFront.setPower(0.8 * frontLeftPower);
            rightFront.setPower(0.8 * frontRightPower);
            leftBack.setPower(0.8 * backLeftPower);
            rightBack.setPower(0.8 * backRightPower);
        }

        telemetry.addData("twistPosition", clawRotationPosition);
        telemetry.addData("frontLeftPower ", frontLeftPower);
        telemetry.addData("frontRightPower ", frontRightPower);
        telemetry.addData("backLeftPower ", backLeftPower);
        telemetry.addData("backRightPower ", backRightPower);
        telemetry.addData("slideExtensionPower", slideExtensionMotor.getPower());
        telemetry.addData("armLeftFront: ", armLeftFront.getPosition());
        telemetry.addData("armRightFront: ", armRightFront.getPosition());
        telemetry.addData("intake rotation position: ", intakeRotation.getPosition());
        telemetry.addData("claw position: ", claw.getPosition());
        telemetry.addData("armPosition", armPosition.position);

        telemetry.addData("intakeArmRotationPosition" , intakeRotationPosition.position);

        telemetry.addData("LeftFront", leftFront.getCurrentPosition());
        telemetry.addData("LeftBack", leftBack.getCurrentPosition());
        telemetry.addData("RightFront", rightFront.getCurrentPosition());
        telemetry.addData("RightBack", rightBack.getCurrentPosition());

        telemetry.update();
    }
}