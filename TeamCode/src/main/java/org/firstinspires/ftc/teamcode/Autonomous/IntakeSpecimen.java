package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class IntakeSpecimen {

    private SlidesRotationClass slideRotation;
    private ExtensionClass extensionMotor;
    private IntakeWristClass wrist;
    private ClawClass claw;

    public Action intakeSpecimen(){
        return new SequentialAction(
                new ParallelAction(
                        slideRotation.pickUpSpecimenFromHumanPlayer(),
                        wrist.pickUpSpecimenFromHumanPlayer()
                ),
                claw.close()
        );
    }
}


//needed to intake specimen
//slideRotation.pickUpSpecimenFromHumanPlayer()
//claw.close()
//wrist.pickUpSpecimenFromHumanPlayer()