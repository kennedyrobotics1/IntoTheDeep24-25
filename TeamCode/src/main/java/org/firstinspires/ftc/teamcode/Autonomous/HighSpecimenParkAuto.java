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

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 60, Math.toRadians(90)));

        Action MoveToHighBar = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0, 30))
                .build();

        Action MoveBackToPlaceSpecimen = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(-35, 55))
                .build();

        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        MoveToHighBar,
                        slideRotation.highBarSpecimen(),
                        extensionMotor.highBarSpecimen(),
                        claw.close()
                ),
                new SequentialAction(
                        extensionMotor.specimenHIGHERBar()
                )

        ));

    }
}