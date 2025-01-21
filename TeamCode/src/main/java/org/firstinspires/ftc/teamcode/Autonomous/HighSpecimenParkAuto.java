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
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.net.PasswordAuthentication;
import java.util.Arrays;


@Config
@Autonomous(name = "HighSpecimenParkAuto", group = "23393 Auto")
public class HighSpecimenParkAuto extends LinearOpMode {

    private SlidesRotationClass slideRotation;
    private ExtensionClass extensionMotor;
    private IntakeWristClass wrist;
    private ClawClass claw;

    @Override
    public void runOpMode() {

        slideRotation = new SlidesRotationClass(hardwareMap);
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);
        wrist = new IntakeWristClass(hardwareMap, telemetry);
        claw = new ClawClass(hardwareMap);


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 60, Math.toRadians(90)));

        Action PlaceFirst = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action Push2toObservation = drive.actionBuilder(new Pose2d(0, 30, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 37))

                .strafeTo(new Vector2d(-35, 37))

                .strafeTo(new Vector2d(-35, 13))

                .strafeTo(new Vector2d(-42, 13))

                .strafeTo(new Vector2d(-42, 50))

                .strafeTo(new Vector2d(-42, 13))

                .strafeTo(new Vector2d(-53, 13))

                .strafeTo(new Vector2d(-51, 57))
                .build();

        Action UpALittle = drive.actionBuilder(new Pose2d(-51, 57, Math.toRadians(90)))
                .strafeTo(new Vector2d(-51, 55))
                .build();

        Action ScoreSecond = drive.actionBuilder(new Pose2d(-51, 55, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 25))
                .build();

        Action BacktoThird = drive.actionBuilder(new Pose2d(0, 25, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 50))
                .build();

        Action UptoThird = drive.actionBuilder(new Pose2d(-40, 50, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 57))
                .build();

        Action ScoreThird = drive.actionBuilder(new Pose2d(-40, 57, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 25))
                .build();

        Action BacktoFourth = drive.actionBuilder(new Pose2d(0, 25, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 50))
                .build();

        Action UptoFourth = drive.actionBuilder(new Pose2d(-40, 50, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 57))
                .build();

        Action ScoreFourth = drive.actionBuilder(new Pose2d(-40, 57, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 25))
                .build();



        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        PlaceFirst,
                        claw.close(),
                        wrist.home(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new ParallelAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        Push2toObservation,
                        wrist.pickUpSpecimenFromHumanPlayer(),
                        claw.open(),
                        extensionMotor.retraction(),
                        slideRotation.pickUpSpecimenFromHumanPlayer()
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBarSpecimen(),
                        wrist.home()
                ),
                new SequentialAction(
                        UpALittle
                ),
                new ParallelAction(
                        ScoreSecond,
                        extensionMotor.highBarSpecimen()
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        BacktoThird,
                        extensionMotor.retraction(),
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        wrist.pickUpSpecimenFromHumanPlayer(),
                        claw.open()
                ),
                new SequentialAction(
                        UptoThird
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBarSpecimen(),
                        wrist.home()
                ),
                new ParallelAction(
                        ScoreThird,
                        extensionMotor.highBarSpecimen()
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        BacktoFourth,
                        extensionMotor.retraction(),
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        wrist.pickUpSpecimenFromHumanPlayer(),
                        claw.open()
                ),
                new SequentialAction(
                        UptoFourth
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBarSpecimen(),
                        wrist.home()
                ),
                new ParallelAction(
                        ScoreFourth,
                        extensionMotor.highBarSpecimen()
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                )


        ));

    }
}