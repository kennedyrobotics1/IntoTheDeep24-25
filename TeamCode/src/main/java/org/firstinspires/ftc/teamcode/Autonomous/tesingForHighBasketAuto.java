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
@Autonomous(name = "tesingForHighBasketAuto", group = "23393 Auto")
public class tesingForHighBasketAuto extends LinearOpMode{

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

        Action MoveUpToBasket = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(53, 60))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                //testing first high basket
                new ParallelAction(
                        MoveUpToBasket,
                        slideRotation.highBasketSample(),
                        extensionMotor.highBasketSample(),
                        claw.close()
                ),
                new SequentialAction(
                        wrist.out(),
                        claw.open()
                ),
                new SequentialAction(
                        wrist.home()
                )
              /*  new ParallelAction(
                        slideRotation.yellowSamplePickUp(),
                        extensionMotor.sampleYellowPickUp(),
                        extensionMotor.ascentLevelOne(),
                        slideRotation.ascentLevelOne()
                ) */
        ));
    }
}
