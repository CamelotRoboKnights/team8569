package org.firstinspires.ftc.teamcode.team.Other;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.text.DecimalFormat;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@TeleOp(name = "Feild Orented", group = "Other")
public class FeildOrentedDrive extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        double JoyX;
        double JoyY;
        double NewX;
        double NewY;
        double pi = 3.1415926;
        double OrientationDegrees;
        double OrientationRadians;




        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        while (opModeIsActive()) {
            JoyY = -gamepad1.left_stick_y;
            JoyX = gamepad1.left_stick_x;
            OrientationDegrees = robot.navx_device.getYaw();
            OrientationRadians = OrientationDegrees * pi / 180;
            NewY = JoyY * Math.cos(OrientationRadians) + JoyX * Math.sin(OrientationRadians);
            NewX = -JoyY * Math.sin(OrientationRadians) + JoyX * Math.cos(OrientationRadians);

            robot.Motor1.setPower(NewY - NewX);//Sets the motor power for the front right motor
            robot.Motor2.setPower(NewY + NewX);//sets the motor power for the front left motor
            robot.Motor3.setPower(NewY - NewX);//Sets the motor power for the back left motor
            robot.Motor4.setPower(NewY + NewX);//Sets the motor power for the back right motor


            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
        }
    }

