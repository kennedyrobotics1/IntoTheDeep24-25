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
@Autonomous(name = "Push3IntoObservation", group = "23393 Auto")
public class Push3IntoObservation extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-12, 60, Math.toRadians(270)));

        Action TrajectoryForwardToSample = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(-35, 60))

                .strafeTo(new Vector2d(-35, 12))

                .strafeTo(new Vector2d(-42, 12))

                .strafeTo(new Vector2d(-42, 55))

                .strafeTo(new Vector2d(-42, 12))

                .strafeTo(new Vector2d(-54, 12))

                .strafeTo(new Vector2d(-54, 53))

                .strafeTo(new Vector2d(-54, 12))

                .strafeTo(new Vector2d(-62, 12))

                .strafeTo(new Vector2d(-62, 55))

                .build();



     /*
        Action TrajectoryParkingObservation = drive.actionBuilder(drive.pose)
                .lineToX(-50)
                .build();
*/

     /*
        Action TrajectoryAction1 = drive.actionBuilder(drive.pose)
                .lineToX(10)
                .build();

        Action TrajectoryAction2 = drive.actionBuilder(new Pose2d(15, 20, 0))
               .splineTo(new Vector2d(5, 5), Math.toRadians(90))
                .build();
        */


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