package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

@Autonomous(name = "MarkerAutonomous", group = "Beta")
public class MarkerAuto extends IterativeActionOpMode
{
    private MecanumHardwareMap hardwareMap;

    @Override
    protected void initState()
    {
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    @Action(order = 0)
    public void resetEncoders(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Action(order = 1)
    public void setRunMode(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Action(order = 2)
    public void goRight(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getFrontLeftMotor().setTargetPosition(1750);
        this.hardwareMap.getFrontRightMotor().setTargetPosition(-1750);
        this.hardwareMap.getBackLeftMotor().setTargetPosition(-1750);
        this.hardwareMap.getBackRightMotor().setTargetPosition(1750);

        this.hardwareMap.getFrontLeftMotor().setPower(.25);
        this.hardwareMap.getFrontRightMotor().setPower(.25);
        this.hardwareMap.getBackLeftMotor().setPower(.25);
        this.hardwareMap.getBackRightMotor().setPower(.25);

        this.telemetry.addData("FrontLeft Encoder", this.hardwareMap.getFrontLeftMotor().getCurrentPosition());
        this.telemetry.update();
    }

    @Action(order = 3)
    public void waitForRightCompletion(IterativeState state, HardwareMap map)
    {
        if (Math.abs(this.hardwareMap.getFrontLeftMotor().getPower()) > 0.05) state.setCompleted(false);
        else {
            state.setCompleted(true);

            this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            this.telemetry.addData("Reset", this.hardwareMap.getFrontLeftMotor().getCurrentPosition());
            this.telemetry.update();
        }
    }



    @Action(order = 5)
    public void backUp(IterativeState state, HardwareMap map)
    {
        this.hardwareMap.getFrontLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getFrontRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackLeftMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.hardwareMap.getBackRightMotor().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.hardwareMap.getFrontLeftMotor().setTargetPosition(-1604);
        this.hardwareMap.getFrontRightMotor().setTargetPosition(-1604);
        this.hardwareMap.getBackLeftMotor().setTargetPosition(-1604);
        this.hardwareMap.getBackRightMotor().setTargetPosition(-1604);

        this.hardwareMap.getFrontLeftMotor().setPower(.5);
        this.hardwareMap.getFrontRightMotor().setPower(.5);
        this.hardwareMap.getBackLeftMotor().setPower(.5);
        this.hardwareMap.getBackRightMotor().setPower(.5);

        this.telemetry.addData("New FrontLeft", this.hardwareMap.getFrontLeftMotor().getCurrentPosition());
        this.telemetry.update();
    }

    @Action(order = 8)
    public void stop(IterativeState state, HardwareMap map)
    {
        if (this.hardwareMap.getFrontRightMotor().getCurrentPosition() > -1550)
            state.setCompleted(false);
        else {
            this.hardwareMap.getFrontLeftMotor().setPower(0);
            this.hardwareMap.getFrontRightMotor().setPower(0);
            this.hardwareMap.getBackLeftMotor().setPower(0);
            this.hardwareMap.getBackRightMotor().setPower(0);
            state.setCompleted(true);
        }

        this.telemetry.addData("Brake", this.hardwareMap.getFrontLeftMotor().getCurrentPosition());
        this.telemetry.update();
    }
}