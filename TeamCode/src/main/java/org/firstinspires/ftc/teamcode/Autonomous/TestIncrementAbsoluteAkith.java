package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "TestIncrementAbsoluteAkith", group = "23393d Auto")
public class TestIncrementAbsoluteAkith extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {



        AutonomousTracker auto = new AutonomousTracker(0, 0);
        auto.invertY = true;
        auto.invertX = true;

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(270)));

        Action Sample1Retrieve = drive.actionBuilder(drive.pose)

                //Up to Outside Sample
                .strafeTo(auto.update2d(0,30))

                //side to Outside Sample
                .strafeToLinearHeading(auto.update2d(-60, 0), Math.toRadians(0))

                //Down to Score Outside Sample
                .strafeToLinearHeading(auto.update2d(0,30), Math.toRadians(45))

                .strafeTo(auto.update2d(0, -30))

                .build();





        while (!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        Sample1Retrieve,

                        new Action() {
                            // This action and the following action do the same thing

                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        },
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }
                )

        );

    }
}