package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.ExtendedMecanumController;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;

@Autonomous(name = "AutonomousCraterFull")
public class AutonomousCraterFull extends AshevilleAuto
{
    /*
    private static final double AWAY_FROM_LANDER_TIME = RIGHTTIME + 1.6;
    private static final double ADVANCE_WALL_TIME = AWAY_FROM_LANDER_TIME + 1.6;
    private static final double SPIN_LEFT_TIME = ADVANCE_WALL_TIME + 3.05;
    private static final double NUDGE_WALL_TIME = SPIN_LEFT_TIME + 1.5;
    private static final double NUDGE_AWAY_FROM_WALL_TIME = NUDGE_WALL_TIME + 0.4;
    private static final double DUMP_MARKER_TIME = NUDGE_AWAY_FROM_WALL_TIME + 1.7;
    private static final double BACK_TO_WALL_TIME = DUMP_MARKER_TIME + .5;
    private static final double TO_CRATER_TIME = BACK_TO_WALL_TIME + 2.35;
    private static final double TO_SAMPLE_TIME = TO_CRATER_TIME + .8;
    private static final double BREAK_CRATER_TIME = TO_SAMPLE_TIME + .8;*/

    //New values
    private static final double AWAY_FROM_LANDER_TIME = CENTERTIME + 0.85;
    private static final double ADVANCE_WALL_TIME = AWAY_FROM_LANDER_TIME + 1.6;
    private static final double SPIN_LEFT_TIME = ADVANCE_WALL_TIME + 1;
    private static final double NUDGE_WALL_TIME = SPIN_LEFT_TIME + 1.5;
    private static final double NUDGE_AWAY_FROM_WALL_TIME = NUDGE_WALL_TIME + 0.2;
    private static final double DUMP_MARKER_TIME = NUDGE_AWAY_FROM_WALL_TIME + 1.5;
    private static final double SPIN_MARKER_TIME = DUMP_MARKER_TIME + 2.75;
    private static final double BACK_TO_WALL_TIME = SPIN_MARKER_TIME + 1.0;
    private static final double FLIP_OUT_ARM_TIME = BACK_TO_WALL_TIME + 2.0;


   // private static final double TO_CRATER_TIME = BACK_TO_WALL_TIME + 2.35;
   // private static final double TO_SAMPLE_TIME = TO_CRATER_TIME + .8;
  //  private static final double BREAK_CRATER_TIME = TO_SAMPLE_TIME + .8;

    private Robot robot;

    @Override
    public void init()
    {
        super.init();
        this.robot = new MecanumRobot(((OpMode) this).hardwareMap);
    }

/*
    This is the afterLanding override that we used in states.

    protected void afterLanding()
    {
        if (elapsedTime < AWAY_FROM_LANDER_TIME) {
            goRight(.3);
        } else if (elapsedTime < ADVANCE_WALL_TIME) {
            goBackward(.5);
        } else if (elapsedTime < SPIN_LEFT_TIME) {
            this.robot.startRotating(Robot.RotationDirection.LEFT, 0.2F);
        } else if (elapsedTime < NUDGE_WALL_TIME) {
            goForward(0.25);
        } else if (elapsedTime < NUDGE_AWAY_FROM_WALL_TIME) {
            goBackward(0.25);
        } else if (elapsedTime < DUMP_MARKER_TIME) {
            goRight(1);
        } else if (elapsedTime < BACK_TO_WALL_TIME) {
            goForward(.5);
        } else if (elapsedTime < TO_CRATER_TIME) {
            goLeft(0.85);
        } else if (elapsedTime < TO_SAMPLE_TIME) {
            goBackward(.25);
        } else if (elapsedTime < BREAK_CRATER_TIME) {
            goLeft(.5);
        } else {
            stopMotors();
        }
    }
*/


    protected void afterLanding()
    {
        if (elapsedTime < AWAY_FROM_LANDER_TIME) {
            goRight(.3);
        } else if (elapsedTime < ADVANCE_WALL_TIME) {
            goBackward(.5);
        } else if (elapsedTime < SPIN_LEFT_TIME) {
            this.robot.startRotating(Robot.RotationDirection.LEFT, 0.2F);
        } else if (elapsedTime < NUDGE_WALL_TIME) {
            goRight(0.25);
        } else if (elapsedTime < NUDGE_AWAY_FROM_WALL_TIME) {
            goLeft(0.25);
        } else if (elapsedTime < DUMP_MARKER_TIME) {
            goBackward(0.5);
        } else if (elapsedTime < SPIN_MARKER_TIME) {
            telemetry.addLine("Inside of SPIN_MARKER_TIME");
            stopMotors();
            spinIntakeServo(Servo.Direction.FORWARD);
        } else if (elapsedTime < BACK_TO_WALL_TIME) {
            goForward(0.5);
        } else if (elapsedTime < FLIP_OUT_ARM_TIME) {
            hardwareMap.getCurlArmMotor().setPower(0.2);
        } else {
           stopMotors();
           this.hardwareMap.getCurlArmMotor().setPower(0);
        }
    }

    @Override
    public void stop()
    {
        stopIntakeServo();
    }
}
