package org.firstinspires.ftc.teamcode.team.Merlin1;

import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
//import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.hardware.I2cAddr;
//import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
//import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
//import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;

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
 * STILL NEED THE SENSORS
 *
 *
 */
public class Merlin1Hardware
{
    /* Public OpMode members. */
    public DcMotor  Motor1    = null;//Front right motor
    public DcMotor  Motor2    = null;//Front left motor
    public DcMotor  Motor3    = null;//Back left motor
    public DcMotor  Motor4    = null;//Back right motor
    public DcMotor  Sweeper   = null;//The sweeper\ball collector
    public DcMotor  Flipper   = null;//The flipper that flips the ball
    public OpticalDistanceSensor LeftLight;//The left Optical Distance Sensor
    public OpticalDistanceSensor RightLight;//The right Optical Distance Sensor
    public ModernRoboticsI2cRangeSensor rangeSensor;//The range sensor on the buton press side
    public ModernRoboticsI2cCompassSensor compas;//The compass Modern robotics sensor
    public ModernRoboticsI2cRangeSensor rangeSensor2;


                        //The navX sensor
    public final int NAVX_DIM_I2C_PORT = 2;//Sets the NavX port
    public AHRS navx_device;
    public navXPIDController yawPIDController;

    //public ElapsedTime runtime = new ElapsedTime();//Sets a thing called runtime that measures an elapsed time.

                        //More NavX
    public final byte NAVX_DEVICE_UPDATE_RATE_HZ = 50;

    public final double YAW_PID_P = 0.005;
    public final double YAW_PID_I = 0.0;
    public final double YAW_PID_D = 0.0;

    public boolean calibration_complete = false;//Sets a boolean that says if the NavX sensor is complete or not




    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Merlin1Hardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        RightLight = hwMap.opticalDistanceSensor.get("odrSensor");//Finds the Right Optical Distance sensor in the hardware map
        LeftLight = hwMap.opticalDistanceSensor.get("odlSensor");//Finds the Left Optical Distance sensor in the hardware map
        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "range");//Finds the range sensor in the hardware map
        rangeSensor = hwMap.get(ModernRoboticsI2cRangeSensor.class, "range");
        compas = hwMap.get(ModernRoboticsI2cCompassSensor.class, "compas");//Finds the compass sensor in the hardware map

        navx_device = AHRS.getInstance(hwMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT, AHRS.DeviceDataType.kProcessedData, NAVX_DEVICE_UPDATE_RATE_HZ);
        yawPIDController = new navXPIDController( navx_device, navXPIDController.navXTimestampedDataSource.YAW);


        // Define and Initialize Motors
        Motor1  = hwMap.dcMotor.get("motor1");//Finds the front right motor in the hardware map
        Motor2  = hwMap.dcMotor.get("motor2");//Finds the front left motor in the hardware map
        Motor3  = hwMap.dcMotor.get("motor3");//Finds the back left motor in the hardware map
        Motor4  = hwMap.dcMotor.get("motor4");//Finds the back right motor in the hardware map
        Sweeper = hwMap.dcMotor.get("sweeper");//Finds the sweeper in the hardware map
        Flipper = hwMap.dcMotor.get("flipper");//Finds the flipper in the hardware map
        Motor1.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to reverse so forwards is positive
        Motor2.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive so forward is still positive
        Motor3.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive so forward is still positive
        Motor4.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to reverse so forwards is positive
        Sweeper.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to reverse so collecting is positive
        Flipper.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the Flipper to forward so launching is positive

        // Set all motors to zero power
        Motor1.setPower(0);//Sets the power to 0 so motors don't move
        Motor2.setPower(0);
        Motor3.setPower(0);
        Motor4.setPower(0);
        Sweeper.setPower(0);
        Flipper.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        Motor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Motor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Motor3.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Motor4.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Sweeper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Flipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);//Use encoder's becuase that is used for the flicking



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

