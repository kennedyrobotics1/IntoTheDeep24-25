package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTracker;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "CalibrateStraightTest", group = "23393 Auto")
public class CalibrateStraightTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        AutonomousTracker auto = new AutonomousTracker(0, 0);


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(270)));

        Action TrajectoryForwardToSample = drive.actionBuilder(drive.pose)

                //Up to Outside Sample
                .strafeTo(auto.update2d(0, -20))

                .strafeTo(auto.update2d(-10, 0))

                .strafeTo(auto.update2d(0, 20))

                .strafeTo(auto.update2d(10, 0))

                .build();

//                .splineToConstantHeading(new Vector2d(-45, 13), Math.toRadians(270))
//
//                .strafeTo(new Vector2d(-46, 13))
//
//                .splineToConstantHeading(new Vector2d(60, 43), Math.toRadians(270))
//
//                .build();

        while (!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        TrajectoryForwardToSample,
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