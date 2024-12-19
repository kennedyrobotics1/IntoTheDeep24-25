//package org.firstinspires.ftc.teamcode.Teleop;
//
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.ElapsedTime;
//import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Iterative;
//import org.firstinspires.ftc.teamcode.Teleop.ServoController;
//
//@TeleOp(name = "goBuildaTestingTeleop", group = "Iterative OpMode")
//
//public class goBuildaTestingTeleop extends BasicOpMode_Iterative {
//    private ElapsedTime runtime = new ElapsedTime();
//
//    private Servo claw;
//private ServoController ClawController;
//

//
//    public void init() {
//        claw = hardwareMap.get(Servo.class, "servo3");
//        ClawController = new ServoController(0);
//    }
//
//    public void start() {
//
//        //Claw
//        if (gamepad1.a) {
//            //CLAW OPEN
//            claw.setPosition(ClawController.update(0.5));
//        } else if (gamepad1.x) {
//            //CLAW CLOSE
//            claw.setPosition(ClawController.update(-0.5));
//        }
//
//    }
//
//    public void loop() {
//    }
//}