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

        Action FirstSpecimenPlace = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action UpToThirdSpecimen = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-30, 40), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-40, 12), Math.toRadians(215))
                .strafeTo(new Vector2d(-40, 55))
                .splineToConstantHeading(new Vector2d(-50, 15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-50, 58), Math.toRadians(90))
                .build();
//
        Action UpALittle = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(-50, 50))
                .build();
//
        Action ScoreSecond = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(0, 32), Math.toRadians(270))
                .build();

        Action BacktoThird = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 57))
                .build();
//
        Action ScoreThird = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(0, 32), Math.toRadians(270))
                .build();

        Action BacktoFourth = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, 57))
                .build();
//
        Action ScoreFourth = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(0, 32), Math.toRadians(270))
                .build();


        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        FirstSpecimenPlace,
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
                        UpToThirdSpecimen,
                        extensionMotor.retraction(),
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        wrist.pickUpSpecimenFromHumanPlayer(),
                        claw.open()
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