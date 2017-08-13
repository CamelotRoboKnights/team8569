package org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin3;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;






/**
 * This is NOT an opmode.
 *
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Motor1:      "motor1"
 * Motor channel:  Motor2:      "motor2"
 * Motor channel:  Motor3:      "motor3"
 * Motor channel:  Motor4:      "motor4"
 * Motor channel:  Sweeper:     "sweeper"
 * Motor channel:  Flipper:     "flipper"
 *
 * Core Device Module: "dim"
 * Optical Distance Sensor: LeftLight: "odrSensor"          The wiring for these two is backwards
 * Optical Distance Sensor: RightLight: "odlSensor"
 * Range Sensor: RightRange: "RightRange"
 * Range Sensor: LeftRange: "LeftRange"
 *
 *
 *
 */
public class RevTouchHardware
{
    /* Public OpMode members. */
    public DigitalChannel touch;


    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public RevTouchHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        touch = hwMap.digitalChannel.get("touch");


        // Define and initialize ALL installed servos.

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}

