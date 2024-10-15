package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

// Non-RR imports
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
   public class Slides {
       private DcMotorEx slideLeftMotor;
       private DcMotorEx slideRightMotor;

       public Slides(HardwareMap hardwareMap){
           slideLeftMotor = hardwareMap.get(DcMotorEx.class, "slideLeftMotor");
           slideRightMotor = hardwareMap.get(DcMotorEx.class, "slideRightMotor");
       }

       public class SlideExtention implements Action {

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
               packet.put("slideLeftMotorPos", posLeft);
              // packet.put("slideRightMotorPos", posRight);

               telemetry.addData("slideLeftMotorPos", posLeft);
               if (posLeft < 40.0){
                   return true;
               } else{
                   slideLeftMotor.setPower(0);
                   return false;
               }

       }
       }
    public Action slideExtention(){

           return new SlideExtention();
    }
   }
