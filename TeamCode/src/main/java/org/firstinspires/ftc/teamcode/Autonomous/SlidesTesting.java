package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Config
@Autonomous(name = "SlidesTesting", group = "Autonomous")
public class SlidesTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("slideLeftMotorPos", 2000);
        telemetry.update();
        Slides slides = new Slides(hardwareMap, telemetry);

        waitForStart();

//        Actions.runBlocking(slides.highBasketExtension());
        Actions.runBlocking(slides.lowBasketExtension());
//        Actions.runBlocking(slides.submersibleExtension());
//        Actions.runBlocking(slides.retractSlideExtension());
    }

}
