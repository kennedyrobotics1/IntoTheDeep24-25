package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MeepMeepTesting {
        public static void main(String[] args) {
            MeepMeep meepMeep = new MeepMeep(800);

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(-12, 60, Math.toRadians(270)))

                                    .strafeTo(new Vector2d(0, 31))

                                    .strafeTo(new Vector2d(0, 50))

                                    .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(90)))

                                    .lineToLinearHeading(new Pose2d(3, 31, Math.toRadians(270)))

                                    .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(90)))

                                    .lineToLinearHeading(new Pose2d(3, 31, Math.toRadians(270)))

                                    .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(90)))

                                    .lineToLinearHeading(new Pose2d(3, 31, Math.toRadians(270)))

                                    .lineToLinearHeading(new Pose2d(-45, 50, Math.toRadians(90)))

                                    .lineToLinearHeading(new Pose2d(3, 31, Math.toRadians(270)))

                                    .build()
                    );

            Image img = null;
            try { img = ImageIO.read(new File("MeepMeepTesting\\field-2024-juice-dark.png")); }
            catch (IOException e) {}
            meepMeep.setBackground(img)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }