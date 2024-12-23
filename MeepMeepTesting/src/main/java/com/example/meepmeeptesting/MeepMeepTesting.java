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
                            drive.trajectorySequenceBuilder(new Pose2d(-12, 60, Math.toRadians(90)))
<<<<<<< HEAD

                                    .strafeTo(new Vector2d(0, 31))

                                    .lineToLinearHeading(new Pose2d(-42, 62, Math.toRadians(270)))
                                   // .strafeTo(new Vector2d(-42, 62))

                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))

=======
                                    // high bar with preloaded specimen
                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))
                                    // push 3 samples into observation zone
                                    .strafeTo(new Vector2d(-35, 40))
                                    .lineToLinearHeading(new Pose2d(-35,12, Math.toRadians(90)))
                                    .strafeTo(new Vector2d(-42, 12))
                                    .strafeTo(new Vector2d(-42, 55))
                                    .strafeTo(new Vector2d(-42, 12))
                                    .strafeTo(new Vector2d(-54, 12))
                                    .strafeTo(new Vector2d(-54, 53))
                                    .strafeTo(new Vector2d(-54, 12))
                                    .strafeTo(new Vector2d(-62, 12))
                                    .strafeTo(new Vector2d(-62, 55))
                                    // pick up specimen 2 from human player
                                    .lineToLinearHeading(new Pose2d(-42, 56, Math.toRadians(270)))
                                    // high bar with specimen 2
                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))
                                    // pick up specimen 3 from human player
                                    .lineToLinearHeading(new Pose2d(-42, 56, Math.toRadians(270)))
                                    // high bar with specimen 3
                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))
                                    // pick up specimen 4 from human player
                                    .lineToLinearHeading(new Pose2d(-42, 56, Math.toRadians(270)))
                                    // high bar with specimen 4
                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))
                                    // pick up specimen 5 from human player
                                    .lineToLinearHeading(new Pose2d(-42, 56, Math.toRadians(270)))
                                    // high bar with specimen 5
                                    .lineToLinearHeading(new Pose2d(0, 31, Math.toRadians(90)))
>>>>>>> e269de156400318108904fe2808a15db6c761f74
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