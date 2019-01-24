package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;

public abstract class MecanumAutonomous extends IterativeActionOpMode
{
    protected Robot robot;

    protected abstract void initState();

    public abstract void orient(IterativeState state, HardwareMap map);
    public abstract void unhook(IterativeState state, HardwareMap map);
    public abstract void collapseArm(IterativeState state, HardwareMap map);
    public abstract void adjustFace(IterativeState state, HardwareMap map);
    public abstract void moveToVisionRange(IterativeState state, HardwareMap map);
    public abstract void checkVisionRange(IterativeState state, HardwareMap map);
    public abstract void checkCenterSample(IterativeState state, HardwareMap map);
    public abstract void strafeRight(IterativeState state, HardwareMap map);
    public abstract void checkRightSample(IterativeState state, HardwareMap map);
    public abstract void strafeLeft(IterativeState state, HardwareMap map);
    public abstract void checkLeftSample(IterativeState state, HardwareMap map);
    public abstract void goToGoldSample(IterativeState state, HardwareMap map);
    public abstract void knockSample(IterativeState state, HardwareMap map);
    public abstract void backUp(IterativeState state, HardwareMap map);
    public abstract void move90Left(IterativeState state, HardwareMap map);
    public abstract void driveForward(IterativeState state, HardwareMap map);
    public abstract void rotateLeft45(IterativeState state, HardwareMap map);
    public abstract void goForward4Feet(IterativeState state, HardwareMap map);
    public abstract void dropMarker(IterativeState state, HardwareMap map);
    public abstract void turn180(IterativeState state, HardwareMap map);
    public abstract void goForward7Feet(IterativeState state, HardwareMap map);

    @Override
    protected boolean isCompletedByDefault()
    {
        return false;
    }
}
