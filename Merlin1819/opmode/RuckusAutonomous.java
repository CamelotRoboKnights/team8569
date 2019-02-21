package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.ExtendedMecanumController;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;

import java.util.Calendar;

public abstract class RuckusAutonomous extends IterativeActionOpMode
{
    protected Robot robot;
    private ElapsedTime elapsedTime;
    private ElapsedTime lastTime;

    protected final void resetTime()
    {
        lastTime.reset();
    }

    protected final boolean timePassed(long milliseconds)
    {
        return (lastTime.milliseconds() >= milliseconds);
    }

    protected void initState() {
        this.robot = new MecanumRobot(super.hardwareMap);

    }

    /**
     * {@code @Order} is 1.
     */
    public void lower(IterativeState state, HardwareMap map)
    {
        this.resetTime();
        this.robot.getRobotComponentController(ExtendedMecanumController.class).startMovingRetractionArm(0.5F);
    }

    public void isLowered(IterativeState state, HardwareMap map)
    {
        if (this.timePassed((long) (1000 * 5.2))) {
            this.robot.getRobotComponentController(ExtendedMecanumController.class).stopMovingRetractionArm();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }

    public void unlatch(IterativeState state, HardwareMap map)
    {
        this.resetTime();
        //We are at an orientation where this will move us sideways.
        this.robot.startMoving(Robot.MovementDirection.FORWARD, 0.125F);
    }

    public void isUnlatched(IterativeState state, HardwareMap map)
    {
        if (this.timePassed(2 * 1000)) {
            this.robot.stopMoving();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }

    public void moveOut(IterativeState state, HardwareMap map)
    {
        this.resetTime();
        this.robot.startMoving(Robot.MovementDirection.RIGHT, 0.25F);
    }

    public void isMovedOut(IterativeState state, HardwareMap map)
    {
        if (timePassed(2 * 1000)) {
            this.robot.stopMoving();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }
}
