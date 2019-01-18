package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.AbstractRobot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;

public class MecanumRobot extends AbstractRobot
{
    private static final int TICKS_PER_ROTATION = 1220,
                             NUMBER_OF_MOTORS   = 4;

    private static final float RADIUS = 2,
                        MOVE_ACCURACY = 0.4F;
    private static final double CIRCUMFERENCE = 2 * Math.PI * MecanumRobot.RADIUS;


    private MecanumHardwareMap hardwareMap;

    private boolean finishedMoving;

    private double startEncoderPosition;

    public MecanumRobot(HardwareMap map)
    {
        super(map);
        this.finishedMoving = false;
        this.startEncoderPosition = 0;
    }

    @Override
    public void startMoving(MovementDirection direction, float power)
    {
        if (power < -1 || power > 1) {
            throw new IllegalArgumentException("Power is out of bounds");
        } else switch (direction) {
            case FORWARD:
                this.hardwareMap.getFrontLeftMotor().setPower(power);
                this.hardwareMap.getFrontRightMotor().setPower(power);
                this.hardwareMap.getBackLeftMotor().setPower(power);
                this.hardwareMap.getBackRightMotor().setPower(power);
                break;

            case BACKWARD:
                this.hardwareMap.getFrontLeftMotor().setPower(-power);
                this.hardwareMap.getFrontRightMotor().setPower(-power);
                this.hardwareMap.getBackLeftMotor().setPower(-power);
                this.hardwareMap.getBackRightMotor().setPower(-power);
                break;

            case LEFT:
                this.hardwareMap.getFrontLeftMotor().setPower(power);
                this.hardwareMap.getFrontRightMotor().setPower(-power);
                this.hardwareMap.getBackLeftMotor().setPower(-power);
                this.hardwareMap.getBackRightMotor().setPower(power);
                break;

            case RIGHT:
                this.hardwareMap.getFrontLeftMotor().setPower(-power);
                this.hardwareMap.getFrontRightMotor().setPower(power);
                this.hardwareMap.getBackLeftMotor().setPower(power);
                this.hardwareMap.getBackRightMotor().setPower(-power);
                break;

            default:
                throw new IllegalArgumentException("Invalid direction.");
        }
    }

    @Override
    public void stopMoving()
    {
        this.hardwareMap.getFrontLeftMotor().setPower(0);
        this.hardwareMap.getFrontRightMotor().setPower(0);
        this.hardwareMap.getBackLeftMotor().setPower(0);
        this.hardwareMap.getBackRightMotor().setPower(0);
    }

    @Override
    public void startRotating(RotationDirection direction, float power) {
        if (power < -1 || power > 1) {
            throw new IllegalArgumentException("Power is out of bounds.");
        } else switch (direction) {
            case LEFT:
                this.hardwareMap.getFrontLeftMotor().setPower(power);
                this.hardwareMap.getFrontRightMotor().setPower(-power);
                this.hardwareMap.getBackLeftMotor().setPower(power);
                this.hardwareMap.getBackRightMotor().setPower(-power);
                break;
            case RIGHT:
                this.hardwareMap.getFrontLeftMotor().setPower(-power);
                this.hardwareMap.getFrontRightMotor().setPower(power);
                this.hardwareMap.getBackLeftMotor().setPower(-power);
                this.hardwareMap.getBackRightMotor().setPower(power);
                break;
            default:
                throw new IllegalArgumentException("Invalid direction.");
        }
    }

    @Override
    public void stopRotating()
    {
        stopMoving();
    }

    @Override
    public boolean moveDistance(MovementDirection direction, float feet)
    {
        if (this.finishedMoving) {
            this.finishedMoving = false;
            this.startEncoderPosition = this.getFrontLeftDistanceTravelled();
        }

        final double distanceTravelled = this.getBackLeftDistanceTravelled() - this.startEncoderPosition;

        if (distanceTravelled >= feet) {
            this.stopMoving();
            return (this.finishedMoving = true);
        } else {
            if (!direction.equals(MovementDirection.FORWARD))
                throw new UnsupportedOperationException("Can only move forward.");
            else {
                this.startMoving(direction, MecanumRobot.MOVE_ACCURACY);
                return false;
            }
        }
    }

    private double getFrontLeftDistanceTravelled()
    {
        return this.hardwareMap.getFrontLeftMotor().getCurrentPosition() /
                MecanumRobot.CIRCUMFERENCE * MecanumRobot.TICKS_PER_ROTATION;

    }

    private double getFrontRightDistanceTravelled()
    {
        return this.hardwareMap.getFrontRightMotor().getCurrentPosition() /
                MecanumRobot.CIRCUMFERENCE * MecanumRobot.TICKS_PER_ROTATION;
    }

    private double getBackLeftDistanceTravelled()
    {
        return this.hardwareMap.getBackLeftMotor().getCurrentPosition() /
                MecanumRobot.CIRCUMFERENCE * MecanumRobot.TICKS_PER_ROTATION;
    }

    private double getBackRightDistanceTravelled()
    {
        return this.hardwareMap.getBackRightMotor().getCurrentPosition() /
                MecanumRobot.CIRCUMFERENCE * MecanumRobot.TICKS_PER_ROTATION;
    }

    @Override
    public boolean encodersSupported()
    {
        return true;
    }
}
