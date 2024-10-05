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
@Autonomous(name = "Template Autoop", group = "16481-Example")
public class NetBlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(36, 60, Math.toRadians(270)));

        //^ double check the starting position, robot is using this as (0, 60) **Why is x = ?

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
                .strafeTo(new Vector2d(65, 8))

                //Down To Last Sample
                .strafeTo(new Vector2d(65, 55))

                //Side to Park
                .strafeTo(new Vector2d(-50, 60))

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