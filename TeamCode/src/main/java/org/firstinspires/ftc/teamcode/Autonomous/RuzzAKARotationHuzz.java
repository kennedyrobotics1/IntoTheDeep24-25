package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;


@Config
@Autonomous(name = "RuzzAKARotationHuzz", group = "Autonomous")
public class RuzzAKARotationHuzz extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        IntakeWristRotation wrist = new IntakeWristRotation(hardwareMap, telemetry);

        waitForStart();


        Actions.runBlocking(wrist.highBasket());
    }

}