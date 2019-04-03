package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;


@Autonomous(name = "AutonomousDepotFull")
public class AutonomousDepotFull extends AshevilleAuto {
    static double CRATERTIME = RIGHTTIME + 2.1;
    static double BACKTIME = CRATERTIME + 1;
    static double PUSHBACKTIME = BACKTIME + 1;
    //static double GETOUTOFDEPOTTIME = PUSHBACKTIME + 1.8;
    static double ROTATE_TIME = PUSHBACKTIME + .7;
    static double ADVANCE_TO_CRATER_TIME = ROTATE_TIME + 2.5;
//    was 3

    private Robot robot;

    @Override
    public void init()
    {
        super.init();
        this.robot = new MecanumRobot(((OpMode) this).hardwareMap);
    }

    protected void afterLanding() {
        if(elapsedTime < CRATERTIME) {
            goRight(1.0); // Moves us towards the depot NOTE TO SELF: CHANGE NAME
        } else if(elapsedTime < BACKTIME) {
            goLeft(.5); // Abruptly reverses direction to throw the marker off
        } else if(elapsedTime < PUSHBACKTIME) {
            goRight(.5); // goes back towards the depot in order to push the marker in,
//                                 in case we didn't get the marker all the way in the depot
//        } else if(elapsedTime < GETOUTOFDEPOTTIME ){
//            goLeft(.5); //tries to egt us out of the depot and get away from our marker

        } else if (elapsedTime < ROTATE_TIME) {
            this.robot.startRotating(Robot.RotationDirection.LEFT, 0.2F);
        } else if (elapsedTime < ADVANCE_TO_CRATER_TIME) {
            goBackward(.5);
        } else {
            stopMotors(); // shuts off the motors after all actions are completed
        }
    }
}


