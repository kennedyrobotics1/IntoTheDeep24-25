package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "HighSpecimenParkAuto", group = "23393 Auto")
public class HighSpecimenParkAuto extends LinearOpMode {
    private SlidesRotationClass slideRotation;
    private ExtensionClass extensionMotor;
    private IntakeWristClass wrist;
    private ClawClass claw;
    private TwistClass twist;

    @Override
    public void runOpMode() {

        slideRotation = new SlidesRotationClass(hardwareMap);
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);
        wrist = new IntakeWristClass(hardwareMap, telemetry);
        claw = new ClawClass(hardwareMap);
        twist = new TwistClass(hardwareMap);


        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 60, Math.toRadians(90)));

        Action PlaceFirst = drive.actionBuilder(new Pose2d(0, 60, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 20))
                .build();

        Action Push2 = drive.actionBuilder(new Pose2d(0, 20, Math.toRadians(90)))

                .strafeTo(new Vector2d(0, 31))

                .splineToConstantHeading(new Vector2d(-35, 33), Math.toRadians(225))

                .splineToConstantHeading(new Vector2d(-43, 12), Math.toRadians(180))

                .strafeTo(new Vector2d(-43, 55))

                .splineToConstantHeading(new Vector2d(-47, 50), Math.toRadians(270))

                .strafeTo(new Vector2d(-47, 15))

                .splineToConstantHeading(new Vector2d(-56, 15), Math.toRadians(90))

                .strafeTo(new Vector2d(-53, 55), new TranslationalVelConstraint(30))

                .build();

        Action PlaceSecond = drive.actionBuilder(new Pose2d(-53, 55, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-50, 50), Math.toRadians(0))

                .strafeTo(new Vector2d(-5, 40))

                .splineToConstantHeading(new Vector2d(3, 20), Math.toRadians(270), new TranslationalVelConstraint(30))
                .build();

        Action BacktoThird = drive.actionBuilder(new Pose2d(3, 20, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 31))

                .splineToConstantHeading(new Vector2d(-45, 55), Math.toRadians(90), new TranslationalVelConstraint(30))
                .build();

        Action PlaceThird = drive.actionBuilder(new Pose2d(-45, 55, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-40, 55), Math.toRadians(0))

                .strafeTo(new Vector2d(-5, 40))

                .splineToConstantHeading(new Vector2d(6, 20), Math.toRadians(270), new TranslationalVelConstraint(30))
                .build();

        Action BacktoFourth = drive.actionBuilder(new Pose2d(6, 20, Math.toRadians(90)))
                .strafeTo(new Vector2d(0, 31))

                .splineToConstantHeading(new Vector2d(-45, 55), Math.toRadians(90), new TranslationalVelConstraint(30))
                .build();

        Action PlaceFourth = drive.actionBuilder(new Pose2d(-45, 55, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-40, 50), Math.toRadians(0))

                .strafeTo(new Vector2d(-5, 40))

                .splineToConstantHeading(new Vector2d(7, 20), Math.toRadians(270), new TranslationalVelConstraint(30))
                .build();



        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        PlaceFirst,
                        claw.close(),
                        wrist.home(),
                        twist.horizontal(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new ParallelAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        Push2,
                        wrist.specimenFromHumanPlayer(),
                        claw.open(),
                        twist.horizontal(),
                        slideRotation.specimenFromHumanPlayer(),
                        new SequentialAction(
                                extensionMotor.retract(),
                                extensionMotor.specimenHumanPlayer()
                        )
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        PlaceSecond,
                        wrist.home(),
                        twist.horizontal(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        BacktoThird,
                        twist.horizontal(),
                        slideRotation.specimenFromHumanPlayer(),
                        wrist.specimenFromHumanPlayer(),
                        claw.open(),
                        new SequentialAction(
                                extensionMotor.retract(),
                                extensionMotor.specimenHumanPlayer()
                        )
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        PlaceThird,
                        twist.horizontal(),
                        wrist.home(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        BacktoFourth,
                        twist.horizontal(),
                        slideRotation.specimenFromHumanPlayer(),
                        wrist.specimenFromHumanPlayer(),
                        claw.open(),
                        new SequentialAction(
                                extensionMotor.retract(),
                                extensionMotor.specimenHumanPlayer()
                        )
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        PlaceFourth,
                        wrist.home(),
                        twist.horizontal(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new SequentialAction(
                        extensionMotor.specimenHighBarOuttake()
                ),
                new ParallelAction(
                        claw.open(),
                        extensionMotor.retract()
                )


        ));

    }
}