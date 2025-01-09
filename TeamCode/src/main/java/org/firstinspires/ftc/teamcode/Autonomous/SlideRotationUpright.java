package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Config
@Autonomous(name = "SlideRotationUpright", group = "Mechanism Tests")
public class SlideRotationUpright extends LinearOpMode {

    private SlidesRotationClass slideRotation;


    @Override
    public void runOpMode() {
        slideRotation = new SlidesRotationClass(hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(slideRotation.highBarSpecimen()));

    }
}