package org.firstinspires.ftc.teamcode.Other;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import android.util.Log;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.DecimalFormat;


import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware;

@TeleOp(name = "NavX Sensor", group = "Other")
public class NavXSensor extends LinearOpMode {


    /* Declare OpMode members. */
    Merlin1Hardware robot           = new Merlin1Hardware();
    //private final int NAVX_DIM_I2C_PORT = 2;
    //private AHRS navx_device;
    //private navXPIDController yawPIDController;

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        int number  = 1;

        //navx_device = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"), NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData);
        //yawPIDController = new navXPIDController( navx_device, navXPIDController.navXTimestampedDataSource.YAW);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("NavX Heading", robot.navx_device.getCompassHeading());
            telemetry.addData("NavX Altitude", robot.navx_device.getAltitude());
            telemetry.addData("NavX BoardYawAxis", robot.navx_device.getBoardYawAxis());
            telemetry.addData("NavX getQuaternionW", robot.navx_device.getQuaternionW());
            telemetry.addData("NavX getQuaternionY", robot.navx_device.getQuaternionY());
            telemetry.addData("NavX getQuaternionX", robot.navx_device.getQuaternionX());
            telemetry.addData("NavX getQuaternionZ", robot.navx_device.getQuaternionZ());
            telemetry.addData("NavX getRawAccelX", robot.navx_device.getRawAccelX());
            telemetry.addData("NavX getRawAccelY", robot.navx_device.getRawAccelY());
            telemetry.addData("NavX getRawAccelZ", robot.navx_device.getRawAccelZ());
            telemetry.addData("NavX getRawGyroX", robot.navx_device.getRawGyroX());
            telemetry.addData("NavX getRawGyroY", robot.navx_device.getRawGyroY());
            telemetry.addData("NavX getRawGyroZ", robot.navx_device.getRawGyroZ());
            telemetry.addData("NavX getPitch", robot.navx_device.getPitch());
            telemetry.addData("NavX getRawMagX", robot.navx_device.getRawMagX());
            telemetry.addData("NavX getRawMagY", robot.navx_device.getRawMagY());
            telemetry.addData("NavX getRawMagZ", robot.navx_device.getRawMagZ());
            telemetry.addData("NavX getRoll", robot.navx_device.getRoll());
            telemetry.addData("NavX getWorldLinearAccelX", robot.navx_device.getWorldLinearAccelX());
            telemetry.addData("NavX getWorldLinearAccelY", robot.navx_device.getWorldLinearAccelY());
            telemetry.addData("NavX getWorldLinearAccelZ", robot.navx_device.getWorldLinearAccelZ());
            telemetry.addData("NavX getYaw", robot.navx_device.getYaw());
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}