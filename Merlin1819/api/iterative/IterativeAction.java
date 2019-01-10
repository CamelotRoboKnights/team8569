package org.firstinspires.ftc.teamcode.team.Merlin1819.api.iterative;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Defines the means for an
 * iterative {@link OpMode} action
 * to be carried out.
 *
 * @author Zigy Lim
 *
 * @version 1.0
 * @since 1.0
 *
 * @see IterativeActionPool
 * @see IterativeActionOpMode
 * @see IterativeState
 */
public interface IterativeAction
{
    /**
     * Executes an action.
     * @param state the state in the chain of events
     * @param map the hardware map associated with this event
     *
     * @author Zigy Lim
     *
     * @since 1.0
     */
    void execute(IterativeState state, HardwareMap map);
}
