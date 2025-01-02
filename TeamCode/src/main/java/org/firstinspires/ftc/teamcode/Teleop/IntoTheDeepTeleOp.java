package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
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
        slideExtensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        slideExtensionMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intakeRotation = hardwareMap.get(Servo.class, "servo5");
        clawRotation = hardwareMap.get(Servo.class, "servo4");
        claw = hardwareMap.get(Servo.class, "servo3");
        intakeRotationPosition = new ServoController(0);
        clawRotationPosition = 0;

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
        if (gamepad2.cross) {
            //CLAW OPEN
            claw.setPosition(0.30);
        } else if (gamepad2.square) {
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
            armPosition.update(0.004);
        // backward slide rotation (Up position)
        } else if (gamepad2.dpad_left) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(-0.004);
        }



        // slides extend up (must hold button to hold slide position)
        if (gamepad2.dpad_up) {
            slideExtensionMotor.setPower(1.0);
        // slides extend down (must hold button to hold slide position)
        } else if (gamepad2.dpad_down) {
            slideExtensionMotor.setPower(-1.0);
        } else {
            slideExtensionMotor.setPower(0);
        }



        //MACROS:

        //Pick up SPECIMEN from HUMAN PLAYER

        if (gamepad2.share) {

            //slides rotate down
            armPosition = new ServoController(0.6);
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            //intake wrist up
            intakeRotation.setPosition(0.4);

            //claw open
            claw.setPosition(0.30);
        }

        //testing out LED Control

        Gamepad.LedEffect bajaEffect = new Gamepad.LedEffect.Builder()
                //rbg converted into integers between 0.0-1.0
                .addStep(0.20392156862, 1, 0.72549019607, 1000)
                .build();

        gamepad1.runLedEffect(bajaEffect);
        //not sure if this means that it will just run for a little bit when first initialized
        //still trying to figure out how to run LED when an action is complete




        // half power on drivetrain
        if(gamepad1.left_bumper){
            leftFront.setPower(0.35 * frontLeftPower);
            rightFront.setPower(0.35 * frontRightPower);
            leftBack.setPower(0.35 * backLeftPower);
            rightBack.setPower(0.35 * backRightPower);
        } else {
            leftFront.setPower(frontLeftPower);
            rightFront.setPower(frontRightPower);
            leftBack.setPower(backLeftPower);
            rightBack.setPower(backRightPower);



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
            telemetry.update();
        }
    }
}