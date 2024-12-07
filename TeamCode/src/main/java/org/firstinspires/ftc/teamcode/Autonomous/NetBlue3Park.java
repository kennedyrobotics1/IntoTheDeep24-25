package org.firstinspires.ftc.teamcode.Autonomous;

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
@Autonomous(name = "NetBlue3Park", group = "23393 Auto")
public class NetBlue3Park extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(36, 60, Math.toRadians(270)));

        Action TrajectoryForwardToSample = drive.actionBuilder(drive.pose)
                //Up to Outside Sample
                .strafeTo(new Vector2d(36, 8))

                //side to Outside Sample
                .strafeTo(new Vector2d(47, 8))

                //Down to Score Outside Sample
                .strafeTo(new Vector2d(47, 60))

                //up to Middle Sample
                .strafeTo(new Vector2d(47,8))

                //Side to Middle Sample
                .strafeTo(new Vector2d(57,8))

                //Down to Score Middle Sample
                .strafeTo(new Vector2d(57, 57))

                //Up to Last Sample
                .strafeTo(new Vector2d(57, 8))

                //Side to Last Sample
                .strafeTo(new Vector2d(62, 8))

                //Down To Last Sample
                .strafeTo(new Vector2d(62, 55))

                //Side to Park
                .strafeTo(new Vector2d(-35, 65))

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