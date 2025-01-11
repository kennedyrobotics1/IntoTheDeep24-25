package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

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

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(38, 60, Math.toRadians(180)));

       Action MoveUpToBasket = drive.actionBuilder(drive.pose)
               .strafeTo(new Vector2d(45, 60))
               .build();

       Action TurnToBasket = drive.actionBuilder(drive.pose)
               .turn(Math.toRadians(90))
                .build();

       Action TurnToFirstSample = drive.actionBuilder(drive.pose)
                       .turn(Math.toRadians(270))
                               .build();

        Action TurnToSecondSample = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(295))
                .build();

        Action TurnToThirdSample = drive.actionBuilder(drive.pose)
                .turn(Math.toRadians(310))
                .build();

        Action MoveToLevelOneAscent = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(20,0), Math.toRadians(180))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        MoveUpToBasket,
                        slideRotation.highBasketSample(),
                        extensionMotor.highBasketSample()
                        //move to basket, move slides to angle and raise slides at the same time
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
                        TurnToFirstSample,
                        slideRotation.yellowSamplePickUp()
                        //turn and move towards angle of first pickup at the same time
                ),
                new SequentialAction(
                        extensionMotor.sampleYellowPickUp(),
                        wrist.out(),
                        claw.close()
                        //grab first sample
                ),
                new ParallelAction(
                        extensionMotor.retractSlides(),
                        TurnToBasket,
                        slideRotation.highBasketSample()
                       // retract, turn, and rotate angle to basket at same time
                ),
                new SequentialAction(
                        extensionMotor.highBasketSample(),
                        wrist.out(),
                        claw.open()
                        //drop first yellow sample
                ),
                new ParallelAction(
                        wrist.home(),
                        extensionMotor.retractSlides()
                        //rotate wrist back and retract slides at same time
                ),
                new ParallelAction(
                        TurnToSecondSample,
                        slideRotation.yellowSamplePickUp()
                        //turn and move towards angle of second pickup at the same time
                ),
                new SequentialAction(
                        extensionMotor.sampleYellowPickUp(),
                        wrist.out(),
                        claw.close()
                        //grab second sample
                ),
                new ParallelAction(
                        extensionMotor.retractSlides(),
                        TurnToBasket,
                        slideRotation.highBasketSample()
                        // retract, turn, and rotate angle to basket at same time
                ),
                new SequentialAction(
                        extensionMotor.highBasketSample(),
                        wrist.out(),
                        claw.open()
                        //drop second yellow sample
                ),
                new ParallelAction(
                        wrist.home(),
                        extensionMotor.retractSlides()
                        //rotate wrist back and retract slides at same time
                ),
                new ParallelAction(
                        TurnToThirdSample,
                        slideRotation.yellowSamplePickUp()
                        //turn and move towards angle of second pickup at the same time
                ),
                new SequentialAction(
                        extensionMotor.sampleYellowPickUp(),
                        wrist.out(),
                        claw.close()
                        //grab third sample
                ),
                new ParallelAction(
                        extensionMotor.retractSlides(),
                        TurnToBasket,
                        slideRotation.highBasketSample()
                        // retract, turn, and rotate angle to basket at same time
                ),
                new SequentialAction(
                        extensionMotor.highBasketSample(),
                        wrist.out(),
                        claw.open()
                        //drop third yellow sample
                ),
                new ParallelAction(
                        wrist.home(),
                        extensionMotor.retractSlides()
                        //rotate wrist back and retract slides at same time
                ),
                new ParallelAction(
                        MoveToLevelOneAscent,
                        extensionMotor.ascentLevelOne()
                        //move to level one ascent and extend towards level 1 ascent
                ),
                new SequentialAction(
                        slideRotation.ascentLevelOne()
                        //rotate towards angle when it gets to level 1 ascent
                )
                //done yayyy mega happy ending
        ));
    }

}
