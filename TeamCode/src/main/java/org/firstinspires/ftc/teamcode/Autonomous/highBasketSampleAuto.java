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
@Autonomous(name = "HighBasketSampleAuto", group = "23393 Auto")
public class highBasketSampleAuto extends LinearOpMode {

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

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(38, 60, Math.toRadians(0)));

       Action MoveUpToBasketForFirst = drive.actionBuilder(new Pose2d(38, 60, Math.toRadians(0)))
               .strafeTo(new Vector2d(59, 60))
               .build();

       Action MoveAwayFromBasketAfterFirst = drive.actionBuilder(new Pose2d(58, 60, Math.toRadians(0)))
               .strafeTo(new Vector2d(35, 60))
               .build();

        Action MoveToSecondSample = drive.actionBuilder(new Pose2d(35, 60, Math.toRadians(0)))
                .strafeTo(new Vector2d(37, 26))
               .build();

        Action MoveUpToBasketForSecond = drive.actionBuilder(new Pose2d(35, 25, Math.toRadians(0)))
                .strafeTo(new Vector2d(59, 60))
                .build();

        Action MoveToThirdSample = drive.actionBuilder(new Pose2d(58, 60, Math.toRadians(0)))
                .strafeTo(new Vector2d(47, 60))
                .strafeTo(new Vector2d(47, 26))
                .build();

        Action MoveUpToBasketForThird = drive.actionBuilder(new Pose2d(45, 25, Math.toRadians(0)))
                .strafeTo(new Vector2d(58, 60))
                .build();

        Action MoveToFourthSample = drive.actionBuilder(new Pose2d(58, 60, Math.toRadians(0)))
                .strafeTo(new Vector2d(58,26))
                .build();

        Action MoveToBasketForFourth = drive.actionBuilder(new Pose2d(55, 25, Math.toRadians(0)))
                .strafeTo(new Vector2d(58, 60))
                .build();

        while (!isStopRequested() && !opModeIsActive()) {

        }

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        MoveUpToBasketForFirst,
                        slideRotation.highBasketSample(),
                        extensionMotor.highBasketSample(),
                        claw.close()
                        //move to basket backwards, move slides to angle and raise slides at the same time
                ),
                new SequentialAction(
                        wrist.out(),
                        claw.open(),
                        wrist.home()
                        //rotate wrist and open claw once we get to basket
                ),
                new ParallelAction(
                        MoveAwayFromBasketAfterFirst,
                        extensionMotor.retract()
                        //rotate wrist back and retract slides at same time
                ),
                new ParallelAction(
                        MoveToSecondSample,
                        wrist.pickUpSample(),
                        twist.vertical(),
                        slideRotation.halfSampleRotation()
                ),
                new SequentialAction(
                        slideRotation.yellowSamplePickUp(),
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBasketSample(),
                        MoveUpToBasketForSecond,
                        twist.horizontal()
                ),
                new SequentialAction(
                    extensionMotor.highBasketSample(),
                    claw.open(),
                        wrist.home(),
                        extensionMotor.retract()
                ),
                new ParallelAction(
                        MoveToThirdSample,
                        wrist.pickUpSample(),
                        twist.vertical(),
                        slideRotation.halfSampleRotation()
                ),
                new SequentialAction(
                        slideRotation.yellowSamplePickUp(),
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBasketSample(),
                        MoveUpToBasketForThird,
                        twist.horizontal()
                ),
                new SequentialAction(
                        extensionMotor.highBasketSample(),
                        claw.open(),
                        wrist.home(),
                        extensionMotor.retract()
                ),
                new ParallelAction(
                        MoveToFourthSample,
                        twist.vertical(),
                        wrist.pickUpSample(),
                        slideRotation.halfSampleRotation()
                ),
                new SequentialAction(
                        slideRotation.yellowSamplePickUp(),
                        claw.close()
                ),
                new ParallelAction(
                        slideRotation.highBasketSample(),
                        MoveToBasketForFourth
                ),
                new SequentialAction(
                        extensionMotor.highBasketSample(),
                        claw.open(),
                        wrist.home()
                )
        ));
    }

}
