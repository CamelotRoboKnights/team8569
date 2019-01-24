package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumRobot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;

@Autonomous(name = "ScrimmageAutoTest")
@Disabled
public class ScrimmageAutoTest extends IterativeActionOpMode
{
    private MecanumRobot robot;

    @Override
    protected void initState()
    {
        this.robot = new MecanumRobot(this.hardwareMap);
    }

    @Action
    public void move(IterativeState state, HardwareMap map)
    {
        if (this.robot.moveDistance(Robot.MovementDirection.FORWARD, 500)) state.setCompleted(true);
    }

    @Override
    protected boolean automaticallyCompleteActions()
    {
        return false;
    }
}
