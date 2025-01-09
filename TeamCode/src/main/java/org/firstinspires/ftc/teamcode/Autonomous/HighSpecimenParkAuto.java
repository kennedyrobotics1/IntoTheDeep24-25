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

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-12, 60, Math.toRadians(270)));

        Action MoveToHighBar = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0, 31))
                .build();

        Action MoveBackToPlaceSpecimen = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0, 50))
                .build();

        Action FirstHumanPlayerSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-45, 50), Math.toRadians(0))
                .build();

        Action PlaceFirstSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(3, 31), Math.toRadians(270))
                .build();

        Action SecondHumanPlayerSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-45, 50), Math.toRadians(90))
                .build();

        Action PlaceSecondSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(3, 31), Math.toRadians(270))
                .build();

        Action ThirdHumanPlayerSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-45, 50), Math.toRadians(90))
                .build();

        Action PlaceThirdSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(3, 31), Math.toRadians(270))
                .build();

        Action FourthHumanPlayerSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-45, 50), Math.toRadians(90))

                .build();

        Action PlaceFourthSpeciemn = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(3, 31), Math.toRadians(270))
                .build();

        Action FifthHumanPlayerSpecimen = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-45, 50), Math.toRadians(90))
                .build();

        Action PlaceFifthSpeciemn = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(3, 31), Math.toRadians(270))
                .build();



        waitForStart();

        if (isStopRequested()) return;


        Actions.runBlocking(new SequentialAction(

                FirstHumanPlayerSpecimen,
                PlaceFirstSpecimen,

                SecondHumanPlayerSpecimen,
                PlaceSecondSpecimen,

                ThirdHumanPlayerSpecimen,
                PlaceThirdSpecimen,

                FourthHumanPlayerSpecimen,
                PlaceFourthSpeciemn,

                FifthHumanPlayerSpecimen,
                PlaceFifthSpeciemn
        ));


        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        MoveToHighBar,
                        slideRotation.highBarSpecimen(),
                        claw.close(),
                        wrist.out()
                ),
                new SequentialAction(
                        MoveBackToPlaceSpecimen
                )
        ));

        Actions.runBlocking(new SequentialAction(
                new ParallelAction(
                        FirstHumanPlayerSpecimen,
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        wrist.pickUpSpecimenFromHumanPlayer(),
                        claw.open(),
                        extensionMotor.pickupFromHumanPlayer()
                ),
                new SequentialAction(
                        claw.close()
                ),
                new ParallelAction(
                        PlaceFirstSpecimen,
                        wrist.home(),
                        slideRotation.highBarSpecimen()
                )
        ));


    }
}