package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Config
@Autonomous(name = "SlidesTesting", group = "Autonomous")
public class SlidesTesting extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("slideLeftMotorPos", 2000);
        telemetry.update();
        Slides slides = new Slides(hardwareMap, telemetry);
        waitForStart();

       /* Actions.runBlocking(slides.highBasketExtention());
        Actions.runBlocking(slides.lowBasketExtention());
        Actions.runBlocking(slides.submersibleExtention());
        Actions.runBlocking(slides.retractSlideExtention()); */
    }

}
