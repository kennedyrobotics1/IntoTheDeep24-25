package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "ClawAutoOpen", group = "Mechanism Tests")
public class ClawAutoOpen extends LinearOpMode {

    private ClawClass claw;


    @Override
    public void runOpMode() {
        claw = new ClawClass(hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;

        //run action
        Actions.runBlocking(claw.open());


    }
}

