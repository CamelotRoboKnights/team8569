//This is done being commented 17-1-13

package org.firstinspires.ftc.teamcode.team.Merlin1718.Scrimmage;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRColor;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;

public class ScrimmageHardware
{
    /* Public OpMode members. */
    public DcMotor  Motor1    = null;//Front right motor
    public DcMotor  Motor2    = null;//Front left motor
    public DcMotor  Motor3    = null;//Back left motor
    public DcMotor  Motor4    = null;//Back right motor
    public DcMotor  glyph    = null;
    public Servo leftGrasper = null;
    public Servo rightGrasper = null;
    public Servo leftSorter = null;
    public Servo rightSorter = null;
    public ColorSensor leftColor = null;
    public ColorSensor rightColor = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode

    /* Constructor */
    public ScrimmageHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        // Define and Initialize Motors
        Motor1  = hwMap.dcMotor.get("motor1");//Finds the front right motor in the hardware map
        Motor2  = hwMap.dcMotor.get("motor2");//Finds the front left motor in the hardware map
        Motor3  = hwMap.dcMotor.get("motor3");//Finds the back left motor in the hardware map
        Motor4  = hwMap.dcMotor.get("motor4");//Finds the back right motor in the hardware map
        glyph = hwMap.dcMotor.get("glyph");
        leftGrasper = hwMap.servo.get("leftGrasper");
        rightGrasper = hwMap.servo.get("rightGrasper");
        leftSorter = hwMap.servo.get("leftSorter");
        rightSorter = hwMap.servo.get("rightSorter");
        leftColor = hwMap.get(ColorSensor.class, "leftColor");
        rightColor = hwMap.get(ColorSensor.class, "rightColor");



        Motor1.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to reverse so forwards is positive
        Motor2.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive so forward is still positive
        Motor3.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive so forward is still positive
        Motor4.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to reverse so forwards is positive
        glyph.setDirection(DcMotorSimple.Direction.REVERSE);
        // Set all motors to zero power
        Motor1.setPower(0);//Sets the power to 0 so motors don't move
        Motor2.setPower(0);
        Motor3.setPower(0);
        Motor4.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        Motor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Motor4.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Define and initialize ALL installed servos.

    }

}

