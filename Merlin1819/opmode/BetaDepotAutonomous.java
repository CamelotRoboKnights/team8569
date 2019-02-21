package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

@Autonomous(name = "BetaDepotAutonomous", group = "Beta")
public class BetaDepotAutonomous extends RuckusAutonomous
{
    @Override
    @Action(order = 0)
    public void lower(IterativeState state, HardwareMap map)
    {
        super.lower(state, hardwareMap);
    }

    @Override
    @Action(order = 1)
    public void isLowered(IterativeState state, HardwareMap map)
    {
        super.isLowered(state, map);
    }

    @Override
    @Action(order = 2)
    public void unlatch(IterativeState state, HardwareMap map)
    {
        super.unlatch(state, map);
    }

    @Override
    @Action(order = 3)
    public void isUnlatched(IterativeState state, HardwareMap map)
    {
        super.isUnlatched(state, map);
    }

    @Override
    @Action(order = 4)
    public void moveOut(IterativeState state, HardwareMap map)
    {
        super.moveOut(state, map);
    }

    @Override
    @Action(order = 5)
    public void isMovedOut(IterativeState state, HardwareMap map)
    {
        super.isMovedOut(state, map);
    }
}
