package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@Config
@Autonomous(name = "TestingSlidesAndIntakeAuto", group = "Autonomous")
public class TestingSlidesAndIntakeAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SlidesExtension slides = new SlidesExtension(hardwareMap, telemetry);

        SlidesRotation armRight = new SlidesRotation(hardwareMap, telemetry);
        SlidesRotation armLeft = new SlidesRotation(hardwareMap, telemetry);

        IntakeRotation intakeRotation = new IntakeRotation(hardwareMap, telemetry);
        Claw claw = new Claw(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        slides.highBasketExtension(),
                        new ParallelAction(
                                armLeft.highBasket(),
                                armRight.highBasket()
                        ),
                        intakeRotation.highBasket(),
                        claw.open()
                )
        );
//        Actions.runBlocking(slides.lowBasketExtension());
//        Actions.runBlocking(slides.submersibleExtension());
//        Actions.runBlocking(slides.retractSlideExtension());
    }

}