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

        Action MoveToHighBar = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action PushSpecimenIntoObservation = drive.actionBuilder(new Pose2d(0, 30, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 35))
                .strafeTo(new Vector2d(-35, 35))
                .strafeTo(new Vector2d(-35, 15))
                .strafeTo(new Vector2d(-47, 15))
                .strafeTo(new Vector2d(-47, 57))
                .strafeTo(new Vector2d(-47, 15))
                .strafeTo(new Vector2d(-53, 15))
                .strafeTo(new Vector2d(-53, 57))
                .build();


        Action PlaceSecondSpecimen = drive.actionBuilder(new Pose2d(-47, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(-53, 50))
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action PickupThirdSpecimen = drive.actionBuilder(new Pose2d(0, 30, Math.toRadians(90)))
                .strafeTo(new Vector2d(-47, 50))
                .strafeTo(new Vector2d(-47, 57))
                .build();

        Action PlaceThirdSpecimen = drive.actionBuilder(new Pose2d(-47, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action PickupFourthSpecimen = drive.actionBuilder(new Pose2d(0, 30, Math.toRadians(90)))
                .strafeTo(new Vector2d(-47, 50))
                .strafeTo(new Vector2d(-47, 57))
                .build();

        Action PlaceFourthSpecimen = drive.actionBuilder(new Pose2d(-47, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 30))
                .build();

        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                MoveToHighBar,
                PushSpecimenIntoObservation,
                PlaceSecondSpecimen,
                PickupThirdSpecimen,
                PlaceThirdSpecimen,
                PickupFourthSpecimen,
                PlaceFourthSpecimen
        ));

    }
}