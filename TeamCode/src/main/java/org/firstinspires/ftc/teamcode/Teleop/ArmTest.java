package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;

@TeleOp(name = "ArmTest", group = "Iterative OpMode")

public class ArmTest extends BasicOpMode_Iterative {
    private ElapsedTime runtime = new ElapsedTime();

    private Servo armTemp;

    double armPosition;

    public void init() {
        armTemp = hardwareMap.get(Servo.class, "servo4");
        armTemp.setDirection(Servo.Direction.REVERSE);
    }

    public void start(){
        armPosition = 0;
    }

    public void loop(){

        if (gamepad2.dpad_right) {
            armPosition += 0.001;
            /* if (armRightFront.getPosition() > 1.0) {
                armPosition = 1.0;
            } */
            armTemp.setPosition(armPosition);
        } else if (gamepad2.dpad_left) {
            armPosition -= 0.001;
            /* if (armRightFront.getPosition() < 0.0) {
                armPosition = 0.0;
            } */
            armTemp.setPosition(armPosition);
        }

        telemetry.addData("arm Position (degrees): ", armTemp.getPosition());
        telemetry.update();
        }
    }