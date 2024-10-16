package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "SlidesTesting", group = "Autonomous")
public class IntakeTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Intake intake = new Intake(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(intake.intakeIn());
    }

}