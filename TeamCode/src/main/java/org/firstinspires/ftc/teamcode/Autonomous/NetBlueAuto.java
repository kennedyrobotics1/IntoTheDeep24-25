package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTracker;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
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
import java.util.concurrent.TimeUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "NetBlueAuto", group = "23393 Auto")
public class NetBlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {



        AutonomousTracker auto = new AutonomousTracker(0, 0, 0);
        auto.invertY = true;
        auto.invertX = true;

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(270)));
        

//
//                //up to Middle Sample
//                .strafeTo(auto.update2d(0, MOVE_UP_TO_SAMPLE_INITIAL))
//
//                //Side to Middle Sample
//                .strafeTo(auto.update2d(MOVE_LEFT_LITTLE, 0))
//
//                //Down to Score Middle Sample
//                .strafeTo(auto.update2d(0, MOVE_BACKWARDS))
//
//                //Up to Last Sample
//                .strafeTo(auto.update2d(0, -MOVE_BACKWARDS))
//
//                //Side to Last Sample
//                .strafeTo(auto.update2d(MOVE_LEFT_LITTLE, 0))
//
//                //Down To Last Sample
//                .strafeTo(auto.update2d(0, MOVE_BACKWARDS_FINAL))
//
//                .strafeTo(auto.update2d(100, 0))
//
//                .build();

        double MOVE_UP_TO_SAMPLE_INITIAL = 52;
        double MOVE_LEFT_LITTLE = -8;
        double MOVE_BACK_SCORE_SAMPLE1 = -43;

        Action Sample1Retrieve = drive.actionBuilder(drive.pose)

                //Up to Outside Sample
                .strafeTo(auto.update2d(0, MOVE_UP_TO_SAMPLE_INITIAL))

                //side to Outside Sample
                .strafeTo(auto.update2d(MOVE_LEFT_LITTLE, 0))

                //Down to Score Outside Sample
                .strafeTo(auto.update2d(0, MOVE_BACK_SCORE_SAMPLE1/2))

                .strafeTo(auto.update2d(-3, MOVE_BACK_SCORE_SAMPLE1/2))

                .build();


        // constants
        double MOVE_BACKWARDS = -48;
        double MOVE_BACKWARDS_FINAL = -35;

        Action Sample2and3Score = drive.actionBuilder(drive.pose)

                //up to Middle Sample
                .strafeTo(auto.update2d(0, MOVE_UP_TO_SAMPLE_INITIAL))

                //Side to Middle Sample
                .strafeTo(auto.update2d(MOVE_LEFT_LITTLE, 0))

                //Down to Score Middle Sample
                .strafeTo(auto.update2d(0, MOVE_BACKWARDS))

                //Up to Last Sample
                .strafeTo(auto.update2d(0, -MOVE_BACKWARDS))

                //Side to Last Sample
                .strafeTo(auto.update2d(MOVE_LEFT_LITTLE, 0))

                //Down To Last Sample
                .strafeTo(auto.update2d(0, MOVE_BACKWARDS_FINAL))

                .strafeTo(auto.update2d(100, 0))

                .build();





        while (!isStopRequested() && !opModeIsActive()) {

        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(
                new SequentialAction(
                        Sample1Retrieve,
                        new SleepAction(1),
                        Sample2and3Score,
                        new Action() {
                        // This action and the following action do the same thing

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