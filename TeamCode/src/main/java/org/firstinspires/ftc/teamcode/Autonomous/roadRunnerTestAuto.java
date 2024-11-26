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
@Autonomous(name = "roadRunnerTestAuto", group = "16481-Example")
public class roadRunnerTestAuto extends LinearOpMode {

    @Override
    public void runOpMode() {

        AutonomousTracker auto = new AutonomousTracker(0, 0, 0);
        auto.invertY = true;
        auto.invertX = true;
        auto.invertR = true;

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(270)));

        double MOVE_FORWARD = 20;
        double MOVE_FORWARD_TO_GET_IN_POSITION = -7;


        Action TrajectoryForwardToSample = drive.actionBuilder(drive.pose)

                .strafeTo(auto.update2d(0, MOVE_FORWARD))
                .strafeToLinearHeading(auto.update2d(-40,-10), auto.updateRotation(-135))
                .strafeTo(auto.update2d(0, MOVE_FORWARD_TO_GET_IN_POSITION))
                .build();

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