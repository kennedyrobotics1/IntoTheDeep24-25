package org.firstinspires.ftc.teamcode.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousTracker;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@Config
@Autonomous(name = "SkeletonSubsystemAuto", group = "23393 Auto")
public class SkeletonSubsystemAuto extends LinearOpMode {

    private SlideRotationClass slideRotation;
    private SlideExtensionClass extensionMotor;

    @Override
    public void runOpMode() {

        slideRotation = new SlideRotationClass(hardwareMap, telemetry);
        extensionMotor = new SlideExtensionClass(hardwareMap, telemetry);


        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;





        Actions.runBlocking(
                new SequentialAction(
                        slideRotation.highSpecimenPosition()
                )
        );

//        Actions.runBlocking(
//                new SequentialAction(
//                        extensionMotor.highSpecimenExtend()
//                )
//        );

    }
}