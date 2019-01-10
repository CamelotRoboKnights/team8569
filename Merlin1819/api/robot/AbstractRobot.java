package org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 *
 * This class is a helper to creating
 * a {@link Robot} implementation.
 *
 * It is recommended that if you
 * extend this class to create a
 * working implementation.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see Robot
 */
public abstract class AbstractRobot implements Robot
{
    protected HardwareMap map;

    protected AbstractRobot(HardwareMap map)
    {
        this.map = map;
    }

    @Override
    public boolean encodersSupported()
    {
        return false;
    }

    @Override
    public boolean gyroSupported()
    {
        return false;
    }

    @Override
    public float getOrientation()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveDistance(MovementDirection direction, float meters)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void rotateDistance(MovementDirection direction, float degrees)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void startMoving(float degrees, float power)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void moveDistance(float degrees, float power)
    {
        throw new UnsupportedOperationException();
    }
}
