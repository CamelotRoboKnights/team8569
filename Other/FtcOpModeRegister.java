/*
 * This is one way to display codes on the phone, we decided to go the other way with either
 * @Autonomous or @TeleOp at the beginning of our code.
 */

package org.firstinspires.ftc.teamcode.team.Other;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

/**
 * Register Op Modes
 */
public class FtcOpModeRegister implements OpModeRegister {

    /**
     * The Op Mode Manager will call this method when it wants a list of all
     * available op modes. Add your op mode to the list to enable it.
     *
     * @param manager op mode manager
     */
    public void register(OpModeManager manager) {

    /*
     * register your op modes here.
     * The first parameter is the name of the op mode
     * The second parameter is the op mode class property
     *
     * If two or more op modes are registered with the same name, the app will display an error.
     */
        //manager.register("Basic Vision Sample", BasicVisionSample.class);
        //manager.register("Linear Vision Sample", LinearVisionSample.class);
        //manager.register("Manual Vision Sample", ManualVisionSample.class);
    }
}
