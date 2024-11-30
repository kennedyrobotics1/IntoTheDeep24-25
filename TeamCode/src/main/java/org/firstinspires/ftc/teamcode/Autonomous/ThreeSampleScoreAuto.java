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
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.Vector;


@Config
@Autonomous(name = "ThreeSampleScoreAuto", group = "23393 Auto")
public class ThreeSampleScoreAuto extends LinearOpMode {


    private SlidesRotationAuto armLeft;
    private SlidesRotationAuto armRight;
    private IntakeWristRotation wrist;
    private ExtensionAuto extensionMotor;


    @Override
    public void runOpMode() {

         armRight = new SlidesRotationAuto(hardwareMap);
         armLeft = new SlidesRotationAuto(hardwareMap);
         wrist = new IntakeWristRotation(hardwareMap, telemetry);
        extensionMotor = new ExtensionAuto(hardwareMap, telemetry);

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(35, 60, Math.toRadians(270)));

        Action MoveToScore = drive.actionBuilder(drive.pose)

                .strafeTo(new Vector2d(35, 50))
                .strafeToLinearHeading(new Vector2d(50, 50), Math.toRadians(45))
                .strafeTo(new Vector2d(55, 55))

                .build();

        while (!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                    new ParallelAction(
                            MoveToScore,
                            armLeft.highBasket(),
                            armRight.highBasket()
                        ),
                        new SequentialAction(
                                extensionMotor.extensionHigh(),
                                wrist.out(),
                                //clawhere
                                wrist.home(),
                                extensionMotor.extensionLow()

                        ),
                        new Action() {

                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        },
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        }
                )
        );
    }
}