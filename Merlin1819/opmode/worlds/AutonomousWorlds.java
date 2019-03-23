package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.worlds;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.robot.Robot;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.CubeSampler;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumHardwareMap;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.MecanumRobot;

@Autonomous(name = "AutonomousWorlds", group = "Worlds")
public class AutonomousWorlds extends IterativeActionOpMode {
    private CubeSampler sampler;
    private CubeSampler.CubePosition yellowPosition;
    private Robot robot;
    private MecanumHardwareMap hardwareMap;

    @Override
    protected void initState() {
        super.initState();
        assert (this.hardwareMap != null);
        this.sampler = new CubeSampler(super.hardwareMap, 0.40);
        this.sampler.activateTfod();

        //Getting the position before the robot lands
        //TODO verify that this camera angle is optimal
        this.yellowPosition = this.sampler.detectCubePosition();
        this.robot = new MecanumRobot(super.hardwareMap);
        this.hardwareMap = new MecanumHardwareMap(super.hardwareMap);
    }

    private boolean completed = false;

    @Action(order = 0)
    public void land(IterativeState state, HardwareMap map) {
        long[] counter = new long[3];
        this.telemetry.addData("Cube Position", "Cube Position " +
                ((this.yellowPosition != null) ?
                        this.yellowPosition.toString().toLowerCase() : "cube position not detected"));
        switch (this.yellowPosition) {
            case LEFT:
                if (sideways(1, counter, 0) &&
                        dislodgeSample(counter, 1) &&
                        sideways(-1, counter, 2)) {
                    stop();
                }
                break;
            case CENTER:
                if (dislodgeSample(counter, 0)) stop();
                break;
            case RIGHT:
                if (sideways(-1, counter, 0) &&
                        dislodgeSample(counter, 1) &&
                        sideways(1, counter, 2))
                    stop();
                break;
        }

    }


    public boolean dislodgeSample(long[] v, int idx) {
        v[idx]++;
        boolean isDone = false;
        if (v[idx] < 20) {
            goRight(.2);
        } else if (v[idx] < 40) {
            goLeft(.2);
        } else {
            isDone = true;
        }
        return isDone;

    }

    public boolean goToNeutral(long[] v, int idx) {
        v[idx]++;

        boolean isDone = false;

        if (v[idx] < 40) {
            goRight(0.2);
        } else {
            isDone = true;
        }

        return isDone;
    }

    public boolean sideways(int whichDir, long[] v, int idx) {
        v[idx]++;

        boolean isDone = false;

        if (v[idx] < 20) {
            if (whichDir == 1)
                goForward(.2);
            else goBackward(.2);
        } else {
            isDone = true;
        }

        return isDone;
    }


    protected final void goForward(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(power);
        this.hardwareMap.getFrontRightMotor().setPower(power);
        this.hardwareMap.getBackLeftMotor().setPower(power);
        this.hardwareMap.getBackRightMotor().setPower(power);
    }

    protected final void goLeft(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(power);
        this.hardwareMap.getFrontRightMotor().setPower(-power);
        this.hardwareMap.getBackLeftMotor().setPower(-power);
        this.hardwareMap.getBackRightMotor().setPower(power);
    }

    protected final void goRight(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(-power);
        this.hardwareMap.getFrontRightMotor().setPower(power);
        this.hardwareMap.getBackLeftMotor().setPower(power);
        this.hardwareMap.getBackRightMotor().setPower(-power);
    }

    protected final void goBackward(double power) {
        this.hardwareMap.getFrontLeftMotor().setPower(-power);
        this.hardwareMap.getFrontRightMotor().setPower(-power);
        this.hardwareMap.getBackLeftMotor().setPower(-power);
        this.hardwareMap.getBackRightMotor().setPower(-power);
    }

    protected final void stopMotors() {
        this.hardwareMap.getFrontLeftMotor().setPower(0);
        this.hardwareMap.getFrontRightMotor().setPower(0);
        this.hardwareMap.getBackLeftMotor().setPower(0);
        this.hardwareMap.getBackRightMotor().setPower(0);


        this.hardwareMap.getLiftMotor().setPower(0);
    }
}
