package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.Action;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeAction;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

import java.util.Objects;

/**
 *
 * An example class to show how to control
 * state of an {@link IterativeActionOpMode} state machine.
 *
 * @author Zigy Lim
 * @version 1.0
 * @since 1.0
 */
@Autonomous(name = "zigyapitest")
public class IterativeActionTest extends IterativeActionOpMode
{
    private int ping,
                pong;

    public IterativeActionTest()
    {
        this.ping = this.pong = 0;
    }

    @Action(order = 0)
    public void updatePing(IterativeState state, HardwareMap map)
    {
        this.telemetry.addData("Ping", ++this.ping);
        this.telemetry.update();
    }

    @Action(order = 1)
    public void updatePong(IterativeState state, HardwareMap map)
    {
        this.telemetry.addData("Pong", ++this.pong);
        this.telemetry.update();

       // state.restart();
    }
}
