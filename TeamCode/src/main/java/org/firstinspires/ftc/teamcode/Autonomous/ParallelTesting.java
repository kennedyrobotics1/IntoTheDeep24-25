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
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.net.PasswordAuthentication;
import java.util.Arrays;


@Config
@Autonomous(name = "ParallelTesting", group = "23393 Auto")
public class ParallelTesting extends LinearOpMode {

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
        Action PlaceSecond = drive.actionBuilder(new Pose2d(-53, 58, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-50, 50), Math.toRadians(0))

                .strafeTo(new Vector2d(-5, 40))

                .splineToConstantHeading(new Vector2d(3, 28), Math.toRadians(270), new TranslationalVelConstraint(30))
                .build();

        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        wrist.home(),
                        new SequentialAction(
                                slideRotation.highBarSpecimen(),
                                extensionMotor.highBarSpecimen()
                        )
                )
        ));

    }
}