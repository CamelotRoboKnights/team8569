package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.Action;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeAction;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeActionOpMode;
import org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative.IterativeState;

import java.util.Objects;

@Autonomous(name= "zigyapitest")
public class IterativeActionTest extends IterativeActionOpMode
{
    @Action
    public void someAction(IterativeState state, HardwareMap map)
    {
        this.telemetry.addData("Test", "Test");
        state.restart();
    }
}
