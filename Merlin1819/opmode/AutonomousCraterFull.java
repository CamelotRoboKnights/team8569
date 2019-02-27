package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;

public class AutonomousCraterFull extends AshevilleAuto
{
    private static double HITWALLTIME = RIGHTTIME + 2.5;
    private static double SPINLEFTTIME = HITWALLTIME + 1;
    private static double BACKUPTIME = SPINLEFTTIME + 0.5;
    private static double BACKUPFORWARDTIME = BACKUPTIME + 0.2;
    private static double GORIGHTTIME = BACKUPFORWARDTIME + 2;
    private static double GOLEFTTIME = GORIGHTTIME + 3;

    private Robot robot;

    @Override
    public void init()
    {
        super.init();
        this.robot = new MecanumRobot(((OpMode) this).hardwareMap);
    }

    protected void afterLanding()
    {
        if (elapsedTime < HITWALLTIME) {
            goForward(.5);
        } else if (elapsedTime < SPINLEFTTIME) {
            this.robot.startRotating(Robot.RotationDirection.LEFT, 0.3F);
        } else if (elapsedTime < BACKUPTIME) {
            goBackward(0.25);
        } else if (elapsedTime < BACKUPFORWARDTIME) {
            goForward(0.1);
        } else if (elapsedTime < GORIGHTTIME) {
            goForward(1);
        } else if (elapsedTime < GOLEFTTIME) {
            goLeft(0.85);
        } else {
            stopMotors();
        }
    }
}
