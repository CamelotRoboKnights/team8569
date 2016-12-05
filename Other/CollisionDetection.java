package org.firstinspires.ftc.teamcode.team.Other;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@TeleOp(name = "Collision", group = "Other")//This is the name of the code and what will be seen on the robot phone
//@Disabled //Uncomment this if it is not wanted on the phone
public class CollisionDetection extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();//Setting up the Hardware map

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //boolean calibration_complete = false;
        double CurrWorldLinearAccelX;
        double LastWorldLinearAccelX;
        double CurrWorldLinearAccelY;
        double LastWorldLinearAccelY;

        double CurrentJerkX;
        double CurrentJerkY;

        String collision_state;
        final String Collision = "Collision";
        final String NoCollision = "--------";
        LastWorldLinearAccelX = 0.0;
        LastWorldLinearAccelY = 0.0;
        collision_state = "--------";
        boolean CollisionDetected = false;
        final double CollisionThesholdG = 0.5;


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            CurrWorldLinearAccelX = robot.navx_device.getWorldLinearAccelX();
            CurrentJerkX = CurrWorldLinearAccelX - LastWorldLinearAccelX;
            LastWorldLinearAccelX = CurrWorldLinearAccelX;

            CurrWorldLinearAccelY = robot.navx_device.getWorldLinearAccelY();
            CurrentJerkY = CurrWorldLinearAccelY - LastWorldLinearAccelY;
            LastWorldLinearAccelY = CurrWorldLinearAccelY;



            if ( ( Math.abs(CurrentJerkX) > CollisionThesholdG ) || ( Math.abs(CurrentJerkY) > CollisionThesholdG) )
            {
                CollisionDetected = true;
            }




            telemetry.update();
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

}
