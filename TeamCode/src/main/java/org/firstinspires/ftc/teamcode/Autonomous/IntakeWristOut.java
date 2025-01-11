package org.firstinspires.ftc.teamcode.Autonomous;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "IntakeWristOut", group = "Mechanism Tests")
public class IntakeWristOut extends LinearOpMode {

    private IntakeWristClass wrist;

    @Override
    public void runOpMode() {
        wrist = new IntakeWristClass(hardwareMap, telemetry);

        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(wrist.forwardToPlaceSpecimen()));


    }
}


