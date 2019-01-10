package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumHardwareMap;

/**
 *
 * An operational mode
 * that allows the user to
 * move within in current orientation
 * any angle, from 0 to 360 degrees.
 *
 * @author Zigy Lim
 * @author Zachary Ireland
 *
 * @since 1.1
 * @version 1.0
 *
 * @see CardinalDirectionTeleOp
 */
@TeleOp(name = "AllDirectionTeleOp")
public class AllDirectionTeleOp extends OpMode
{
    private static final float MOVE_DEAD_ZONE = 0.2F,
                             ROTATE_DEAD_ZONE = 0.2F;

    private MecanumHardwareMap map;

    @Override
    public void init()
    {
        this.map = new MecanumHardwareMap(this.hardwareMap);
    }

    @Override
    public void loop()
    {
        final float forward = gamepad1.left_stick_y,
                right = -gamepad1.left_stick_x,
                clockwise = (gamepad1.left_trigger >= gamepad1.right_trigger) ?
                        gamepad1.left_trigger : -gamepad1.right_trigger;

        this.map.getFrontLeftMotor().setPower(forward + clockwise + right);
        this.map.getFrontRightMotor().setPower(forward - clockwise - right);
        this.map.getBackLeftMotor().setPower(forward + clockwise - right);
        this.map.getBackRightMotor().setPower(forward - clockwise + right);
    }
}
