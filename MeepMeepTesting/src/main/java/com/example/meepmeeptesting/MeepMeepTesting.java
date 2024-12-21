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
                            drive.trajectorySequenceBuilder(new Pose2d(36, 60, Math.toRadians(270)))

                                    //Up to Outside Sample
                                    .strafeTo(new Vector2d(36, 8))

                                    //side to Outside Sample
                                    .strafeTo(new Vector2d(47, 8))

                                    //Down to Score Outside Sample
                                    .strafeTo(new Vector2d(47, 60))

                                    //up to Middle Sample
                                    .strafeTo(new Vector2d(47,8))

                                    //Side to Middle Sample
                                    .strafeTo(new Vector2d(57,8))

                                    //Down to Score Middle Sample
                                    .strafeTo(new Vector2d(57, 57))

                                    //Up to Last Sample
                                    .strafeTo(new Vector2d(57, 8))

                                    //Side to Last Sample
                                    .strafeTo(new Vector2d(65, 8))

                                    //Down To Last Sample
                                    .strafeTo(new Vector2d(65, 55))

                                    //Side to Park
                                    .strafeTo(new Vector2d(-50, 60))
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