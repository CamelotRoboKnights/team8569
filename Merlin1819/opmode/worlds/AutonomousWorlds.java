package org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.worlds;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;
import org.firstinspires.ftc.teamcode.team.Merlin1819.opmode.robot.CubeSampler;

@Autonomous(name = "AutonomousWorlds", group = "Worlds")
public class AutonomousWorlds extends IterativeActionOpMode
{
    private CubeSampler sampler;
    private CubeSampler.CubePosition yellowPosition;

    @Override
    protected void initState()
    {
        super.initState();
        assert (this.hardwareMap != null);
        this.sampler = new CubeSampler(this.hardwareMap, 0.40);
        this.sampler.activateTfod();

        //Getting the position before the robot lands
        //TODO verify that this camera angle is optimal
        this.yellowPosition = this.sampler.detectCubePosition();
    }

    @Action(order = 0)
    public void land(IterativeState state, HardwareMap map)
    {
        this.telemetry.addData("Cube Position", "Cube Position " +
                ((this.yellowPosition != null) ? this.yellowPosition.toString().toLowerCase() : "cube position not detected"));

        switch (this.yellowPosition) {
            case LEFT:

                break;
            case RIGHT:
                break;

            case CENTER:

                break;

            default:

                break;
        }
    }

    public void reorient(IterativeState state, HardwareMap map)
    {

    }
}
