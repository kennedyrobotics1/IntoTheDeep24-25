package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.Arrays;


@Config
@Autonomous(name = "TrajectoryTest", group = "23393 Auto")
public class TrajectoryTest extends LinearOpMode {

    @Override
    public void runOpMode() {


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 60, Math.toRadians(90)));

        Action PlaceFirst = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 30))

                .strafeTo(new Vector2d(0, 31))

                .splineToConstantHeading(new Vector2d(-35, 33), Math.toRadians(225))

                .splineToConstantHeading(new Vector2d(-43, 12), Math.toRadians(180))

                .strafeTo(new Vector2d(-43, 50))

                .splineToConstantHeading(new Vector2d(-47, 50), Math.toRadians(270))

                .strafeTo(new Vector2d(-47, 12))

                .splineToConstantHeading(new Vector2d(-54, 12), Math.toRadians(90))

                .strafeTo(new Vector2d(-53, 55))

                        .build();



        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                PlaceFirst
        ));

    }
}