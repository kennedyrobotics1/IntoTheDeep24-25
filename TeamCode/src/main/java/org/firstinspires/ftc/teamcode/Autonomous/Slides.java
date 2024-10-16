package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
   public class Slides {
       private DcMotorEx slideLeftMotor;
       private DcMotorEx slideRightMotor;
       private Telemetry telemetry;

       public Slides(HardwareMap hardwareMap, Telemetry telemetryA){
           slideLeftMotor = hardwareMap.get(DcMotorEx.class, "slideLeftMotor");
           slideRightMotor = hardwareMap.get(DcMotorEx.class, "slideRightMotor");
           telemetry = telemetryA;
       }

       public class HighBasketExtention implements Action {

           private boolean initialized = false;
           @Override
           public boolean run(@NonNull TelemetryPacket packet) {
               if (!initialized) {
                   slideLeftMotor.setPower(-0.5);
                  // slideRightMotor.setPower                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 0.5);
                   initialized = true;
               }

               double posLeft = slideLeftMotor.getCurrentPosition();
              // double posRight = slideRightMotor.getCurrentPosition();
               telemetry.addData("slideLeftMotorPos", posLeft);
               telemetry.update();
               if (posLeft > -900.0){
                   return true;
               } else{
                   slideLeftMotor.setPower(0);
                   return false;
               }

       }
       }
    public class LowBasketExtention implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideLeftMotor.setPower(-0.5);
                // slideRightMotor.setPower                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 0.5);
                initialized = true;
            }

            double posLeft = slideLeftMotor.getCurrentPosition();
            // double posRight = slideRightMotor.getCurrentPosition();
            telemetry.addData("slideLeftMotorPos", posLeft);
            telemetry.update();
            if (posLeft > -500.0){
                return true;
            } else{
                slideLeftMotor.setPower(0);
                return false;
            }

        }
    }

    public class SubmersibleExtention implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideLeftMotor.setPower(-0.5);
                // slideRightMotor.setPower                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 0.5);
                initialized = true;
            }

            double posLeft = slideLeftMotor.getCurrentPosition();
            // double posRight = slideRightMotor.getCurrentPosition();
            telemetry.addData("slideLeftMotorPos", posLeft);
            telemetry.update();
            if (posLeft > -350.0){
                return true;
            } else{
                slideLeftMotor.setPower(0);
                return false;
            }

        }
    }

    public class RetractSlideExtention implements Action {

        private boolean initialized = false;
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                slideLeftMotor.setPower(-0.5);
                // slideRightMotor.setPower                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 0.5);
                initialized = true;
            }
            double posLeft = slideLeftMotor.getCurrentPosition();
            // double posRight = slideRightMotor.getCurrentPosition();
            telemetry.addData("slideLeftMotorPos", posLeft);
            telemetry.update();
            if (posLeft > -500.0){
                return true;
            } else{
                slideLeftMotor.setPower(0);
                return false;
            }

        }
    }
    public Action highBasketExtention(){

           return new HighBasketExtention();
    }
    public Action lowBasketExtention(){

        return new LowBasketExtention();
    }
    public Action submersibleExtention(){

        return new SubmersibleExtention();
    }
    public Action retractSlideExtention(){

        return new RetractSlideExtention();
    }
   }
