package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "SlideRotationUpright", group = "Mechanism Tests")
public class SlideRotationUprightTest extends LinearOpMode {

    private SlidesRotationClass slideRotation;


    @Override
    public void runOpMode() {
        slideRotation = new SlidesRotationClass(hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(slideRotation.yellowSamplePickUp()));

    }
}