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
@Autonomous(name = "SlideExtendUp", group = "Mechanism Tests")
public class SlideExtendUp extends LinearOpMode {

    private ExtensionClass extensionMotor;


    @Override
    public void runOpMode() {
        extensionMotor = new ExtensionClass(hardwareMap, telemetry);


        while (!isStopRequested() && !opModeIsActive()) {
        }
        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(extensionMotor.sampleYellowPickUp()));

    }
}

