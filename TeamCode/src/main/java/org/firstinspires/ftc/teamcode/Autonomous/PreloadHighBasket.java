package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "PreloadHighBasket", group = "23393 Auto")
public class PreloadHighBasket extends LinearOpMode {

    private SlidesRotationAuto slideRotation;
    private ExtensionAuto extensionMotor;
    private IntakeWristRotation wrist;
    private Intake claw;


    @Override
    public void runOpMode() {


        slideRotation = new SlidesRotationAuto(hardwareMap);
        extensionMotor = new ExtensionAuto(hardwareMap, telemetry);
        wrist = new IntakeWristRotation(hardwareMap, telemetry);
        claw = new Intake(hardwareMap, telemetry);

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(35, 60, Math.toRadians(270)));

        Action MoveToHighBasket = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(35, 50))
                .strafeToLinearHeading(new Vector2d(55, 55), Math.toRadians(45))
                .build();

        Action InnerSampleToNetZone = drive.actionBuilder(new Pose2d(55, 55, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(30, 38), Math.toRadians(0))
                .strafeTo(new Vector2d(35, 10))
                .strafeTo(new Vector2d(42, 10))
                .strafeToLinearHeading(new Vector2d(55, 55), Math.toRadians(-45))
                .build();

        Action Park = drive.actionBuilder(new Pose2d(55, 55, Math.toRadians(-45)))
                .strafeToLinearHeading(new Vector2d(-58, 60), Math.toRadians(0))
                .build();


        while (!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new ParallelAction(
                MoveToHighBasket,
                slideRotation.highBasket()
        ));
        Actions.runBlocking(extensionMotor.extensionHigh());

        Actions.runBlocking(wrist.out());

        Actions.runBlocking(claw.open());

        Actions.runBlocking(wrist.home());

//        Actions.runBlocking(new ParallelAction(
//                claw.open(),
//                wrist.home()
//        ));


//        Actions.runBlocking(
//                new SequentialAction(
//                    new ParallelAction(
//                            MoveToHighBasket,
//                            slideRotation.highBasket()
//                        ),
//                    new ParallelAction(
//                            extensionMotor.extensionHigh(),
//                            wrist.out()
//                    ),
//                    new ParallelAction(
//                            claw.open(),
//                            wrist.home()
//                    ),
//                    new ParallelAction(
//                            extensionMotor.extensionLow(),
//                            claw.close()
//                    ),
//                    new ParallelAction(
//                            InnerSampleToNetZone,
//                            slideRotation.home()
//                            ),
//        new Action() {
//
//            @Override
//            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//                telemetry.addLine("Action!");
//                telemetry.update();
//                return false;
//            }
////        },
//                // Only that this action uses a Lambda expression to reduce complexity
//                (telemetryPacket) -> {
//                    telemetry.addLine("Action!");
//                    telemetry.update();
//                    return false; // Returning true causes the action to run again, returning false causes it to cease
//                }

    }

}