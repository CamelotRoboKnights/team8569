package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;

@Autonomous(name = "ScrimmageAutoTest")
@Disabled
public class ScrimmageAutoTest extends IterativeActionOpMode
{
    private MecanumRobot robot;
    private int iteration;

    @Override
    protected void initState()
    {
        this.robot = new MecanumRobot(this.hardwareMap);
        this.iteration = 0;
    }

    @Action
    public void move(IterativeState state, HardwareMap map)
    {
        if (this.robot.moveDistance(Robot.MovementDirection.FORWARD, 500)) state.setCompleted(true);
        else {
            this.telemetry.addData("Iteration", ++this.iteration);
        }
    }

    @Override
    protected boolean automaticallyCompleteActions()
    {
        return false;
    }
}
