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
    private CRServo claw;
    private CRServo claw2;

    private Servo armLeftFront;
    private Servo armRightFront;
    private ServoController armPosition;
    boolean RotatingThing = false;

    double frontLeftPower, frontRightPower, backLeftPower, backRightPower;
    double clawRotationPosition;

    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        slideExtensionMotor = hardwareMap.get(DcMotorEx.class, "slideExtensionMotor");
        slideExtensionMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeRotation = hardwareMap.get(Servo.class, "servo5");
        //clawRotation = hardwareMap.get(Servo.class, "servo4");
        claw = hardwareMap.get(CRServo.class, "servo4");
        claw2 = hardwareMap.get(CRServo.class, "servo3");
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

        // rotating entire intake
        // forward rotation
        if (gamepad1.dpad_left) {
            intakeRotationPosition.update(0.008);
            intakeRotation.setPosition(intakeRotationPosition.position);
        // backward rotation
        } else if (gamepad1.dpad_right) {
            intakeRotationPosition.update(-0.008);
            intakeRotation.setPosition(intakeRotationPosition.position);
        }

//        // rotating claw
//        if (gamepad2.x) {
//            clawRotationPosition -= 0.008;
//            clawRotation.setPosition(clawRotationPosition);
//        } else if (gamepad2.b) {
//            clawRotationPosition += 0.008;
//            clawRotation.setPosition(clawRotationPosition);
//        }

        // open claw
        if (gamepad1.a) {
            claw.setPower(1);
            claw2.setPower(-1);
        // close claw
        } else if (gamepad1.b) {
            claw.setPower(-1);
            claw2.setPower(1);
        }
        else{
            claw.setPower(0);
            claw2.setPower(0);
        }



        // forward arm rotation (toward floor)
        if (gamepad1.right_trigger != 0) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(0.005);
        // backward arm rotation
        } else if (gamepad1.left_trigger != 0) {
            armLeftFront.setPosition(armPosition.position);
            armRightFront.setPosition(1 - armPosition.position);
            armPosition.update(-0.005);
        }

        // slides go up (must hold button to hold slide position)
        if (gamepad1.right_bumper) {
            slideExtensionMotor.setPower(1.0);
        // slides go down (must hold button to hold slide position)
        } else if (gamepad1.left_bumper) {
            slideExtensionMotor.setPower(-1.0);
        } else {
            slideExtensionMotor.setPower(0);
        }

        // half power on drivetrain
        if(gamepad2.a){
            leftFront.setPower(0.35 * frontLeftPower);
            rightFront.setPower(0.35 * frontRightPower);
            leftBack.setPower(0.35 * backLeftPower);
            rightBack.setPower(0.35 * backRightPower);
        } else {
            leftFront.setPower(frontLeftPower);
            rightFront.setPower(frontRightPower);
            leftBack.setPower(backLeftPower);
            rightBack.setPower(backRightPower);

            telemetry.addData("frontLeftPower ", frontLeftPower);
            telemetry.addData("frontRightPower ", frontRightPower);
            telemetry.addData("backLeftPower ", backLeftPower);
            telemetry.addData("backRightPower ", backRightPower);
            telemetry.addData("slideExtensionPower", slideExtensionMotor.getPower());
            telemetry.addData("armLeftFront: ", armLeftFront.getPosition());
            telemetry.addData("armRightFront: ", armRightFront.getPosition());
            telemetry.addData("intake rotation position: ", intakeRotation.getPosition());
            telemetry.addData("armPosition", armPosition.position);
            telemetry.addData("intakeArmRotationPosition" , intakeRotationPosition.position);
            telemetry.update();
        }
    }
}