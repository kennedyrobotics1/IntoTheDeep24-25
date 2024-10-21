package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "ArmRotationTesting", group = "Autonomous")
public class ArmRotationTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //ArmRotation armLeft = new ArmRotation(hardwareMap, telemetry);
        //ArmRotation armRight = new ArmRotation(hardwareMap, telemetry);
        ArmRotation armTest = new ArmRotation(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        //.setLowest(),
                        //armRight.setLowest(),
                        armTest.setLowest()
                )
        );
    }
}