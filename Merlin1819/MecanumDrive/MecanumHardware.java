package org.firstinspires.ftc.teamcode.team.Merlin1819.MecanumDrive;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;



public class MecanumHardware {


    public DcMotor  frontRightMotor    = null;
    public DcMotor  frontLeftMotor     = null;
    public DcMotor  backRightMotor     = null;
    public DcMotor  backLeftMotor      = null;


    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode

    /* Constructor */
    public MecanumHardware(){}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        // Define and Initialize Motors
        frontLeftMotor = hwMap.dcMotor.get("frontLeftMotor");//Finds the Front Left motor in the hardware map
        frontRightMotor  = hwMap.dcMotor.get("frontRightMotor");//Finds the Front Right motor in the hardware map
        backLeftMotor = hwMap.dcMotor.get("backLeftMotor");//Finds the Back Left motor in the hardware map
        backRightMotor = hwMap.dcMotor.get("backRightMotor");//Finds the Back Right motor in the hardware map



        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power to positive because duh.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive because duh.
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power as positive because duh.
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive because duh.

        frontRightMotor.setPower(0);//Sets the power to 0 so motors don't move
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);



        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }




}

