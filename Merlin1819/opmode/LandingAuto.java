package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

@Autonomous(name = "LandingAutonomous")
public class LandingAuto extends IterativeActionOpMode
{
    private MecanumHardwareMap hardwareMap;

    static int inchesToTicks(double many, int gearboxRatio)
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
    public void setRunMode(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getRetractArmMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //this.hardwareMap.getStringMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Action(order = 1)
    public void releasePin(IterativeState state, HardwareMap map)
    {
        //this.hardwareMap.getStringMotor().setTargetPosition(200);
        //this.hardwareMap.getStringMotor().setPower(0.5);

        this.hardwareMap.getFrontRightMotor().setTargetPosition(200);
        this.hardwareMap.getFrontRightMotor().setPower(0.5);
    }

    @Action(order = 2)
    public void printTelemetry(IterativeState state, HardwareMap map)
    {
        //this.telemetry.addData("StringMotor", this.hardwareMap.getStringMotor().getCurrentPosition());
        this.telemetry.addData("Debug Info", this.hardwareMap.getFrontRightMotor().getCurrentPosition());
        this.telemetry.update();
        state.restartFromMethod();
    }

 //   @Action(order = 2)
    public void setMotorPower(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getRetractArmMotor().setTargetPosition(-2415);
        this.hardwareMap.getRetractArmMotor().setPower(0.25);
    }

  //  @Action(order = 3)
    public void bringDownRetractArm(IterativeState state, HardwareMap map)
    {
        if (this.hardwareMap.getRetractArmMotor().getPower() > 0.1) state.setCompleted(false);
        else state.setCompleted(true);
    }

  //  @Action(order = 4)
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

 //   @Action(order = 5)
    public void releaseTube(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getCollectorServo().setPosition(1);
    }
}
