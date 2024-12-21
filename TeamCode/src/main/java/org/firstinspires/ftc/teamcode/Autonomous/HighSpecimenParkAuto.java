package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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


    @Override
    public void runOpMode() {

        slideRotation = new SlidesRotationClass(hardwareMap);
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);
        wrist = new IntakeWristClass(hardwareMap, telemetry);
        claw = new ClawClass(hardwareMap);

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-12, 60, Math.toRadians(90)));

        Action MoveToHighBar = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(0, 31))

                .build();

        Action MoveToSpecimenPickup = drive.actionBuilder(drive.pose)

                .strafeToLinearHeading(new Vector2d(-42, 56), Math.toRadians(270))

                .build();

        Action Push3IntoObservation = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(-35, 60))

                .strafeToLinearHeading(new Vector2d(-35,12), Math.toRadians(90))

                .strafeTo(new Vector2d(-42, 12))

                .strafeTo(new Vector2d(-42, 55))

                .strafeTo(new Vector2d(-42, 12))

                .strafeTo(new Vector2d(-54, 12))

                .strafeTo(new Vector2d(-54, 53))

                .strafeTo(new Vector2d(-54, 12))

                .strafeTo(new Vector2d(-62, 12))

                .strafeTo(new Vector2d(-62, 55))

                .build();

        Action Park = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(-35, 60))

                .build();

        while (!isStopRequested() && !opModeIsActive()) {
        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        claw.close(),
                        MoveToHighBar,
                        slideRotation.highBarSpecimen(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new ParallelAction(
                        claw.open(),
                        extensionMotor.retractSlides()
                ),
                new ParallelAction(
                        MoveToSpecimenPickup,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                claw.close(),
                                wrist.pickUpSpecimenFromHumanPlayer()
                        )
                ),
                new ParallelAction(
                        MoveToHighBar,
                        slideRotation.highBarSpecimen(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                new ParallelAction(
                        claw.open(),
                        extensionMotor.retractSlides()
                ),
                new ParallelAction(
                        Push3IntoObservation
                )
                ));
    }
}