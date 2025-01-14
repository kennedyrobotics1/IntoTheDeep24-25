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


    @Override
    public void runOpMode() {

        slideRotation = new SlidesRotationClass(hardwareMap);
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);
        wrist = new IntakeWristClass(hardwareMap, telemetry);
        claw = new ClawClass(hardwareMap);

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(38, 60, Math.toRadians(0)));

       Action MoveUpToBasketForFirst = drive.actionBuilder(new Pose2d(38, 60, Math.toRadians(0)))
               .strafeTo(new Vector2d(58, 60))
               .build();

       Action MoveToNetPushPosition = drive.actionBuilder(new Pose2d(58, 60, Math.toRadians(0)))
               .strafeTo(new Vector2d(41, 60))
               .build();

       Action MoveToSecondSample = drive.actionBuilder(new Pose2d(41, 60, Math.toRadians(0)))
               .strafeToLinearHeading(new Vector2d(45,50), Math.toRadians(180))
               .build();

      // Action MoveToThirdSample =

        Action MoveForwardToSample = drive.actionBuilder(new Pose2d(41, 60, Math.toRadians(0)))
                //Up to Outside Sample
                .strafeTo(new Vector2d(41, 8))
                //side to Outside Sample
                .strafeTo(new Vector2d(47, 8))
                //Down to Score Outside Sample
                .strafeTo(new Vector2d(53, 60))
                //up to Middle Sample
                .strafeTo(new Vector2d(49,8)) // 47
                //Side to Middle Sample
                .strafeTo(new Vector2d(59,8))
                //Down to Score Middle Sample
                .strafeTo(new Vector2d(59, 57))
                //Up to Last Sample
                .strafeTo(new Vector2d(59, 8)) // 57
                //Side to Last Sample
                .strafeTo(new Vector2d(70, 8))
                //Down To Last Sample
                .strafeTo(new Vector2d(70, 55))
                .build();

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
                        claw.open()
                        //rotate wrist and open claw once we get to basket
                ),
                new ParallelAction(
                        wrist.home(),
                        extensionMotor.retractSlides()
                        //rotate wrist back and retract slides at same time
                ),
                new ParallelAction(
                        MoveToSecondSample,
                        slideRotation.yellowSamplePickUp()
                ),
                new SequentialAction(
                        extensionMotor.sampleYellowPickUp(),
                        wrist.out(),
                        claw.close(),
                        wrist.home()
                ),
                new ParallelAction(
                        extensionMotor.retractSlides(),
                        slideRotation.highBasketSample()
                ),
                new ParallelAction(

                )

        ));
    }

}
