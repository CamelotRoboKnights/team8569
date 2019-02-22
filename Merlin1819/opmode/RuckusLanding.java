package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.ExtendedMecanumController;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;

import java.util.Calendar;

public class RuckusLanding extends IterativeActionOpMode
{
    protected Robot robot;
    private ElapsedTime elapsedTime;
    private ElapsedTime lastTime;

    private static final long DOWN_TIME  = 4600, //4.6 seconds in millis
                            UNLATCH_TIME = 1000,
                            FORWARD_TIME = 2000;

    private static final float LOWERING_POWER = 0.5F,
                               UNLATCH_POWER  = 0.125F,
                               FORWARD_POWER  = 0.125F;

    private final void resetTime()
    {
        this.lastTime.reset();
    }

    private final boolean timePassed(long milliseconds)
    {
        return (this.lastTime.milliseconds() >= milliseconds);
    }

    private ExtendedMecanumController getExtendedController()
    {
        return this.robot.getRobotComponentController(ExtendedMecanumController.class);
    }

    protected void initState() {
        this.robot = new MecanumRobot(super.hardwareMap);
    }

    @Action(order = 0)
    public void startLowering(IterativeState state, HardwareMap map)
    {
        this.elapsedTime = new ElapsedTime();
        this.lastTime = new ElapsedTime();

        this.resetTime();
        this.getExtendedController().startMovingRetractionArm(LOWERING_POWER);
    }

    @Action(order = 1)
    public void checkLowered(IterativeState state, HardwareMap map)
    {
        if (this.timePassed(DOWN_TIME)) {
            this.getExtendedController().stopMovingRetractionArm();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }

    @Action(order = 2)
    public void startUnlatching(IterativeState state, HardwareMap map)
    {
        this.resetTime();
        //The robot is at an orientation where this will move us sideways.
        this.robot.startMoving(Robot.MovementDirection.FORWARD, UNLATCH_POWER);
    }

    @Action(order = 3)
    public void checkIfUnlatched(IterativeState state, HardwareMap map)
    {
        if (this.timePassed(UNLATCH_TIME)) {
            this.robot.stopMoving();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }

    @Action(order = 4)
    public void startMovingOut(IterativeState state, HardwareMap map)
    {
        this.resetTime();
        //The robot is at an orientation where this will move us forward.
        this.robot.startMoving(Robot.MovementDirection.RIGHT, FORWARD_POWER);
    }

    @Action(order = 5)
    public void checkIfMovedOut(IterativeState state, HardwareMap map)
    {
        if (timePassed(FORWARD_TIME)) {
            this.robot.stopMoving();
            state.setCompleted(true);
        } else state.setCompleted(false);
    }
}
