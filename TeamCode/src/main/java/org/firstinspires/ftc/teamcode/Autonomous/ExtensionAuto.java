package org.firstinspires.ftc.teamcode.Autonomous;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class ExtensionAuto {
    private DcMotor extensionMotor;
    private Telemetry telemetry;

    public ExtensionAuto(HardwareMap hardwareMap, Telemetry telemetryB){
        extensionMotor = hardwareMap.get(DcMotor.class, "slideExtensionMotor");
        extensionMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        extensionMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        telemetry = telemetryB;
    }

    public class ExtensionHigh implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                while(extensionMotor.getCurrentPosition() < 100) {
                    extensionMotor.setPower(.3);
                }
                initialized = true;
            }
            return true;
        }
    }

    public class ExtensionLow implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
               while(extensionMotor.getCurrentPosition() > 100) {
                   extensionMotor.setPower(-.3);
               }
                initialized = true;
            }
            return true;
        }
    }

    public Action extensionHigh(){
        return new ExtensionHigh();
    }

    public Action extensionLow(){
        return new ExtensionLow();
    }

}