package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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

    private Servo wrist;
    private ServoController wristPosition;

    private Servo twist;
    private Servo claw;

    private Servo armLeftFront;
    private Servo armRightFront;

    private ServoController armPosition;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;
    double slidesStartingPosition;
    boolean twistModeActive;
    boolean twistLock;

    private static final double TICKSPERINCH = 75.71;

    boolean lastA = false;                      // Use to track the prior button state.
    boolean lastLB = false;                     // Use to track the prior button state.


    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slideExtensionMotor = hardwareMap.get(DcMotorEx.class, "slideExtensionMotor");
        slideExtensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slidesStartingPosition = slideExtensionMotor.getCurrentPosition();

        wrist = hardwareMap.get(Servo.class, "servo1e");
        twist = hardwareMap.get(Servo.class, "servo2e");
        claw = hardwareMap.get(Servo.class, "servo0e");
        wristPosition = new ServoController(0);
        wrist.setPosition(wristPosition.position);
        twist.setPosition(0.05);
        twistModeActive = false;
        twistLock = false;
        claw.setPosition(0.25);

        armLeftFront = hardwareMap.get(Servo.class, "servo4");
        armRightFront = hardwareMap.get(Servo.class, "servo5");

        armPosition = new ServoController(0.35);
        setArmRotationPosition(armPosition.position);

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

        telemetry.update();

        // wrist rotation
        // forward rotation
        if (gamepad2.left_bumper) {
            wristPosition.update(-0.008);
            wrist.setPosition(wristPosition.position);
        // backward rotation
        } else if (gamepad2.right_bumper) {
            wristPosition.update(0.008);
            wrist.setPosition(wristPosition.position);
        }


        //Claw
        if (gamepad2.cross) {
            //CLAW OPEN
            claw.setPosition(0.30);
        } else if (gamepad2.square) {
            //CLAW CLOSE
            claw.setPosition(0.25);
        }


        //twist rotation
        if (gamepad2.left_trigger > 0.5 && !twistLock && !twistModeActive) {
            // vertical
            twist.setPosition(0);
            twistLock = true;
            twistModeActive = true;
        } else if (gamepad2.left_trigger > 0.5 && !twistLock && twistModeActive) {
            // horizontal
            twist.setPosition(0.05);
            twistModeActive = false;
            twistLock = true;
        } else if (!(gamepad2.left_trigger > 0.5) && twistLock) {
            twistLock = false;
        }


        // forward slide rotation (toward floor)
        if (gamepad2.dpad_right) {
            setArmRotationPosition(armPosition.position);
            armPosition.update(0.010);
        // backward slide rotation (Up position)
        } else if (gamepad2.dpad_left) {
            setArmRotationPosition(armPosition.position);
            armPosition.update(-0.010);
        }


        // slides extend up (must hold button to hold slide position)
        if (gamepad2.dpad_up) {
            // extension limit
            if (armPosition.position > 0.2) {
                double pos = slideExtensionMotor.getCurrentPosition();
                if (pos > 16 * TICKSPERINCH + slidesStartingPosition) {
                    slideExtensionMotor.setPower(0);
                } else {
                    slideExtensionMotor.setPower(1.0);
                }
            } else {
                slideExtensionMotor.setPower(1.0);
            }
        // slides extend down (must hold button to hold slide position)
        } else if (gamepad2.dpad_down) {
            slideExtensionMotor.setPower(-0.5);
        } else {
            slideExtensionMotor.setPower(0);
        }


        //MACROS:

        //Pick up SPECIMEN from HUMAN PLAYER

        if (gamepad2.share) {

            //slides rotate down
            setArmRotationPosition(0.7678);
            //intake wrist up
            wristPosition.position = 0.2317;
            wrist.setPosition(wristPosition.position);
            //claw open
            claw.setPosition(0.30);
            // claw rotation in horizontal position
            twist.setPosition(0.05);
        }

        // Rotate to position for high specimen hang
        // need to hold down y for slide extension
        if(gamepad2.triangle) {
            // claw close
            claw.setPosition(0.25);
            wristPosition.position = 0;
            wrist.setPosition(wristPosition.position);
            setArmRotationPosition(0);
            if (armLeftFront.getPosition() == armPosition.position) {
                double pos = slideExtensionMotor.getCurrentPosition();
                if (pos < 7 * TICKSPERINCH + slidesStartingPosition) {
                    slideExtensionMotor.setPower(1);
                } else {
                    slideExtensionMotor.setPower(0);
                }
            }
        }


        // high basket macro: slide rotation, wrist rotation
        if (gamepad2.circle) {
            setArmRotationPosition(0.075);
            wristPosition.position = 0.75;
            wrist.setPosition(wristPosition.position);
        }


        // half power on drivetrain
        if(gamepad1.left_bumper) {
            leftFront.setPower(0.5 * frontLeftPower);
            rightFront.setPower(0.5 * frontRightPower);
            leftBack.setPower(0.5 * backLeftPower);
            rightBack.setPower(0.5 * backRightPower);
        } else {
            leftFront.setPower(frontLeftPower);
            rightFront.setPower(frontRightPower);
            leftBack.setPower(backLeftPower);
            rightBack.setPower(backRightPower);
        }

        telemetry.addData("twistPosition: ", twist.getPosition());
        telemetry.addData("slideExtensionPosition: ", slideExtensionMotor.getCurrentPosition());
        telemetry.addData("frontLeftPower: ", frontLeftPower);
        telemetry.addData("frontRightPower: ", frontRightPower);
        telemetry.addData("backLeftPower: ", backLeftPower);
        telemetry.addData("backRightPower: ", backRightPower);
        telemetry.addData("slideExtensionPower: ", slideExtensionMotor.getPower());
        telemetry.addData("armLeftFront: ", armLeftFront.getPosition());
        telemetry.addData("armRightFront: ", armRightFront.getPosition());
        telemetry.addData("intake rotation position: ", wrist.getPosition());
        telemetry.addData("claw position: ", claw.getPosition());
        telemetry.addData("armPosition: ", armPosition.position);

        telemetry.addData("wristPosition: " , wristPosition.position);

        telemetry.addData("LeftFront: ", leftFront.getCurrentPosition());
        telemetry.addData("LeftBack: ", leftBack.getCurrentPosition());
        telemetry.addData("RightFront: ", rightFront.getCurrentPosition());
        telemetry.addData("RightBack: ", rightBack.getCurrentPosition());

        telemetry.update();
    }

    // slide rotation function
    public void setArmRotationPosition (double position) {
        armPosition.position = position;
        armLeftFront.setPosition(armPosition.position);
        armRightFront.setPosition(1 - armPosition.position);
    }

}