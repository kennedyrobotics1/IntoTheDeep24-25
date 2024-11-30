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
@Autonomous(name = "peepypoopyfartpoop", group = "Autonomous")
public class peepypoopyfartpoop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        SlidesRotationAuto armRight = new SlidesRotationAuto(hardwareMap);
        SlidesRotationAuto armLeft = new SlidesRotationAuto(hardwareMap);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                armLeft.highBasket(),
                                armRight.highBasket()
                        ),
                        new ParallelAction(
                                armLeft.subPickUp(),
                                armRight.subPickUp()
                        )
                )
        );
    }

}
