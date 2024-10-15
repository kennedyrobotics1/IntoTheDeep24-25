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
        Slides slides = new Slides(hardwareMap);

        waitForStart();

        Actions.runBlocking(slides.slideExtention());
    }

}
