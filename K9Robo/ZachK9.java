package org.firstinspires.ftc.teamcode.team.K9Robo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin3.Merlin3Hardware;

// Start out by writing a arcade joystick drive for the K9 - commands you should need are below
// Once your done with that try to work with the servos
// Look at other codes to be able to do that and make sure you comment your code
// remember ;
// I am still reachable so reach out to me if you are stuck
// When you are done try it on the robot

@TeleOp(name="Zack", group="Zach")

public class ZachK9 extends OpMode {

    HardwareK9Robo robot = new HardwareK9Robo();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){
        robot.init(hardwareMap);
    }
    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){
        // Write your code in here

    }
    @Override
    public void stop(){}


}
/*
 * Some commands to know
 *
 *  gamepad1.right_stick_x - returns the right sticks x value
 *  gamepad1.right_stick_y - returns the right sticks y value
 *  gamepad1.left_trigger - returns how far the left trigger has been pressed
 *  gamepad1.right_trigger - returns how far the left trigger has been pressed
 *  robot.leftMotor.setPower(value); - how you set the left motor power
 *  robot.rightMotor.setPower(value); - how you set the right motor power
 *  double someName - how you create a number variable.
 *  Variable1 = Range.clip(Variable1, min value, max value); - Range clips that value
 *
 */