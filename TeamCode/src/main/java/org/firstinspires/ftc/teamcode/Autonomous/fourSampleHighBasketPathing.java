package org.firstinspires.ftc.teamcode.Autonomous;

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
@Autonomous(name = "fourSampleHighBasketPathing", group = "23393 Auto")
public class fourSampleHighBasketPathing extends LinearOpMode {
    @Override
    public void runOpMode() {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(36, 60, Math.toRadians(270)));

        Action TrajectorySampleThenHighBasket = drive.actionBuilder(drive.pose)
                //move and turn to face basket for preloaded sample
                .strafeToLinearHeading(new Vector2d(56,56), Math.toRadians(45))

                //Up to Outside or first Sample
                .strafeToLinearHeading(new Vector2d(42, 29),Math.toRadians(290))

                //back up from sample
                .strafeTo(new Vector2d(42, 50))

                //basket for first sample
                .strafeToLinearHeading(new Vector2d(56,56), Math.toRadians(45))

                //up to second sample
                .strafeToLinearHeading(new Vector2d(56,29), Math.toRadians(290))

                //back and facing basket for second sample
                .strafeToLinearHeading(new Vector2d(56,56), Math.toRadians(45))

                //up to third sample
                .strafeToLinearHeading(new Vector2d(62, 29),Math.toRadians(290))

                //back and facing basket for third sample
                .strafeToLinearHeading(new Vector2d(56, 56), Math.toRadians(45))

                .build();
    }

}
