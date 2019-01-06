package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.AbstractRobot;

public class MecanumRobot extends AbstractRobot
{
    private MecanumHardwareMap hardwareMap;

    public MecanumRobot(HardwareMap map)
    {
        super(map);
        this.hardwareMap = new MecanumHardwareMap(map);
        stopMoving();
    }

    @Override
    public void startMoving(MovementDirection direction, float power)
    {
        if (power < -1 || power > 1) {
            throw new IllegalArgumentException("Power is out of bounds.");
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
                this.hardwareMap.getFrontRightMotor().setPower(-power);
                this.hardwareMap.getBackLeftMotor().setPower(-power);
                this.hardwareMap.getBackRightMotor().setPower(power);
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
    public void startRotating(RotationDirection direction, float power)
    {
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


}
