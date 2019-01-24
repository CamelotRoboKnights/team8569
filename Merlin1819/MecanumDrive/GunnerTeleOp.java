package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumHardwareMap;

@TeleOp(name = "GunnerTeleOp")
public class GunnerTeleOp extends OpMode
{
    private static final float GAMEPAD_DEAD_ZONE = 0.2F;

    private static final float COLLECTOR_POWER = 1F;

    private MecanumHardwareMap hardwareMap;

    @Override
    public void init()
    {
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    @Override
    public void loop()
    {
        if (Math.abs(this.gamepad2.left_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCurlArmMotor().setPower(-this.gamepad2.left_stick_y);
        } else {
            this.hardwareMap.getCurlArmMotor().setPower(0);
        }

        if (Math.abs(this.gamepad2.right_stick_y) >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getRetractArmMotor().setPower(this.gamepad2.right_stick_y);
        } else {
            this.hardwareMap.getRetractArmMotor().setPower(0);
        }

        if (this.gamepad2.left_trigger >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCollectorMotor().setPower(COLLECTOR_POWER);
        }  else if (this.gamepad2.right_trigger >= GAMEPAD_DEAD_ZONE) {
            this.hardwareMap.getCollectorMotor().setPower(-COLLECTOR_POWER);
        } else {
            this.hardwareMap.getCollectorMotor().setPower(0);
        }
    }
}
