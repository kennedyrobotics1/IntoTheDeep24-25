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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "PIDInAutoTest", group = "23393 Auto")
public class PIDInAutoTest extends LinearOpMode {

    private ExtensionClass extensionMotor;
    private IntakeWristClass wrist;
    private ClawClass claw;
    long startTime;
    double time;
    double previousTime;
    double deltaTime;
    private PIDSlidesRotation slidesRotation;

    @Override
    public void runOpMode() {

        slidesRotation = new PIDSlidesRotation(hardwareMap);
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);
        wrist = new IntakeWristClass(hardwareMap, telemetry);
        claw = new ClawClass(hardwareMap);

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-12, 60, Math.toRadians(90)));

        Action MoveToHighBar = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(0, 31), Math.toRadians(90))
                .build();

        Action Push3IntoObservation = drive.actionBuilder(drive.pose)
                // 40 may be catch on sub, can try less if not to reduce time
                .strafeTo(new Vector2d(-35, 40))
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

        Action MoveToSpecimenPickup = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-42, 56), Math.toRadians(270))
                .build();

        Action Park = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(-35, 60))
                .build();

        while (!isStopRequested() && !opModeIsActive()) {
        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                // high bar with preloaded specimen (specimen 1)
                new ParallelAction(
                        claw.close(),
                        MoveToHighBar,
                        PIDSlidesRotation.highBarSpecimen(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                extensionMotor.highBarSpecimen()
                        )
                ),
                // push samples into observation zone
                // may want pushing samples to after we pick up the second specimen
                    // if that's more time efficient/more likely to score us more points
                // don't know if we should consider possibility of other teams moving the specimen though,
                    // which might make it more important to get the samples into the zone first?
                new ParallelAction(
                        claw.open(),
                        extensionMotor.retractSlides(),
                        Push3IntoObservation
                ),
                // pick up 1st specimen (specimen 2) from human player
                // not too sure if things were put in parallel & sequential actions correctly
                // took values for subsystems from teleop macros, may need to edit in their classes
                new ParallelAction(
                        MoveToSpecimenPickup,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                claw.close(),
                                wrist.pickUpSpecimenFromHumanPlayer()
                        )
                ),
                // high bar using 1st picked up specimen (specimen 2)
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
                // pick up 2nd specimen (specimen 3) from human player
                new ParallelAction(
                        MoveToSpecimenPickup,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                claw.close(),
                                wrist.pickUpSpecimenFromHumanPlayer()
                        )
                ),
                // high bar using 2nd picked up specimen (specimen 3)
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
                // pick up 3rd specimen (specimen 4) from human player
                new ParallelAction(
                        MoveToSpecimenPickup,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                claw.close(),
                                wrist.pickUpSpecimenFromHumanPlayer()
                        )
                ),
                // high bar using 3rd picked up specimen (specimen 4)
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
                // pick up 4th specimen (specimen 5) from human player
                new ParallelAction(
                        MoveToSpecimenPickup,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        new SequentialAction(
                                new SleepAction(0.6),
                                claw.close(),
                                wrist.pickUpSpecimenFromHumanPlayer()
                        )
                ),
                // high bar using 4th picked up specimen (specimen 5)
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
                )
                ));
    }
}