package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import android.support.annotation.FractionRes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive.robot.MecanumRobot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

@Autonomous(name = "LandingAutonomous")
public class LandingAuto extends IterativeActionOpMode
{
    private MecanumHardwareMap hardwareMap;

    private static int inchesToTicks(double many, int gearboxRatio)
    {
        final double wheelRadius = 2; //inches
        final double wheelCircumference = 2 * Math.PI * wheelRadius;
        final int pulsesPerRotation = 7,
                countsPerRotation = pulsesPerRotation * 4;
        final double countsPerFullRotation = countsPerRotation * gearboxRatio;
        final double countsPerInch = countsPerFullRotation / wheelCircumference;

        return (int) (countsPerInch * many);
    }

    @Override
    protected void initState()
    {
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    @Action(order = 0)
    public void initTargets(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getRetractArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getStringMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Action(order = 1)
    public void releasePin(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getStringMotor().setTargetPosition(720);
        this.hardwareMap.getStringMotor().setPower(.5);
    }

    @Action(order = 2)
    public void bringDownRetractArm(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getRetractArmMotor().setTargetPosition(-4830);
        this.hardwareMap.getRetractArmMotor().setPower(0.25);
    }

  //  @Action(order = 3)
    public void strafeRight(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getFrontLeftMotor().setTargetPosition(inchesToTicks(-3, 20));
        this.hardwareMap.getFrontRightMotor().setTargetPosition(inchesToTicks(3, 20));
        this.hardwareMap.getBackLeftMotor().setTargetPosition(inchesToTicks(3, 20));
        this.hardwareMap.getBackRightMotor().setTargetPosition(inchesToTicks(-3, 20));

        this.hardwareMap.getFrontLeftMotor().setPower(0.25);
        this.hardwareMap.getFrontRightMotor().setPower(0.25);
        this.hardwareMap.getBackLeftMotor().setPower(0.25);
        this.hardwareMap.getBackRightMotor().setPower(0.25);
    }

 //   @Action(order = 4)
    public void releaseTube(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getTubeServo().setPosition(1);
    }
}
